package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

//일반적으로 의존성 주입은 Chef를 클래스가 아닌 인터페이스로 설계하는 것이 좋다. 테스트이기에 일반 클래스로 작성
@Component
@Data
public class Chef {

}
