package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSelectAll {
	public static void main(String[] args) {
		Connection con = OracleUtility.getConnection();
		System.out.println("모든 학생 조회");
		studentSelectAllMenu(con);
		OracleUtility.close(con);
	}
	
	private static void studentSelectAllMenu(Connection con) {
		String sql="SELECT * FROM TBL_STUDENT";
		try(
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				System.out.printf("학번 : %-10s\t이름 : %-1s\t나이 : %-4d\t주소 : %-7s\n",rs.getString(1),
						rs.getString(2),rs.getInt(3),rs.getString(4));
			}
			
			
		} catch(SQLException e) {
			System.out.println("데이터 조회에 문자가 생겼습니다. 상세내용 - " + e.getMessage());
		}
	}
}
