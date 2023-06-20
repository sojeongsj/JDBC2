package koreait.jdbc.day5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDto {
	
	private int custno;
	private String custname;
	private String grade;
	private long price;
	
	
}
