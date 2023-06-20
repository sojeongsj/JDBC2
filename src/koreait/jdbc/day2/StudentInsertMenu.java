package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsertMenu {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		System.out.println("::::::::::::::::::::학생 등록 메뉴입니다.::::::::::::::::::::");

		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			insertStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main

	private static void insertStudent(Connection connection) throws  Exception {
		Scanner sc = new Scanner(System.in);
		//4개의 입력값 그리고 파라미터 값 변수는 클래스를 정의해서 변경할 예정
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		String stuno, name, temp,add;
		int age;
		int count = 0;
		PreparedStatement ps = connection.prepareStatement(sql);
		while (true) {
			System.out.println("학생번호 입력시 0000입력은 종료입니다.");
			System.out.print("학번을 입력하세요 >>> ");
			stuno = sc.nextLine();

			if (stuno.equals("0000")) {
				System.out.println("학생 등록을 중지합니다.");
				break;
			}
			

			System.out.print("이름을 입력하세요 >>> ");
			name = sc.nextLine();
			

			System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
			temp = sc.nextLine();
												//sc.nextLine();

			System.out.print("주소를 입력하세요 >>> ");
			add = sc.nextLine();
			
			
			//매개변수 전달과 실행을 모아놓기 -> 이후에 메소드를 따로 정의할 예정
			try {
				age = Integer.parseInt(temp);
				ps.setString(1, stuno);
				ps.setString(2, name);
				ps.setInt(3, age);
				ps.setString(4, add);
				count++;
				ps.execute();
				System.out.println("등록 성공");
			} catch (SQLException e) {
				System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요");
			} catch(NumberFormatException e) {
				System.out.println("나이 입력이 잘못되었습니다. 정숫값을 입력해주세요.");
			}

			System.out.println("------------------------------------------------");
		}//while
		ps.close();
		sc.close();
		System.out.println("입력한 학생정보 "+count+"개가 성공적으로 등록되었습니다..");

	}
}
