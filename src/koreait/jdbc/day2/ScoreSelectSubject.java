package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreSelectSubject {
	public static void main(String[] args) {
		Connection con = OracleUtility.getConnection();
		System.out.println("과목 조회");
		ScoreSelectWithSubject(con);
		OracleUtility.close(con);
	}
	
	private static void ScoreSelectWithSubject(Connection con) {
		Scanner sc = new Scanner(System.in);
		String subject;
		String sql="SELECT * FROM TBL_SCORE WHERE SUBJECT = ?";
		//조건절에 사용하는 컬럼이 기본키와 유니크일 때는 0 또는 1개행이 조회됨 -> rs.next()를 if에 사용
		//					 기본키와 유니크가 아닐때는 0~n개 행이 조회됨 -> rs.next()를 where에 사용
		System.out.print("조회할 과목 입력 : ");
		subject = sc.nextLine();
		try(
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print("학번 : " + rs.getString(1)+"\t");
				System.out.print("과목 : " + rs.getString(2)+"\t");
				System.out.print("점수 : " + rs.getInt(3)+" \t");
				System.out.print("교사 : " + rs.getString(4)+"\t");
				System.out.println("학기 : " + rs.getString(5));
			}
			selectCount(con, subject);
			//sql="SELECT COUNT(*) FROM TBL_SCORE WHERE SUBJECT = ?";
			//참고 : 입력한 과목의 건수를 조회할 수 있음
		} catch(SQLException e) {
			System.out.println("데이터 조회에 문자가 생겼습니다. 상세내용 - " + e.getMessage());
		}
		sc.close();
	}
	
	private static void selectCount(Connection con, String subject) {
		String sql="SELECT COUNT(*) FROM TBL_SCORE WHERE SUBJECT = ?";
		//COUNT와 같은 함수 결과는 항상 행 1개, 컬럼 1개
		try(
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			if(rs.next()) {		//다른 조회문과 다르게 if문 안써도됨. rs.next()만 단독으로
				count = rs.getInt(1);
			}
			System.out.println("과목 " + subject + " "+ count +"건이 조회되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("데이터 조회에 문자가 생겼습니다. 상세내용 - " + e.getMessage());
		}
		
	}
}
