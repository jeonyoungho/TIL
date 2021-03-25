package com.example.practice.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	// private static final Logger logger = LoggerFactory.getLogger("com.example.practice.controller.HomeController");
	// private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		
		// trace -> debug -> info -> warn -> error
		logger.trace("trace level: Welcome home! The client locale is {}", locale);
		logger.debug("debug level: Welcome home! The client locale is {}", locale);
		logger.info("info level: Welcome home! The client locale is {}", locale);
		logger.warn("warn level: Welcome home! The client locale is {}", locale);
		logger.error("error level: Welcome home! The client locale is {}", locale);
		
		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();
		
		logger.info("Request URL: {}, Client IP: {}", url, clientIPaddress);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
