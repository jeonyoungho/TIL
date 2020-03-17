package kr.ac.hansung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController { // login?error
	
	@RequestMapping("/login")
	public String showLogin(@RequestParam(value="error",required=false) String error,
			@RequestParam(value="logout",required=false) String logout,
			Model model) {
	
		if(error != null) { //에러가 발생했을시 모델에 errorMsg저장
			model.addAttribute("errorMsg", "Invalid username and password");		
		}
		
		if(logout != null) { //logout할시 모델에 logoutMsg저장
			model.addAttribute("logoutMsg","You have been logged out successfully");
		}
		
		return "login";
	
	}
	
	
}
