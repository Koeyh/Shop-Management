package shopMgr;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void adminLog() {

	}
	public static void main(String[] args) {

		RegistUsers ru = new RegistUsers(); 
		RegistAdmins radmin = new RegistAdmins();
		LogIn login = new LogIn();
		AdminLogin adminLogin = new AdminLogin();
		System.out.println("시작");
		int ch = 0; 
		String userId;
		String adminId;
		while (ch != 3) {
			// 첫번째 조건은 [1]회원가입 [2]로그인 [3] 종료
			// 로그인 - 관리자 / 사용자
			// 관리자 로그인 인증 시 상품등록, 상품조회, 회원조회 기능만
			// 사용자 로그인 인증 시 상품조회, 장바구니, 상품구매 기능만 가능하도록
			// 두번째 조건에서 [1]상품등록 [2]상품조회 [3]장바구니 [4]상품구매 [5]종료
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("[1]회원가입 [2]로그인 [3] 프로그램 종료");			
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			ch = sc.nextInt();
			sc.nextLine();
			if(ch ==  1 ) {
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println("[1]관리자 등록 [2]일반회원 가입");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				int ch2 = sc.nextInt();
				sc.nextLine();
				// 관리자 / 일반 회원가입 분기
				if (ch2 == 1) {
					radmin.SignUp();
				} else if (ch2 == 2)	{
					ru.SignUp();
				} else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			} else if(ch == 2) { // 로그인 분기
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println("[1]관리자 로그인 [2]회원 로그인");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				int ch2 = sc.nextInt();
				sc.nextLine();
				if(ch2 == 1) {
					// 관리자 로그인 호출,
					adminId = adminLogin.Login();
				} else if (ch2 == 2) {
					// 사용자 로그인 호출
					userId = login.Login();
				} else {
					System.out.println("잘못된 입력입니다.");
					break;
				}
			} else if(ch == 3) {		
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}

	}

	// 관리자용 메서드
	public void mainTwo(String userId) {
		Scanner sc = new Scanner(System.in);
		ItemRegist ir = new ItemRegist(); // 상품 등록 클래스 객체 생성
		ItemPrintAdmin ipAdm = new ItemPrintAdmin();
		ShowUsers su = new ShowUsers(); // 회원 조회 클래스 객체 생성
		ShowOrders showOrder = new ShowOrders();
		int ch=0;
		while(ch != 6) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("[1]상품조회 [2]상품등록 [3]회원조회 [4]판매내역 조회 [5]총 매출액 조회 [6]관리 페이지 종료");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			ch = sc.nextInt();
			sc.nextLine();
			if (ch == 1) {
				System.out.println("상품조회 시작.");
				ipAdm.print();
			} else if (ch == 2) {
				System.out.println("상품등록 시작.");
				ir.regist();
			}else if (ch == 3) {
				System.out.println("회원조회 선택");
				su.userPrint();
			} else if (ch == 4) {
				System.out.println("주문내역 조회");
				showOrder.ShowOrderAdmin();
			} else if (ch == 5) {
				System.out.println("총 매출액 조회 선택");
				showOrder.ShowAllSaleCost();
			} else if (ch == 6) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
	// 일반 사용자용 메서드
	public void mainThree(String userId) {
		Scanner sc = new Scanner(System.in);
		ItemPrint ip = new ItemPrint();   // 상품 조회 클래스 객체 생성
		Purchase pc = new Purchase(); // 상품 구매 객체
		String loggedInUserID = userId;	// 매개변수로 받아온 값 새로운 변수에 저장
		int ch=0;
		//		    String loggedInUserID = userId; // 현재 로그인된 사용자의 ID를 저장할 변수
		//		    System.out.println("로그인 된 id" + loggedInUserID);
		while(ch != 5) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("[1]상품조회 [2]상품구매 [3]주문내역조회 [4]고객 페이지 종료");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			ch = sc.nextInt();
			sc.nextLine();
			if(ch == 1) {
				System.out.println("상품조회 선택");
				ip.print();
			} else if (ch == 2) {
				System.out.println("상품구매 선택");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.printf("상품명 입력\n : ");
				String iName = sc.nextLine();
				System.out.printf("구매 수량 입력\n : ");
				int iQuan = sc.nextInt();
				sc.nextLine();	
				pc.buy(userId, iName, iQuan);
			} else if (ch == 3) {
				System.out.println("주문내역 조회");
				ShowOrders showOrder = new ShowOrders();
				// 로그인 시에 사용된 ID를 받아와서 해당 ID로 주문한 내역을 조회
				showOrder.ShowOrder(loggedInUserID);
			}
			else if (ch == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
