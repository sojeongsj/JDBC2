package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class J05_StudentInsertMenu {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("학생 등록 메뉴입니다.");
		System.out.println("학생번호 입력시 0000입력은 종료입니다.");
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		String temp;
		int age;
			while(true) {
				try(
						Connection con = DriverManager.getConnection(url, user, password);	
						){
					String sql = "INSERT INTO TBL_STUDENT VALUES(?,?,?,?)";	
					PreparedStatement pstmt = con.prepareStatement(sql);
				System.out.print("학번 : ");
				temp = sc.nextLine();
				if(temp.equals("0000")) break;
				pstmt.setString(1, temp);
				System.out.print("이름 : ");
				temp = sc.nextLine();
				pstmt.setString(2, temp);
				System.out.print("나이 : ");
				age=Integer.parseInt(sc.nextLine());
				pstmt.setInt(3, age);
				System.out.print("주소 : ");
				temp = sc.nextLine();
				pstmt.setString(4, temp);	
				
				
				pstmt.execute();	//PreparedStatement 객체로 execute하면 SQL이 실행됨
				pstmt.close();
				
				} catch (Exception e) {		
					System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
					System.out.println("SQLException = url 또는 user 또는 password 가 잘못됐습니다.");
					System.out.println("오류메시지 = " + e.getMessage());	
					e.printStackTrace();		//Exception발생의 모든 원인을 cascade 형식으로 출력
				} 
				
			}
			System.out.println("종료합니다.");
		
		
	
	}
}