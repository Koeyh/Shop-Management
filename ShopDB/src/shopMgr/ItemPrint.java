//상품 조회

package shopMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 관리자 계정의 상품 조회는 분류별 조회를 없애고 재고량을 추가해서 출력시키도록
class ItemPrintAdmin extends ItemPrint{
	@Override
	public void print() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// JDBC 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//			System.out.println("JDBC Driver 로드 성공 !"); // 확인용 콘솔출력
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	// thin = tcp : 데이터 전송 방식
			//				localhost = 사용자 PC의 DB에 접속함 / 1521 : 포트 번호
			String user = "SHOPDB";	// Oracle 사용자 계정
			String pw = "1234";		// Oracle 사용자 비밀번호


			System.out.println("전체 상품을 조회합니다.");
			String sql = "SELECT * FROM PRODUCTS";
			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);


			System.out.println();
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("|                              상품명                                 |      가격      |   재고량   |");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			while(rs.next()) {
				String iName = rs.getString("ITEM_NAME");				
				String iBrand = rs.getString("BRAND");
				int iPrice = rs.getInt("PRICE");
				int stock = rs.getInt("STOCK");
				String formattedName = String.format("%-40s", iName);
				String formattedBrand = String.format("%-20s", iBrand);
				String formattedPrice = String.format("%-9d 원", iPrice);


				//					System.out.printf("| %s %-40s | %9d | %-70s |\n", iBrand, iName, iPrice, iInfo);
				System.out.printf("|%s %s | %s | %d EA\n", formattedBrand, formattedName, formattedPrice, stock);

				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}

public class ItemPrint {
	public void print() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// JDBC 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//			System.out.println("JDBC Driver 로드 성공 !"); // 확인용 콘솔출력
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	// thin = tcp : 데이터 전송 방식
			//				localhost = 사용자 PC의 DB에 접속함 / 1521 : 포트 번호
			String user = "SHOPDB";	// Oracle 사용자 계정
			String pw = "1234";		// Oracle 사용자 비밀번호

			int ch = 0;
			while (ch != 3) {
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println("1. 전체 상품 조회 | 2. 브랜드별 조회 | 3. 카테고리별 조회 | 4.상품조회 종료");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				ch = sc.nextInt();
				sc.nextLine();
				if(ch == 1) {
					System.out.println("전체 상품을 조회합니다.");
					String sql = "SELECT * FROM PRODUCTS";
					conn = DriverManager.getConnection(url, user, pw);
					Statement st = conn.createStatement();
					rs = st.executeQuery(sql);
				} else if (ch == 2) { // 브랜드별 조회
					String brandName="";
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					System.out.println("조회하려는 브랜드의 번호를 입력하세요.");
					System.out.println("[1]NIKE | [2]ADIDAS | [3]NEW BALANCE | [4]ASICS | [5]종료");
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					int brand = sc.nextInt();
					sc.nextLine();
					switch(brand) {
					case 1 : {
						brandName = "NIKE";
						break;}
					case 2 : {
						brandName = "ADIDAS";
						break;
					}
					case 3 : {
						brandName = "NEW BALANCE";
						break;
					}
					case 4 : {
						brandName = "ASICS";
						break;
					}
					case 5 : break;
					default : System.out.println("잘못된 입력입니다.");		
					}
					String sql = "SELECT * FROM PRODUCTS WHERE BRAND = ?";
					conn = DriverManager.getConnection(url, user, pw);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, brandName);
					System.out.println(brandName + " 제품을 조회합니다.");
					rs = pstmt.executeQuery();
				} else if (ch == 3) {

					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					System.out.println("[1]상의(반소매) [2]상의(긴소매) [3]하의(반바지) [4]하의(긴바지) [5]신발 [6]카테고리별 조회 나가기");
					System.out.println("조회하려는 카테고리의 번호를 입력하세요.");
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					int ch2 = sc.nextInt();
					sc.nextLine();
					String category = "";
					switch(ch2) {
					case 1:{
						category = "TOPS_S_SLEEVE";
						break;
					}
					case 2: {
						category = "TOPS_L_SLEEVE";
						break;
					}
					case 3: {
						category = "BOTTOMS_SHORTS";
						break;
					}
					case 4: {
						category = "BOTTOMS_PANTS";
						break;
					}
					case 5: {
						category = "SHOES";
						break;
					}
					case 6: 
						break;
					default : System.out.println("잘못된 입력입니다.");
					}
					String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY = ?";
					conn = DriverManager.getConnection(url, user, pw);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, category);
					//					System.out.println(category + " ");
					rs = pstmt.executeQuery();
				} else if (ch == 4) {
					System.out.println("종료");
					break;
				} else {
					System.out.print("잘못된 입력입니다.");
					continue;
				}
				System.out.println();
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println("|                            상품명                               |    가격    |                                상품 정보                                ");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				while(rs.next()) {
					String iName = rs.getString("ITEM_NAME");				
					String iBrand = rs.getString("BRAND");
					int iPrice = rs.getInt("PRICE");
					String iInfo = rs.getString("ITEM_INFO");
					String formattedName = String.format("%-40s", iName);
					String formattedBrand = String.format("%-20s", iBrand);
					String formattedPrice = String.format("%-9d 원", iPrice);
					String formattedInfo = String.format("%-70s", iInfo);

					//					System.out.printf("| %s %-40s | %9d | %-70s |\n", iBrand, iName, iPrice, iInfo);
					System.out.printf("|%s %s | %s | %s\n", formattedBrand, formattedName, formattedPrice, formattedInfo);

				} // while 종료
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
