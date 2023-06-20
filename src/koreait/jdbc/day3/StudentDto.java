package koreait.jdbc.day3;

public class StudentDto {
	private String stuno;
	private String name;
	private int age;
	private String address;
	
	//DTO : Data Transfer Object  가변객체로 데이터를 객체간에 전달하기 위한 목적
	//VO : Value Object 는 불변객체로 equals 와 hashcode 재정의를 하여 set 또는 map 객체에서 활용
	//메소드의 매개변수 또는 리턴 타입으로 사용하기 위해 
	//테이블의 컬럼 구성과 같은 클래스를 정의하여 값을 저장하기 위한 것
	
	public StudentDto() {
		
	}
	
	public StudentDto(String stuno, String name, int age, String address) {
		super();
		this.stuno=stuno;
		this.name=name;
		this.age=age;
		this.address=address;
	}

	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "학번 : " + stuno + "   이름 : " + name + "   나이 : " + age + "   주소 : " + address + "\n";
	}
	
	
}
