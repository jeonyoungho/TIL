package kr.ac.hansung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PetOwner {
	@Autowired //wired by type
	@Qualifier(value="qf_cat")
	private AnimalType animal;
	
	public void play() {
		animal.sound();
	}
}
//ctrl + space + o 자동임포트
//ctrl + space + f 포맷팅