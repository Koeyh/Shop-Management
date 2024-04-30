// 사용자 정보 출력
package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ShowUsers {
	public void userPrint() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		try {
			// JDBC 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SHOPDB";
			String pw = "1234";

			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			ResultSet rs = null;

			// 관리자 계정 구분을 위해 ResultSet 객체 미리 선언
			System.out.println("[1]관리자 계정 조회 [2]일반 회원 조회 [3]전체 조회");
			int typeChoice = sc.nextInt();
			sc.nextLine();
			String sql = "";
			if (typeChoice == 1) {
				// 관리자 회원만 조회
				sql = "SELECT ADMIN, USER_ID, USER_NAME, USER_PHONE FROM USERS WHERE ADMIN = 'Y'";
			} else if (typeChoice == 2) {
				// 일반 회원만 조회
				sql = "SELECT ADMIN, USER_ID, USER_NAME, USER_PHONE FROM USERS WHERE ADMIN = 'N'";
			} else if (typeChoice == 3) {
				sql = "SELECT ADMIN, USER_ID, USER_NAME, USER_PHONE FROM USERS";
			}

			// 쿼리 실행
			rs = st.executeQuery(sql);
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("| 관리자 권한 |          ID            |           이름          |       연락처            |");

			// 데이터 읽어오기
			while (rs.next()) {
				String type = rs.getString("ADMIN");
				String uId = rs.getString("USER_ID");
				String uName = rs.getString("USER_NAME");
				String uPhone = rs.getString("USER_PHONE");

				System.out.printf("| %-7s |    %-16s    |     %-12s      |    %15s    |\n", type, uId, uName, uPhone);
			}
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
