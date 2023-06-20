package koreait.jdbc.day3;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoTest {
	public static void main(String[] args) {
		
		StudentDao dao = new StudentDao();
		System.out.println("1. Insert 테스트");
		System.out.println("2023009 김땡구 17 강남구 - 데이터 입력");
		StudentDto dto = new StudentDto("2023009","김땡구",17,"강남구");
		try {
			int count = dao.insert(dto);
			System.out.println("학생 등록" + count + "건 입력 성공");
			System.out.println("입력 결과 조회 : " + dao.selectOne(dto.getStuno()));
		} catch(SQLException e) {
			System.out.println("예외 - " +e.getMessage());
		}
		
		System.out.println("\n2. Update 테스트");
		System.out.println("2023009 김땡구를 16 용산구 - 데이터 수정");
		dto = new StudentDto("2023009","김땡구",16,"용산구");
		try {
			int count = dao.update(dto);
			System.out.println("학생 등록" + count + "건 정보 수정 성공");
			System.out.println("수정 결과 조회 : " + dao.selectOne(dto.getStuno()));
		} catch(SQLException e) {
			System.out.println("예외 - " +e.getMessage());
		}
		
		System.out.println("\n3. Delete 테스트");
		System.out.println("2023009 - 데이터 삭제");
		try {
			int count = dao.delete("2023009");
			System.out.println("학생 등록" + count + "건 삭제 성공");
			System.out.println("삭제 결과 조회 : " + dao.selectOne("2023009"));
		} catch(SQLException e) {
			System.out.println("예외 - " +e.getMessage());
		}
		
		System.out.println("\n4. SelectAll 테스트");
		System.out.println("학생 테이블의 모든 데이터 조회하여 출력");
		try {
			List<StudentDto> list = dao.selectAll();
			System.out.println(dao.selectAll());
			for(StudentDto stu : list) {
				System.out.println(stu);
			}
		}catch(SQLException e) {
			System.out.println("예외 - " +e.getMessage());
		}
	}

}
