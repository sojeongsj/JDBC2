package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class J03_InsertDMLTest {
	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";

		try (Connection con = DriverManager.getConnection(url, user, password);) {
			System.out.println("연결상태 = " + con);
			if (con != null)
				System.out.println("오라클 데이터베이스 연결 성공! ");

			// DB연결 완료 후에 SQL 실행
			// insert SQL 작성 : 제약조건(기본키 STUNO) 위반되지않는 값으로 입력하기
			String sql = "INSERT INTO TBL_STUDENT VALUES('2023012','김땡땡',17,'경기도')";

			// PreparedStatement 객체를 생성하면서 실행할 SQL을 설정
			// PreparedStatement 객체는 Connection 객체 메소드로 만듬. Connection 구현 객체는
			// DBMS 종류에 따라 생성되고 PreparedStatement 객체도 그에 따라 구현객체가 결정
			PreparedStatement pstmt = con.prepareStatement(sql);
			// oracle.jdbc.driver.OraclePreparedStatementWrapper 클래스로 객체가 생성
			System.out.println("pstmt 객체의 구현 클래스 : " + pstmt.getClass().getName());

			// PreparedStatement() 메소드는 객체를 생성해서 리턴
			pstmt.execute(); // PreparedStatement 객체로 execute하면 SQL이 실행됨
			pstmt.close();

		} catch (Exception e) {
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됐습니다.");
			System.out.println("오류메시지 = " + e.getMessage());
			e.printStackTrace(); // Exception발생의 모든 원인을 cascade 형식으로 출력
		}

	}
}
/*
 * Statement 인터페이스는 SQL 쿼리 처리와 관련된 방법을 정의 객체는 SQL 쿼리문을 데이터베이스에 전송. Connection
 * 객체를 통해서 만듬 PreparedStatement는 Statement의 자식 인터페이스 특징은 SQL을 먼저 컴파일하고 SQL 실행에
 * 필요한 값은 실행할 때 매개변수로 전달하는 방식
 * 
 */
