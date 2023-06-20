package koreait.jdbc.day6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;
import koreait.jdbc.day4.JCustomer;

public class JCustomerDao2 {
	
	private static JCustomerDao2 dao = new JCustomerDao2();
	private JCustomerDao2() {}
	public static JCustomerDao2 getCustomerDao2() {
		return dao;
	}
	
	public JCustomer login(String id, String password) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "SELECT CUSTOM_ID, NAME FROM J_CUSTOM WHERE CUSTOM_ID = ? AND PASSWORD = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		JCustomer result = null;
		if(rs.next()) {
			result = JCustomer.builder()
					.custom_id(rs.getString(1))
					.name(rs.getString(2))
					.build();
		}
		
		return result;	//result가 null이 아니면 로그인 성공
	}
	
	//LoginMain에서 사용자에게 아이디 >> 패스워드 >> 로그인 성공 or 아이디 또는 패스워드가 존재하지 않습니다. 
	
}
