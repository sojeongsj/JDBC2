package koreait.jdbc.day6;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.common.hash.Hashing;

import koreait.jdbc.day4.JCustomer;
import koreait.jdbc.day4.JCustomerDao;

public class LoginMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String id = null;
		String pwtemp = null;
		boolean isLogin = false;
		while (!isLogin) {
			System.out.print("아이디 입력 : ");
			id = sc.nextLine();
			System.out.print("비밀번호 입력 : ");
			pwtemp = sc.nextLine();
			String password = Hashing.sha256(). // 적용할 해시함수 실행
					hashString(pwtemp, StandardCharsets.UTF_8) // 평문, 인코딩 형식
					.toString();

			JCustomerDao2 dao = JCustomerDao2.getCustomerDao2();
			JCustomer user = null;
			try {
				user = dao.login(id, password);
				if (user != null) {
					System.out.print("로그인 성공! ");
					System.out.println(user.getName() + "님 환영합니다.");
					isLogin = true;
				} else {
					System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
				}
			} catch (SQLException e) {
				System.out.println("로그인 예외 : " + e.getMessage());
			}
		}
		sc.close();
	}
}
