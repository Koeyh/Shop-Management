// 회원가입

package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
프로그램이 종료될 때 마다 num이 초기화됨
Java에서는 회원 번호를 자동으로 만들어서 보내 줄 방법을 모르겠다.
시퀀스로 돌리는 쪽으로 가보자
 */
public class RegistUsers { // 회원가입

	public void SignUp() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);



			System.out.println("일반 사용자 회원 가입 시작");

			String sql = "INSERT INTO USERS (USER_ID, PASSWORD, USER_NAME, USER_PHONE, ADMIN) VALUES (?, ?, ?, ?, ?)";

			//				System.out.println("sql 변수값 입력완료");
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			System.out.println("ID 입력 : ");
			String uId = sc.nextLine();
//			System.out.println("패스워드 입력 : ");
			String pw = pwCheck(sc);
			System.out.println("이름 입력 : ");
			String uName = sc.nextLine();
//			System.out.println("연락처(휴대폰) 입력 : ");
			String uPhone = this.phoneCheck(sc);
			String type = "N";


			pstmt.setString(1, uId); // 두 번째 매개 변수 설정
			pstmt.setString(2, pw); // 두 번째 매개 변수 설정
			pstmt.setString(3, uName); // 세 번째 매개 변수 설정
			pstmt.setString(4, uPhone); // 네 번째 매개 변수 설정
			pstmt.setString(5, type); // 네 번째 매개 변수 설정



			pstmt.executeUpdate();
			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 예외 발생");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			try {
				if (conn != null)conn.close();
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
				System.out.println("회원가입이 완료되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 비밀번호 정규표현식
	public String pwCheck(Scanner sc) {
//		System.out.println("pwCheck() 호출완료");
		String password = "";
		/* (?=.*[A-Za-z]) : 적어도 하나의 알파벳이 포함되어야 함
		 * (?=.*\\d) : 적어도 하나의 숫자 포함
		 * (?=.*[@$!%*?&]) 하나의 특문 포함
		 * [A-Za-z\\d@$!%*?&]{8,} 글자 수 최소 8자 이상
		 * */
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		boolean isValidPassword = false;
		while (true) {
			System.out.println("패스워드 입력 :");
			password = sc.nextLine();
			// 정규 표현식과 입력된 전화번호를 비교
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(password);

			// 정규 표현식과 매치되는지 확인
			if (matcher.matches()) {
//				System.out.println("입력한 비밀번호: " + password);
				isValidPassword  = true;
				break; // 올바른 형식 입력 시 반복 종료
			} else {
				System.out.println("올바른 형식의 비밀번호를 입력하세요.");
			}
		}
		return password;
	}

	// 전화번호 표현식	
	public String phoneCheck(Scanner sc) {
		//		Scanner 선언 없이 Scanner타입 변수 sc를 받아와서 사용자 입력 실행
		String phoneNumber = "";
		// 전화번호 형식에 대한 정규 표현식
		String regex = "\\d{3}-\\d{4}-\\d{4}";
		boolean isValidPhoneNumber = false;

		// 입력받을 때까지 반복
		while (true) {
			System.out.println("전화번호를 입력하세요 (형식: xxx-xxxx-xxxx) : ");
			phoneNumber = sc.nextLine();

			// 정규 표현식과 입력된 전화번호를 비교
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(phoneNumber);

			// 정규 표현식과 매치되는지 확인
			if (matcher.matches()) {
//				System.out.println("입력한 전화번호: " + phoneNumber);
				isValidPhoneNumber  = true;
				break; // 올바른 형식의 전화번호 입력 시 반복 종료
			} else {
				System.out.println("올바른 형식의 전화번호를 입력하세요.");
			}
		}
		return phoneNumber;
	}
}

class RegistAdmins extends RegistUsers { 
	// 관리자 회원가입
	@Override
	public void SignUp() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String adminCode = "abcdef";
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);



			System.out.println("관리자 회원 가입 시작");

			String sql = "INSERT INTO USERS (USER_ID, PASSWORD, USER_NAME, USER_PHONE, ADMIN) VALUES (?, ?, ?, ?, ?)";

			//				System.out.println("sql 변수값 입력완료");
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			System.out.println("관리자 코드 입력 : ");
			String inputCode = sc.nextLine();
			if(adminCode.equals(inputCode)) {
				System.out.println("ID 입력 : ");
				String uId = sc.nextLine();
				String pw = pwCheck(sc);
				System.out.println("이름 입력 : ");
				String uName = sc.nextLine();
				String uPhone = super.phoneCheck(sc);
				String type = "Y"; // 관리자 계정 확인용

				pstmt.setString(1, uId); // 두 번째 매개 변수 설정
				pstmt.setString(2, pw); // 두 번째 매개 변수 설정
				pstmt.setString(3, uName); // 세 번째 매개 변수 설정
				pstmt.setString(4, uPhone); // 네 번째 매개 변수 설정
				pstmt.setString(5, type); // 네 번째 매개 변수 설정


				// SQL 실행
				//				int rowsInserted = pstmt.executeUpdate();
				pstmt.executeUpdate();
				System.out.println("관리자 " + uId + " 가입 완료");
				conn.commit();
			} else {
				System.out.println("관리자 번호가 틀렸습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("예외 발생");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			try {
				if (conn != null)conn.close();
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
				System.out.println("회원가입 시스템 안전 종료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}