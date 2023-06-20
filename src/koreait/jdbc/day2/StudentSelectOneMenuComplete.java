package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {
	public static void main(String[] args) {
		Connection con = OracleUtility.getConnection();
		System.out.println("::::: 학생을 학번으로 조회하는 메뉴 :::::");
		selectOneStudent(con);
		
		OracleUtility.close(con);
	}

	private static void selectOneStudent(Connection con) {
		Scanner sc = new Scanner(System.in);
		String stuno;
		String sql="SELECT * FROM TBL_STUDENT WHERE STUNO = ?";
		System.out.println("조회할 학번 입력 : ");
		stuno = sc.nextLine();
		try(
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setString(1, stuno);

			ResultSet rs = ps.executeQuery();	//select 실행하기
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());

			if(rs.next()) {		//주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있음
			//getXXXX 메소드는 rs가 가리키는 현재 행의 컬럼값을 가져오기 함
			System.out.println("학번 : " + rs.getString(1));
			System.out.println("학번 : " + rs.getString("stuno")); //인덱스 대신 컬럼명으로 함
			System.out.println("이름 : " + rs.getString(2));
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("나이 : " + rs.getInt(3));
			System.out.println("나이 : " + rs.getInt("age"));
			System.out.println("주소 : " + rs.getString(4));
			System.out.println("주소 : " + rs.getString("address"));
			}
			else {
				System.out.println("조회 결과가 없습니다.");
			}
			System.out.println("다음 조회 결과 행이 또 있을까요? " + rs.next());
		} catch(SQLException e) {
			System.out.println("데이터 조회에 문자가 생겼습니다. 상세내용 - " + e.getMessage());

		}
		sc.close();
	}
}
//모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행 출력
//과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스
