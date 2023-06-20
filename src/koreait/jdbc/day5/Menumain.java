package koreait.jdbc.day5;

import java.sql.SQLException;

public class Menumain {
	public static void main(String[] args) throws SQLException {
		//우리가 만든 DAO를 정상적으로 동작하는지 확인하려면 데이터를 직접 주고
		//메소드를 호출해서 실행하는 main을 만들어야함
		//main 메소드는 응용프로그램을 만들기 위해서 작성하는 것으로 테스트용은 아님
		
		//특정 메소드가 잘 실행되는지 검증(테스트)하는 것을 단위테스트(unit test) 라고 함
		//자바에서 기본적으로 사용하는 단위테스트 라이브러리로 JUnit이 있음 
		//테스트 케이스는 테스트 메소드또는 이것을 포함하는 클래스 
		//테스트케이스는 test 폴더에 작성
		//테스트케이스는 빌드에 포함시키지 않음. src폴더 : 프로덕션 코드(빌드에 포함되는 코드)
		//우리는 하나의 DAO를 완성하기 위해 main까지 만들지 않고도 검증하는 목적으로 사용할 수 있음
		
		//싱글톤 Dao 사용하기
		MemberDao dao = MemberDao.getInstance();
		dao.insertMem(null);
	}
}
