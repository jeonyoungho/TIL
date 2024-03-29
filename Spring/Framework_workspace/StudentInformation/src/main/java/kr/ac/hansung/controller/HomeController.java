package kr.ac.hansung.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // Component + 컨트롤러
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET) //root(/)로 요청오면 home.jsp를 리턴하여 호출
	public String home(Locale locale, Model model) {

		return "home"; // 뷰이름을 리턴 ,home.jsp
	}

}
