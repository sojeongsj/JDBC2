package koreait.jdbc.day4;

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

public class JProduct {
	private String pcode;
	private String category;
	private String pname;
	private int price;
}
