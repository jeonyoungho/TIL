package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data //Lombok의 setter를 생성하는 기능과 생성자, toString등을 자동으로 생성하도록 @Data 어노테이션을 이용
public class Restaurant {

	@Setter(onMethod_ = @Autowired) //@Setter -> setChef()를 자동으로 컴파일 시 생성
	private Chef chef;              //onMethod_ -> setChef()에 @Autowired를 추가
	
	
	
}
