package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

 // 일반 로그인
public class LogIn {
	private String userId;
	public void setUserId(String userId) {
		this.userId = userId;
	}

//	public  String getUserId() {
//		return this.userId;
//
//	}

	public String Login() {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		String loggedInUserID = null;
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);
			LogIn lg = new LogIn();

			System.out.print("아이디 입력: ");
			String userId = sc.nextLine();
			lg.setUserId(userId);
			System.out.print("비밀번호 입력: ");
			String password = sc.nextLine();

			// SQL 쿼리 작성
			String sql = "SELECT * FROM USERS WHERE USER_ID = ? AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			// SQL 실행 및 결과 확인
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("로그인 성공!");
				//				loggedInUserID = userId;
				main.mainThree(userId);	// 로그인 성공 시 mainTwo메서드로 보내 회원전용 기능들을 열어준다.
			} else {
				System.out.println("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("예외 발생");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
				//	                sc.close();
				System.out.println("로그인 시스템 안전 종료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return loggedInUserID;
	}
}

class AdminLogin extends LogIn{

	@Override
	public String Login() {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		String loggedInUserID = null;
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);
			LogIn lg = new LogIn();

			System.out.print("관리자 아이디 입력: ");
			String adminId = sc.nextLine();
			lg.setUserId(adminId);
			System.out.print("비밀번호 입력: ");
			String password = sc.nextLine();

			// SQL 쿼리 작성
			String sql = "SELECT * FROM USERS WHERE USER_ID = ? AND PASSWORD = ? AND ADMIN = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, password);
			pstmt.setString(3, "Y");

			// SQL 실행 및 결과 확인
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("관리자 로그인 성공!");
				//				loggedInUserID = userId;
				main.mainTwo(adminId);	// 로그인 성공 시 mainTwo메서드로 보내 회원전용 기능들을 열어준다.
			} else {
				System.out.println("로그인 실패: ID / PW / 권한을 확인하세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("예외 발생");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
				//	                sc.close();
				System.out.println("로그인 시스템 안전 종료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return loggedInUserID;
	}
}
