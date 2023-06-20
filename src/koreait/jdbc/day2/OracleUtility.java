package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//select 부터는 지금 만든 유틸리티 클래스로 Connection을 만들어 줍니다.
public class OracleUtility {
	//Connection 생성하여 제공, 리소스 해제 메소드를 작성
	
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419"; 
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 및 사용에 문제가 생겼습니다. : " + e.getMessage());
		}
		return con;		//생성된 Connection 객체 리턴
	}
	
	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();		//con이 null객체라면 close에서 오류 -> 조건식 추가
				System.out.println("연결 종료");
			}
			else {
				System.out.println("Connection이 null입니다.");
			}
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 종료 오류 :" + e.getMessage());
		}
	}
}
