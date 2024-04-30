package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 상품 구매
public class Purchase {
	private Connection conn;
	private PreparedStatement pstmt;

	public Purchase() {
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 오류");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBC 드라이버 로드 오류");
		}
	}



	// 상품명을 입력하여 바로 상품을 구매하는 메서드
	public void buy(String userId, String itemName, int quantity) {
		try {
			String checkStockSql = "SELECT STOCK FROM PRODUCTS WHERE ITEM_NAME = ?";
			pstmt = conn.prepareStatement(checkStockSql);
			pstmt.setString(1, itemName);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int stock = rs.getInt("STOCK");
				if (stock >= quantity) {
					// 재고가 충분하면 구매를 진행
					String insertOrderSql = "INSERT INTO ORDERS (ORDER_NO, USER_ID, ITEM_NAME, ORDER_DATE, QUANTITY) VALUES (ORDERS_SEQ.nextval, ?, ?, SYSDATE, ?)";
					pstmt = conn.prepareStatement(insertOrderSql);
					pstmt.setString(1, userId);
					pstmt.setString(2, itemName);
					pstmt.setInt(3, quantity);
					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						System.out.println(itemName + "을(를) 구매하였습니다.");
						// 재고 업데이트
						String updateStockSql = "UPDATE PRODUCTS SET STOCK = STOCK - ? WHERE ITEM_NAME = ?";
						pstmt = conn.prepareStatement(updateStockSql);
						pstmt.setInt(1, quantity);
						pstmt.setString(2, itemName);
						pstmt.executeUpdate();
						conn.commit();
					}
				} else {
					System.out.println("상품 재고가 부족합니다. 재고량:" + stock);
				}
			} else {
				System.out.println("상품이 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류");
		} finally {
			// 리소스 해제
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}