package com.mjart.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController { // login?error
	
//	@RequestMapping(value="/login")
//	public String showLogin(@RequestParam(value="error",required=false) String error,
//			@RequestParam(value="logout",required=false) String logout,
//			Model model) {
//	
//		if(error != null) {
//			model.addAttribute("errorMsg", "Invalid username and password");		
//		}
//		
//		if(logout != null) {
//			model.addAttribute("logoutMsg","You have been logged out successfully");
//		}
//		
//		return "login";
//	
//	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin(@RequestParam(value="error",required=false) String error,
			@RequestParam(value="logout",required=false) String logout,
			Model model) {
	
		if(error != null) {
			model.addAttribute("errorMsg", "Invalid username and password");		
		}
		
		if(logout != null) {
			model.addAttribute("logoutMsg","You have been logged out successfully");
		}
		
		return "login";
	
	}
	
}
