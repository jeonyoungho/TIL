package kr.ac.hansung.csemall;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//나중에 객체를 출력할때 사용!
public class Offer {
	private int id;
	private String name;
	private String email;
	private String text;
	
	
}
