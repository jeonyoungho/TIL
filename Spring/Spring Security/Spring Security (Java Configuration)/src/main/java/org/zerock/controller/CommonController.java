package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth,Model model) {
		model.addAttribute("msg","Access Denied");
	}
	
	@GetMapping("/customLogin")
	public void login(String error,String logout,Model model) {
		
		if(error != null) {
			model.addAttribute("errorMsg","아이디와 비밀번호가 일치하지 않습니다.");
		}
		
		if(logout != null) {
			model.addAttribute("logoutMsg","정상적으로 로그아웃에 성공하셨습니다.");
		}
		
		
	}
	
	@GetMapping("/customLogout")
	public void logout() {
		log.info("custom logout");
	}
}

