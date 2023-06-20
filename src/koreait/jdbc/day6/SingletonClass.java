package koreait.jdbc.day6;

//싱글톤 패턴 : 객체를 만들때 오직 1개만 만들어서 사용하는 코드 패턴
//웹에서는 메소드만 있는 DAO와 같은 클래스를 싱클톤으로 작성
//static은 정적인 영역 . DAO를 정적인 영역에 만들지는 않음
public class SingletonClass {
	
	//1. 미리 객체를 만들어서 전역변수(필드)로 선언해 둠. private
	private static SingletonClass single = new SingletonClass();
	//SingletonClass 객체는 오직 한번만 만듬
	
	//2. 생성자는 private. 외부의 다른 클래스는 new SingletonClass() 실행 못함
	private SingletonClass() {
		
	}

	//3. 외부의 다른 클레스에서 객체를 요청할 때 리턴해주는 메소드
	public static SingletonClass getInstance() {
		return single;
	}
	
}
