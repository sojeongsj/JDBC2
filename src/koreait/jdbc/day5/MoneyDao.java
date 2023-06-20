package koreait.jdbc.day5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;


public class MoneyDao {
	
	//매출 목록
	public List<MoneyDto> moneyAll() throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM myprice";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MoneyDto> result = new ArrayList<>();
		while(rs.next()) {
			result.add(new MoneyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
		}
		ps.close();
		con.close();
		return result;
	}
	
	//CREATE OR REPLACE VIEW myprice AS
	//SELECT mbt.custno,mbt.custname,
	//DECODE(GRADE,'A','VIP','B','일반','C','직원') AS GRADE
	//, "매출"
	//FROM MEMBER_TBL_02 mbt
	//	JOIN (
	//	SELECT CUSTNO, SUM(PRICE) AS "매출"
	//	FROM MONEY_TBL_02
	//	GROUP BY CUSTNO
	//	ORDER BY "매출" DESC
	//	) myt
	//ON myt.CUSTNO = mbt.CUSTNO;
}
