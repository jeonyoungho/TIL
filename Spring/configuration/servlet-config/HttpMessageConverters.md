# HttpMessageConverters

- 컨트롤러에서 넘어온 데이터(JSON 같은)를 messageConverter로 사용하기 위해 사용<br>

~~~
<mvc:annotation-driven>
	<!--<mvc:message-converters>-->
		<!--<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">-->
			<!--<property name="supportedMediaTypes" value="application/json" />-->
		<!--</bean>-->
	<!--</mvc:message-converters>-->
</mvc:annotation-driven>
~~~


- 참고 https://devjackie.tistory.com/entry/spring-32-이상에서-사용할-수-있는-messageConverter-방법 [devJackie]