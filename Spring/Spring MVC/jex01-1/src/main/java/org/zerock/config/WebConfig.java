package org.zerock.config;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class}; //RootConfig클래스 설정
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class}; //ServletConfig 클래스 설정
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; //스프링 MVC의 기본경로 '/'로 변경
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
	}

}
