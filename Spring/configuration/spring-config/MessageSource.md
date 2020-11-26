# 다국어 처리를 위한 MessageSource

- 'messageSource'가 이름인 빈 객체 정의
~~~
<bean id="messageSource"
     class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basename">
        <value>message.label</value>
    </property>
</bean>
~~~
 
- basename 프로퍼티의 값은 메시지를 로딩할 때 사용할 ResourceBundle 의 베이스 이름 (패키지를 포함한 전체 이름이어야 함.)<br>
- 위와 같은 설정은 message.label 값은 message 패키지에 있는 label 프로퍼티 파일로부터 메시지를 가져옴<br>

- message패키지 밑에 label_ko.Properties와 label_en.Properties파일을 생성 후 다음과 같이 입력
~~~
< label_ko.Properties >
hi=안녕하세요
~~~
<br>
~~~
< label_ko.Properties >
hi=Hello
~~~

- 그리고 입력한 메시지의 Locale에 따라 다음과 같이 출력
~~~
messageSource.getMessage("hi", "default message", Locale.KOREA )); // 안녕하세요 출력
messageSource.getMessage("hi", "default message", Locale.US)); // hi 출력
~~~

- 참고<br>
· http://egloos.zum.com/ultteky/v/10520923<br>
· https://javaslave.tistory.com/69<br>