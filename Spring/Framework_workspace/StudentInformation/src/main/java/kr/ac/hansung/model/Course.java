package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//나중에 객체를 출력할때 사용!
public class Course {
	

	private int year;
	

	private int semester;
	
	@Size(min=0, max=10, message="code must be between 0 and 10 chars")
	@NotEmpty(message="code cannot be empty")
	private String code;
	
	@Size(min=0, max=20, message="coursename must be between 0 and 20 chars")
	@NotEmpty(message="coursename cannot be empty")
	private String coursename;

	@NotEmpty(message="division cannot be empty")
	private String division;
	

	private int grade;
	
	
	
}
