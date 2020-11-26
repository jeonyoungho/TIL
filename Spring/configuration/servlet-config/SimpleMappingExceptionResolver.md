# SimpleMappingExceptionResolver

- Spring MVC에서 Controller단에 발생한 Exception의 종류에 따라 에러 처리 뷰로 이동<br>
- sample-servlet.xml<br>
~~~
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
  	<property name="defaultErrorView" value="exception/CommonCaseException"/>
  	<property name="exceptionMappings">
  		<props>
  			<prop key="org.egovframe.exception.ACaseException">exception/ACaseException</prop>
  			<prop key="org.egovframe.exception.BCaseException">exception/BCaseException</prop>
  		</props>
  	</property>
</bean>
~~~

- 다음과 같은 설정으로 인해 ACaseException이 발생하면 exception/ACaseException.jsp로 이동시켜주고 BCaseException이 발생하면 exception/BCaseException.jsp로 이동시켜준다.<br>
- 만약 명시된 exception이외에 다른 Excpetion이 발생할 경우 defaultErrorView에 설정된 화면으로 이동시켜준다.<br>
- 참고 https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte:ptl:exception_handling