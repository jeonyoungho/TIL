package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.zerock.sample"}) //컴포넌트 스캔 패키지 설정
public class RootConfig {
	
}
