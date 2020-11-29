package org.zerock.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"org.zerock.controller","org.zerock.exception"})
public class ServletConfig implements WebMvcConfigurer{
	//WebMvcConfigurer인터페이스는 스프링 mvc와관련된 설정을 메서드로 오버라이드할때 사용
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resoureces/**").addResourceLocations("/resources/");
	}
	
	@Bean(name = "multipartResolver")//id속성을 반드시 부여해줘야함
	public CommonsMultipartResolver getResolver() throws IOException{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		//10MB
		resolver.setMaxUploadSize(1024 * 1024 * 10);
		//2MB
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
		//1MB
		resolver.setMaxInMemorySize(1024*1024);
		//temp upload
		resolver.setUploadTempDir(new FileSystemResource("/Users/jeon-yongho/Desktop"));
		
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	
	}
	
}
