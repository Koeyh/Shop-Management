package shopMgr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

// 주문내역 조회
class ShowOrders {
	// 통화 서식 설정을 위해 DecimalFormat 객체 생성하여 설정
	DecimalFormat df = new DecimalFormat("###,###,###원");
	public void ShowAllSaleCost() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	// thin = tcp : 데이터 전송 방식
			String user = "SHOPDB";	// Oracle 사용자 계정
			String pw = "1234";		// Oracle 사용자 비밀번호
			/* 중복 방지를 위해 ORDERS 테이블에는 상품 가격을 포함하지 않음
			   가격을 포함한 PRODUCTS 테이블을 조인하여 가격을 가져오기 */ 
			String sql = "SELECT SUM(o.QUANTITY * p.PRICE) AS 총매출액 FROM ORDERS o LEFT OUTER JOIN PRODUCTS p ON o.ITEM_NAME = p.ITEM_NAME" ;
			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				double wholeSaleCost = rs.getInt("총매출액");
				String formattedCost = df.format(wholeSaleCost);
				System.out.printf("총 매출액 : %s\n", formattedCost);
			} // while 종료
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				//				System.out.println("DB 연결 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 관리자용 구매내역 조회 메서드
	public void ShowOrderAdmin() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		try {
			// JDBC 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//			System.out.println("JDBC Driver 로드 성공 !"); // 확인용 콘솔출력
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	// thin = tcp : 데이터 전송 방식
			//				localhost = 사용자 PC의 DB에 접속함 / 1521 : 포트 번호
			String user = "SHOPDB";	// Oracle 사용자 계정
			String pw = "1234";		// Oracle 사용자 비밀번호

			// 테이블의 중복 방지를 위해 ORDERS 테이블은 PRODUCTS 테이블과 상품명으로 연결시켜서 가격을 받아오도록 설정
			String sql = "SELECT o.USER_ID, o.ITEM_NAME, o.ORDER_DATE, o.QUANTITY, (p.PRICE * o.QUANTITY) AS 금액 "
					+ "FROM ORDERS o LEFT OUTER JOIN PRODUCTS p "
					+ "ON o.ITEM_NAME = p.ITEM_NAME ";


			// DB 연결
			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			while(rs.next()) {
				String uId = rs.getString("USER_ID");
				String iName = rs.getString("ITEM_NAME");
				Date orderDate = rs.getDate("ORDER_DATE");
				int quantity = rs.getInt("QUANTITY");
				double totalPrice = rs.getDouble("금액");
				String formattedTotalPrice = df.format(totalPrice);

				System.out.print("|사용자 ID : " + uId);
				System.out.print("|상품명 : " + iName);
				System.out.print("|주문 일자 : " + orderDate);
				System.out.print("|주문 수량 : " + quantity);
				System.out.printf("|총 금액 : %s\n", formattedTotalPrice);
			} // while 종료
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				//				System.out.println("DB 연결 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void ShowOrder(String loggedInID) {
		// ORDERS 테이블의 구매자 정보 입력을 위해 Login 시 받아온 ID를 매개변수로 받아옴
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		try {
			// JDBC 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//			System.out.println("JDBC Driver 로드 성공 !"); // 확인용 콘솔출력
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	// thin = tcp : 데이터 전송 방식
			//				localhost = 사용자 PC의 DB에 접속함 / 1521 : 포트 번호
			String user = "SHOPDB";	// Oracle 사용자 계정
			String pw = "1234";		// Oracle 사용자 비밀번호

			// 사용자 계정의 주문조회는 본인의 주문내역만 볼 수 있게 조건 설정
			String sql = "SELECT o.USER_ID, o.ITEM_NAME, o.ORDER_DATE, o.QUANTITY, (p.PRICE * o.QUANTITY) AS 금액 "
					+ "FROM ORDERS o LEFT OUTER JOIN PRODUCTS p "
					+ "ON o.ITEM_NAME = p.ITEM_NAME "
					+ "WHERE o.USER_ID = '" + loggedInID + "'";
			
			// DB 연결
			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			while(rs.next()) {
				String uId = rs.getString("USER_ID");
				String iName = rs.getString("ITEM_NAME");
				Date orderDate = rs.getDate("ORDER_DATE");
				int quantity = rs.getInt("QUANTITY");
				double totalPrice = rs.getDouble("금액");

				String formattedTotalPrice = df.format(totalPrice);
				System.out.printf("|사용자 ID : %-8s", uId);
				System.out.printf("|상품명 : %-20s", iName);
				System.out.print("|주문 일자 : " + orderDate);
				System.out.printf("|주문 수량 : %-5d", quantity);
				System.out.printf("|총 금액 : %-10s\n", formattedTotalPrice);
			} // while 종료
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				//				System.out.println("DB 연결 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
