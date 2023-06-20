package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JCustomerDao {
	
	//회원 로그인
	public JCustomer selectOne(String custom_id) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM J_CUSTOM WHERE CUSTOM_ID = ?";		//PK 조회 : 결과 행 0개 또는 1개
		PreparedStatement ps = con.prepareStatement(sql);	
		//Statement : SQL, Prepared : SQL 이 미리 컴파일되어 준비된
		//PreparedStatement 는 Statement 인터페이스와 비교할 수 있음
		//Statement 인터페이스 : SQL 실행에 필요한 데이터를 동시에 포함시켜서 컴파일을 함
		ps.setString(1, custom_id);
		//준비된 SQL에 파라미터 전달하여
		ResultSet rs = ps.executeQuery();
		//select 쿼리 진행
		JCustomer result= null;
		if(rs.next()) {
			result = new JCustomer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
		}

		ps.close();
		con.close();
		return result;
	}
	//모두 조회
	public List<JCustomer> selectAll() throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM J_CUSTOM";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<JCustomer> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new JCustomer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5)));
		}
		ps.close();
		con.close();
		return results;
		
	}
	
}
