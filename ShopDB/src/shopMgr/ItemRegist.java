// 상품 등록

package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ItemRegist {
	//	public ItemRegist() {}; // 생성자
	public void regist() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null; // 쿼리에 매개변수 전달
		ResultSet rs = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHOPDB", "1234");
			conn.setAutoCommit(false);
			//			System.out.println("DB 접속 완료\n");


			// SQL 쿼리 생성 (매개 변수를 사용하여 쿼리를 생성)
			// ITEM_NUMBER 컬럼은 DB측에서 시퀀스를 이용하여 순차적으로 증가시키기.
			String sql = "INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, PRICE, STOCK, ITEM_INFO) VALUES (?, ?, ?, ?, ?, ?)";

			//				System.out.println("sql 변수값 입력완료");
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			System.out.printf("상품명 입력 : \n");
			String iName = sc.nextLine();
			System.out.printf("상품 분류 : \n");
			System.out.println("[1]상의-반소매 [2]상의-긴소매 [3]반바지 [4]긴바지 [5]신발 : ");
		
			int cateChoice = sc.nextInt();
			sc.nextLine();
			String category = "";
			switch(cateChoice) {
				case 1 : category = "TOPS_S_SLEEVE"; break; 
				case 2 : category = "TOPS_L_SLEEVE"; break;
				case 3 : category = "BOTTOMS_SHORTS"; break;
				case 4 : category = "BOTTOMS_PANTS"; break;
				case 5 : category = "SHOES"; break;
				default : System.out.println("잘못된 입력입니다."); break;
			}
			//				String category = sc.nextLine();
			System.out.printf("브랜드 입력 : \n");
			String iBrand = sc.nextLine();
			System.out.printf("가격 입력 : \n");
			int iPrice = sc.nextInt();
			sc.nextLine(); // 버퍼 클리어
			// 재고는 입력받는것이 아닌 자동 수량 변경으로 변경할것
			System.out.printf("입고 수량 입력 : \n");
			int stock = sc.nextInt();
			sc.nextLine(); // 버퍼 클리어
			System.out.printf("상품설명 : \n");
			String iInfo = sc. nextLine();

			pstmt.setString(1, iName); 
			pstmt.setString(2, category); 
			pstmt.setString(3, iBrand); 
			pstmt.setInt(4, iPrice);
			pstmt.setInt(5, stock); 
			pstmt.setString(6, iInfo);


			// SQL 실행
			pstmt.executeUpdate();

			System.out.println("상품 등록 완료");
			conn.commit();
			//			if (rowsInserted > 0) {
			//			    System.out.println("데이터가 성공적으로 삽입되었습니다.");
			//			} else {
			//			    System.out.println("데이터 삽입 실패!");
			//			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ItemRegist-Regist()내 예외 발생");
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
				//				sc.close();
//				System.out.println("시스템 안전 종료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}
}
