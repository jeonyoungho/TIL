package kr.ac.hansung.cse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";//이제 컨트롤러에서 view의 이름을 리턴하는 대신 definition의 이름 리턴
	}
	
}
