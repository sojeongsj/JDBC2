package koreait.jdbc.day4;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JBuy {
	private int buy_seq;
	private String customid;
	private String pcode;
	private int quantity;
	private Date buy_date;

}


//필드값이 모두 같으면 equals로 true 가 되도록 하고싶다.
//-> equals와 hashcode를 재정의 해야함(불변객체)
//=> VO . VO는 테스트 케이스에서 객체를 비교할 때 사용할 수 있음. 
//assertEquals 비교