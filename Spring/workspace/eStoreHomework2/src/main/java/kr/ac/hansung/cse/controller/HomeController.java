package kr.ac.hansung.cse.controller;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	//프로그래밍시 application에선 slf4j에 대한 인터페이스만 활용하면 됨
	
	private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
	//private static final Logger logger= LoggerFactory.getLogger("kr/ac/hansung/cse/controller/HomeController");
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale) {
		
		//trace -> debug -> info -> warn -> error
		logger.info("info lelve : Welcome home! The client locale is {}",locale);
		
		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();
		
		logger.info("Request URL : {}, Client IP : {}",url,clientIPaddress);
		
		return "home";//이제 컨트롤러에서 view의 이름을 리턴하는 대신 definition의 이름 리턴
	}
	
}
