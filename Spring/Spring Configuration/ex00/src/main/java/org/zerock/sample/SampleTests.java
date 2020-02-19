package org.zerock.sample;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //pom.xml의 junit과 spring-test의 <scope>test</scope>를 지워야 import됨
//@RunWith 테스트 코드가 스프링을 실행하는 역할을 할 것이라고 명시
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration 지정한 클래스나 문자열을 이용해서 필요한 객체들을 스프링내에 객체로 등록
@Log4j
// Lom4j Lombok을 이용해서 로그를 기록하는 Logger변수로 생성 별도의 Logger객체의 선언 없이도 바로 사용가능
// Log에대한 설정 -> src/main/resources , src/test/resources
//Junit 은 반드시 4.10이상
//테스트 작업은 프로젝트 초기에 설정해두고 사용하는 습관을 가지는 것이 좋다.
public class SampleTests {
	@Setter(onMethod_ = { @Autowired })
	private Restaurant restaurant;
	@Test //Junit 테스트 대상임을 표시
	public void testExist() {
		assertNotNull(restaurant); //restaurant변수가 null이 아니여야만 테스트가 성공한다는 의미
		log.info("restaurant");
		log.info("----------------");
		log.info(restaurant.getChef());
	}
}
