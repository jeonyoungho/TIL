package kr.ac.hansung;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context
		= new ClassPathXmlApplicationContext("/kr/ac/hansung/conf/animal.xml");
		
		PetOwner person = (PetOwner)context.getBean("id_petOwner");
		person.play();
		
		context.close();
	}

}
