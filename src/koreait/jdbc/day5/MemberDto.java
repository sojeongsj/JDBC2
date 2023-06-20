package koreait.jdbc.day5;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class MemberDto {
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
}
