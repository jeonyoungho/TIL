package kr.ac.hansung.cse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context
		= new ClassPathXmlApplicationContext("/kr/ac/hansung/cse/conf/animal.xml");
		
		PetOwner person = (PetOwner)context.getBean("id_petOwner");
		
		person.play();
		
		context.close();
	}

}
