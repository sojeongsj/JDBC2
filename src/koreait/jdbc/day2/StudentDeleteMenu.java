package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {
	public static void main(String[] args) {
		System.out.println("학생 정보 삭제 메뉴입니다.");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		try (
		Connection con = DriverManager.getConnection(url, user, password);		
				){
			deleteStudent(con);
		} catch(Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
		
	}
	
	//@SuppressWarnings("resource") resource 관련된 오류는 출력x
	private static void deleteStudent(Connection connection) throws Exception{
		Scanner sc = new Scanner(System.in);
		String stuno;
		String sql = "DELETE FROM TBL_STUDENT WHERE STUNO = ?";
		System.out.print("삭제할 학생의 학번을 입력 : ");
		stuno=sc.nextLine();
		
		try (
			PreparedStatement ps = connection.prepareStatement(sql);	
				){
			ps.setString(1, stuno);
			int count = ps.executeUpdate();
			System.out.println("성공적으로 "+ count +"건의 삭제가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요" + e.getMessage());
		}
		sc.close();
	}
}
