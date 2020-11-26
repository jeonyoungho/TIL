# Spring Security

- css, js, image 등의 리소스 및 로그인 관련 페이지에 대해서 접근 해용 해줘야한다.<br>
- 이처럼 spring security를 적용 안할부분을 다음과 같이 지정한다.<br>
~~~
<security:http pattern="/resources/**" security="none" />
~~~

- 참고 https://iamksu.tistory.com/55