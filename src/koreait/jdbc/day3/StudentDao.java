package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;
//DAO : Data Access(접근-읽기와 쓰기) Object
//SQL 실행 메소드를 모아놓은 클래스

/*
 * StudentDao 내용 요약
 * Insert, Update는 SQL 파라미터에 전달한 데이터타입을 DTO
 * Delete는 원시형 또는 String
 * Delete는 SQL의 조건절 컬럼이 여러개일때는 DTO가 될 수 있음. MAP도 종종 사용
 * 
 * Insert, Update, Delete는 정수 리턴값으로 반영된 행의 개수를 전달
 * 
 * selectOne : SQL 파라미터에 전달할 데이터를 메소드 인자로 함
 * selectAll : 파라미터 없으며 여러개의 행을 저장할 객체는 List타입
 */


public class StudentDao {
	
	
	
	//나중에 db를 쉽게 코딩하기 위한 프레임워크를 사용하면 Exception 처리 안해도됨
	public int insert(StudentDto student) throws SQLException{
		Connection con = OracleUtility.getConnection();
		
		String sql = "INSERT INTO TBL_STUDENT VALUES(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		con.close();
		return result;
	}
	
	public int update(StudentDto student) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "UPDATE TBL_STUDENT SET AGE = ?, ADDRESS = ? WHERE STUNO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		
		int result = ps.executeUpdate();
		
		ps.close();
		con.close();
		return result;
	}
	
	public int delete(String stuno) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "DELETE FROM TBL_STUDENT WHERE STUNO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, stuno);
		int result = ps.executeUpdate();
		
		ps.close();
		con.close();
		return result;
	}
	
	public StudentDto selectOne(String stuno) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM TBL_STUDENT WHERE STUNO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, stuno);
		ResultSet rs = ps.executeQuery();
		StudentDto result= null;
		if(rs.next()) {
			result = new StudentDto(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			//return new StudentDto(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
		}
		return result;
	}
	
	//SELECT 모두 조회 - 조회결과 값들을 List객체로 리턴. 메소드에서 조회된 결과만을 리턴
	//출력 등 기타 기능들은 이 메소드를 사용한느 프로그램에서 구현
	public List<StudentDto> selectAll() throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT * FROM TBL_STUDENT";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<StudentDto> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new StudentDto(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
		}
		return results;
	}
}
