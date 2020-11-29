package org.zerock.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessException) throws IOException, ServletException {
		System.out.println("Access Denied Handler");
		
		System.out.println("redirect...");
		System.out.println(request.getContextPath());
		
		response.sendRedirect(request.getContextPath() + "/accessError");
		
	}

}
