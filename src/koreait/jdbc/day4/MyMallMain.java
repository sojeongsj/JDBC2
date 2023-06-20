package koreait.jdbc.day4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		JCustomerDao cdao = new JCustomerDao();
		JCustomer customer = null;
		JProductDao pdao = new JProductDao();
		JBuyDao bdao = new JBuyDao();
		System.out.println("쇼핑몰에 오신걸 환영합니다.");
		String sel;
		boolean run = true;

		boolean isLogin = false; // 로그인 성공 여부 저장
		String customid = null;

		System.out.println("장바구니 담기와 상품 구매를 위해 로그인");
		while (!isLogin) {
			System.out.print("간편로그인 - 사용자 아이디 입력(0000 입력시 로그인 종료) >> ");
			customid = sc.nextLine();
			if (customid.equals("0000")) { // 반복 종료
				System.out.println("로그인을 종료합니다.");
				break;
			}
			try {
				customer = cdao.selectOne(customid);
				if (customer != null) {
					System.out.println(customer.getName() + "고객님 환영합니다!");
					isLogin = true; // 반복 종료
				} else
					System.out.println("입력하신 고객 ID는 존재하지 않습니다.");

			} catch (SQLException e) {
				System.out.println("상품 소개 예외 : " + e.getMessage());
			}
		}
		// 장바구니 담기는 로그인 상태인 경우에만 실행

		while (run) {
			System.out.println("선택 👉 1. 로그인 2. 상품소개 3. 상품검색 4. 가격대별 검색 "
					+ "5. 장바구니 담기 6. 구매내역보기 7. 프로그램 종료");
			sel = sc.nextLine();
			switch (sel) {
			case "1" :
				System.out.println("장바구니 담기와 상품 구매를 위해 로그인");
				if(isLogin) {
					System.out.println("이미 로그인을 하셨습니다.");
					break;
				}
				while (!isLogin) {
					System.out.print("간편로그인 - 사용자 아이디 입력(0000 입력시 로그인 종료) >> ");
					customid = sc.nextLine();
					if (customid.equals("0000")) { // 반복 종료
						System.out.println("로그인을 종료합니다.");
						break;
					}
					try {
						customer = cdao.selectOne(customid);
						if (customer != null) {
							System.out.println(customer.getName() + "고객님 환영합니다!");
							isLogin = true; // 반복 종료
						} else
							System.out.println("입력하신 고객 ID는 존재하지 않습니다.");

					} catch (SQLException e) {
						System.out.println("상품 소개 예외 : " + e.getMessage());
					}
				}
				break;
			case "2":
				System.out.println("상품 소개");
				try {
					List<JProduct> products = pdao.selectAll();
					for (JProduct product : products)
						System.out.println(product);
				} catch (SQLException e) {
					System.out.println("상품 소개 예외 : " + e.getMessage());
				}
				break;
			case "3":
				System.out.println("편리한 상품 구매를 위해 검색 하기");
				System.out.print("검색어 입력 : ");
				String pname = sc.nextLine();
				try {
					List<JProduct> products = pdao.selectpn(pname);
					for (JProduct product : products)
						System.out.println(product);
				} catch (SQLException e) {
					System.out.println("상품 소개 예외 : " + e.getMessage());
				}
				break;
			case "4" :
				System.out.println("가격대별 검색");
				System.out.println("0 : 1만원 이하 1 : 1만원대 2: 2만원대 ....");
				System.out.print("입력 : ");
				int ppr = Integer.parseInt(sc.nextLine());
				try {
					List<JProduct> sepr = pdao.selectpr(ppr);
					for (JProduct product : sepr)
						System.out.println(product);
				} catch (SQLException e) {
					System.out.println("상품 소개 예외 : " + e.getMessage());
				}
				break;
			case "5":
				// 4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 main에서 List 에 담기
				List<JBuy> carts = new ArrayList<>();
				if (isLogin) {
					while (true) {
						System.out.println("\n장바구니에 담기 합니다. 그만하려면 상품코드 0000 입력");
						System.out.print("구매할 상품코드 입력 >> ");
						String pcode = sc.nextLine();
						if (pcode.equals("0000"))
							break;
						System.out.print("구매할 수량 입력 >> ");
						int quantity = Integer.parseInt(sc.nextLine());
						carts.add(new JBuy(0, customid, pcode, quantity, null));

						System.out.print("장바구니에 담긴 상품을 결제하시겠습니까? Y/N ");
						if (sc.nextLine().toUpperCase().equals("Y")) {
							break;
						}

					}
					// Dao에서 carts를 전달받아 insert를 List만큼 반복하여 실행
					int count = bdao.insertMany(carts);
					if (count != 0) {
						System.out.println("장바구니 확인 : " + carts);
						System.out.println("\n 결제를 완료했습니다. 현재까지 " + customer.getName() + "회원님의 구매내역입니다.");
					}
				} else {
					System.out.println("로그인한 회원만 장바구니 담기를 실행할 수 있습니다.");
				}
				break;
			case "6":
				if (isLogin) {
					try {
						System.out.println(customer.getName() + " 회원님의 구매내역입니다. \n" + bdao.buymany(customid));
						System.out.println("총 구매 금액은 " + bdao.selectprice(customid) + "원 입니다.");
					} catch (SQLException e) {

						System.out.println("상품 구매 예외 : " + e.getMessage());
					}
				}
				else {
					System.out.println("로그인한 회원만 구매내역 보기를 실행할 수 있습니다.");
					
				}
				break;
			case "7":
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;

			}
		}
		sc.close();	
	}
}
