package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;


//상품 목록 보기
	public class JProductDao {
	public List<JProduct> selectAll() throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM J_PRODUCT";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<JProduct> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new JProduct(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		ps.close();
		con.close();
		return results;
	}
	
	//pname 상품명으로 검색하기
	public List<JProduct> selectpn(String pname) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM J_PRODUCT WHERE PNAME LIKE '%' || ? || '%'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		List<JProduct> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new JProduct(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		ps.close();
		con.close();
		return results;
	}
	
	//금액대 입력 
	//0 - 0~9999원
	//1만원대 - 10000~19999원
	//2만원대 - 20000~29999원
	public List<JProduct> selectpr(int price) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM J_PRODUCT WHERE PRICE BETWEEN ? AND ?	";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, price*10000);
		ps.setInt(2, (price*10000)+9999);
		ResultSet rs = ps.executeQuery();
		List<JProduct> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new JProduct(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		ps.close();
		con.close();
		return results;
	}
	
	
	
	
}
