package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentUpdateMenu {
	public static void main(String[] args) {
		System.out.println(":::::학생 정보 수정 메뉴입니다.:::::");
		
		System.out.println("지정된 학번에 대해 나이와 주소를 수정할 수 있습니다.");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			updateStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
		
	}
	
	//반복없이 1개만 수정
	private static void updateStudent(Connection connection) throws Exception {
		Scanner sc = new Scanner(System.in);
		String add,age,stuno;
		String sql = "UPDATE TBL_STUDENT SET AGE = ?, ADDRESS = ? WHERE STUNO = ?";
		//문자열 안에 SQL문 ; 기호 넣으면 오류 
		System.out.println("학번 0000입력은 수정 취소입니다.");
		System.out.print("변경할 학번 : ");
		stuno = sc.nextLine();
		if(stuno.equals("0000")) {
			System.out.println("학생 정보 수정을 취소합니다.");
			sc.close();
			return;		//리턴에 값이 없을때는 단순하게 메소드 종료로 실행
		}
		System.out.print("변경할 나이 입력 : ");
		age = sc. nextLine();
		System.out.print("변경할 주소 입력 : ");
		add = sc.nextLine();
		
		try (
				PreparedStatement ps = connection.prepareStatement(sql);
				){
			//매개변수의 순서와 형식을 확인하고 전달하는 setXXXX 메소드 실행
			ps.setInt(1, Integer.parseInt(age));
			ps.setString(2, add);
			ps.setString(3, stuno);
//			ps.execute();		//insert,update,delete,select 모두 실행
			int count = ps.executeUpdate();	//리턴값은 반영된 행의 개수 ->새로운 메소드 써보기
			//insert, update, delete 실행
			System.out.println("학생 정보 수정 " + count + "건이 완료되었습니다.");
			System.out.println("등록 성공");
		} catch (SQLException e) {
			System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요");
		} catch(NumberFormatException e) {
			System.out.println("나이 입력이 잘못되었습니다. 정숫값을 입력해주세요.");
		}
		
		sc.close();
		
	}
}
