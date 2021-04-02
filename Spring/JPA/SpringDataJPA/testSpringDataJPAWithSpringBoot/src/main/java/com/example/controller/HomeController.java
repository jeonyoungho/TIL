package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class); 
	
	@GetMapping("/")
    public String home(Model model) {
		
		logger.info("Info: Calling home()");
		logger.debug("Debug: Calling home()");
		logger.trace("Trace: Calling home()");
		
		model.addAttribute("message", "hello world");
		
        return "index";
    }

}
