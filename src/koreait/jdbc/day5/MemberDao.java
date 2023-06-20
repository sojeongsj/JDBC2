package koreait.jdbc.day5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

//DAO에는 입력과 출력은 포함시키지 않음. 오직 어떤 형식의 데이터를 받아서
//어떤 SQL을 실행하여, 어떤 값을 리턴할 것인가를 중점을 두고 정의하면 됨
//DTO는 데이터를 저장하는 목적의 클래스, DAO는 어떤 동작을 할 것인지를 정의한 메소드
public class MemberDao {
	//싱글톤으로 만들어 보기
	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {		//메소드 이름은 getInstance외에 내용을 알 수 있는 이름으로 정하기
		return dao;
	}
	
	
	
	//회원 등록
	public int insertMem(MemberDto member) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "INSERT INTO MEMBER_TBL_02 VALUES (mem2seq.nextval,?,?,?,sysdate,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member.getCustname());
		ps.setString(2, member.getPhone());
		ps.setString(3, member.getAddress());
		ps.setString(4, member.getGrade());
		ps.setString(5, member.getCity());

		int result = ps.executeUpdate();

		ps.close();
		con.close();
		return result;
	}
	
	//selectOne 회원 1명 정보 가져오는거 
	public MemberDto selectOne(int custno) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM MEMBER_TBL_02 WHERE CUSTNO = ?";		//PK 조회 : 결과 행 0개 또는 1개
		PreparedStatement ps = con.prepareStatement(sql);	
		ps.setInt(1,custno);
		ResultSet rs = ps.executeQuery();
		MemberDto result = null;
		if(rs.next()) {
			result = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getString(6), rs.getString(7));
		}
		ps.close();
		con.close();
		return result;
	}
	
	//전체 회원 목록
	public List<MemberDto> selectAll() throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT custno, custname, phone, address, joindate,"
				+ "CASE WHEN grade = 'A' THEN 'VIP'"
				+ "	WHEN grade = 'B' THEN '일반'"
				+ "	WHEN grade = 'C' THEN '직원'"
				+ "	END AS grade\r\n"
				+ "	,city"
				+ "FROM MEMBER_TBL_02;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MemberDto> result = new ArrayList<>();
		while(rs.next()) {
			result.add(new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getDate(5), rs.getString(6), rs.getString(7)));
		}
		ps.close();
		con.close();
		return result;
	}
	
	//회원 정보 수정
	public int update(MemberDto member) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql = "UPDATE MEMBER_TBL_02 SET CUSTNO = ?, CUSTNAME = ?, PHONE = ? ADDRESS = ?,"
				+ "JOINDATE = ?, GRADE = ?, CITY = ?"
				+ "WHERE CUSTNO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member.getCustno());
		ps.setString(2, member.getCustname());
		ps.setString(3, member.getPhone());
		ps.setString(4, member.getAddress());
		ps.setDate(5, member.getJoindate());
		ps.setString(6, member.getGrade());
		ps.setString(7, member.getCity());
		ps.setInt(8, member.getCustno());
		
		int result = ps.executeUpdate();
		
		ps.close();
		con.close();
		
		return result;
	}
}
