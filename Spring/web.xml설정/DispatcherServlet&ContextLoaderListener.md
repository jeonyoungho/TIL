<h1>DispathcerServlet & ContextLoaderListener</h1>

<h3>1.DispatcherServlet</h3>

- 해당 애플리케이션으로 들어오는 요청을 모두 처리<br>
![image](https://user-images.githubusercontent.com/44339530/98519899-b7eaad00-22b4-11eb-894c-0f653a8a78e8.png)<br>
- 사용자 URL 요청 -> DispatcherServlet (<url-pattern>을 통해 인식)-> HandlerMapping -> Controller(Servlet) -> ViewResolver -> View -> DispatcherServlet -> 사용자<br>

- DispatcherServlet은 모든 요청을 Servlet으로 보내어 처리하게 되므로 굳이 하지 말아야 할 요청도 보내는 경우가 있습니다. 이런 경우는 다음과 같이 예외 처리<br>
![image](https://user-images.githubusercontent.com/44339530/98520021-e10b3d80-22b4-11eb-84eb-1daa2d42b914.png)
<br>

<h3>ContextLoaderListener</h3>

- DispatcherServlet는 각각의 독립적으로 설정파일을 Load 하고 공유 불가<br>
- ContextLoaderListener를 사용하게 되면 Load시 여러 설정파일을 Load할 수 있음<br>
- ContextLoaderListener 와 DispatcherServlet 를 같이 사용할 경우 ContextLoaderListener는 부모 context가 되고 DispatcherServlet는 자식 context가 된다. 자식은 부모의 설정을 사용할 수 있다.<br>
- ※ 공통적인 부분(공통 빈) 설정은 ContextLoaderListener에 설정 하면 된다.<br>
![image](https://user-images.githubusercontent.com/44339530/98520351-40694d80-22b5-11eb-95c7-d169fe8e1c06.png)<br>

- TIP<br>
![image](https://user-images.githubusercontent.com/44339530/98520744-d69d7380-22b5-11eb-9976-aabbda54a8e7.png)
<br>
- 위와 같은 경우 DispatcherServlet 은 각가 별도의 webapplicationcontext를 생성하지만 두 context 는 독립적이므로 각각의 설정파일에서 생성한 빈을 서로 사용할 수 없다.(공유X)<br>
- 하지만 ContextLoaderListener를 통해 설정된 빈들은 두 context모두 공유하여 사용할 수 있다.<br>

- 참고<br>
https://sharedstoryit.tistory.com/entry/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%84%A4%EC%A0%95-%EA%B4%80%EB%A0%A8-%EC%9E%90%EB%A3%8CDispatcherServlet<br>
http://blog.daum.net/pokkacoffee/217<br>