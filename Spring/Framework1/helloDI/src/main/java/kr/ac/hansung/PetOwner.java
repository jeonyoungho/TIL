package kr.ac.hansung;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PetOwner {
	private AnimalType animal;
	
	public void play() {
		animal.sound();
	}
}
//ctrl + space + o 자동임포트
//ctrl + space + f 포맷팅