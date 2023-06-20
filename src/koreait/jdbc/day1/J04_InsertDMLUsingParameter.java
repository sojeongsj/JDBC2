package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//ㅎ가생 성적처리 프로그램중에 새로운 학생을 등록(입력) 하는 기능 만들기(테이블에 insert실행)
public class J04_InsertDMLUsingParameter {

	public static void main(String[] args) {

		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		
		
		try(
			Connection	con = DriverManager.getConnection(url, user, password);
				) {
			System.out.println("연결상태 = " + con);
			if (con != null)
				System.out.println("오라클 데이터베이스 연결 성공! ");

			//DB연결 완료 후에 SQL 실행
			//insert SQL 작성 : 실행되는 값이 되도록 매개변수로 처리
			String sql = "INSERT INTO TBL_STUDENT VALUES(?,?,?,?)";	
			
			//PreparedStatement 객체를 생성하면서 실행할 SQL을 설정
			//PreparedStatement 객체는 Connection 객체 메소드로 만듬. Connection 구현 객체는 
			//DBMS 종류에 따라 생성되고 PreparedStatement 객체도 그에 따라 구현객체가 결정
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			//매개변수에 값 전달하기 : 매개변수의 데이터형식 순서 문자열, 문자열, 정수, 문자열
			//setXXXX 메소드의 첫번째 인자느느 매개변수의 인덱스, 두번재 인자는 값 
			pstmt.setString(1, "2023003");		//1번 인덱스에 "2023003" 대입
			pstmt.setString(2, "빵빵이");			//2번 인덱스에 "빵빵이" 대입
			pstmt.setInt(3, 22);				//3번 인덱스에 22 대입
			pstmt.setString(4, "강원도");			//4번 인덱스에 "강원도" 대입
			
			//PreparedStatement() 메소드는 객체를 생성해서 리턴
			pstmt.execute();	//PreparedStatement 객체로 execute하면 SQL이 실행됨
			pstmt.close();
			
			System.out.println("새로운 데이터가 정상적으로 입력되었습니다.");
	
			
			
		} catch (Exception e) {		
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됐습니다.");
			System.out.println("오류메시지 = " + e.getMessage());	
			e.printStackTrace();		//Exception발생의 모든 원인을 cascade 형식으로 출력
		} 
		
	}

}
/*
 * Statement 인터페이스는 SQL  쿼리 처리와 관련된 방법을 정의 
 * 객체는 SQL 쿼리문을 데이터베이스에 전송. Connection 객체를 통해서 만듬
 * PreparedStatement는 Statement의 자식 인터페이스
 * 특징은 SQL을 먼저 컴파일하고 SQL 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식
 * 
 */