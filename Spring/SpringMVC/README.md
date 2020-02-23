<h2> Java MVC Configuration </h2>
1.web.xml 및 spring디렉토리(servlet-context.xml , root-context.xml)삭제<br>
2.pom.xml에 mavenC-war-plugin 추가 및 스프링 버전, 컴파일 관련 버전 수정 후 Update Project<img width="844" alt="maven-war-plugin 추가" src="https://user-images.githubusercontent.com/44339530/74820272-18137a80-5345-11ea-83dc-df22819976c3.png">
3.config package 생성 후 RootConfig , WebConfig 클래스 추가<br>
· Java 설정을 이용하는 경우 xml대신 설정 파일을 직접 작성해야만한다.<br>
· @Configuration이라는 어노테이션을 이용해 해당 클래스의 인스턴스를 이용해서 설정 파일을 명시<br>
· Webconfig 클래스는 3개의 추상 메서드를 오버라이드 (getRootConfigClasses(), getServletConfigClasses(), getServletMappings()) 하도록 작성된다.<br>
· getRootConfigClasses() 는 root-context.xml을 대신하는 클래스를 지정<br><img width="844" alt="config 패키지생성" src="https://user-images.githubusercontent.com/44339530/74820285-1e095b80-5345-11ea-9696-ec302d1b4db5.png"><img width="844" alt="RootConfig" src="https://user-images.githubusercontent.com/44339530/74820288-1ea1f200-5345-11ea-9bf3-fec10b0c44a5.png"><img width="844" alt="Webconfig" src="https://user-images.githubusercontent.com/44339530/74820292-1f3a8880-5345-11ea-8269-297d0d610365.png"><br>
4.servlet-context.xml을 대신하는 별도의 ServletConfig.java 생성 ( 두 가지가 있으나 첫번째 사용)<br>
· @EnableWebMvc어노테이션과 WebMvcConfigurer인터페이스를 구현하는 방식<br>
· @Configuration과 WebMvcConfigurationSupport클래스를 상속하는 방식 - 일반 @Configuration우선 순위가 구분되지 않는 경우에 사용<br>
<img width="844" alt="ServletConfig생성" src="https://user-images.githubusercontent.com/44339530/75110136-802bce80-566d-11ea-84f8-30e47c3552ff.png"><br>
5.WebConfig클래스 설정 변경<br>
<img width="844" alt="스크린샷 2020-02-23 오후 6 54 03" src="https://user-images.githubusercontent.com/44339530/75110158-e7498300-566d-11ea-80b8-87de283336f8.png"><br>
6.실행결과<br>
<img width="844" alt="스크린샷 2020-02-23 오후 7 12 32" src="https://user-images.githubusercontent.com/44339530/75110347-76f03100-5670-11ea-8b4a-b26500e77f86.png"><br>

<h2> Spring MVC Controller </h2>
· 스프링MVC의 Controller는 기본적으로 Java Beans 규칙에 맞는 객체는 다시 화면으로 객체를 전달한다. 반면에 기본 자료형의 경우는 파라미터로 선언하더라도 기본적으로 화면까지 전달되지 않는다.<br>
1.SampleDTO.class<br>
<img width="844" alt="SampleDTO" src="https://user-images.githubusercontent.com/44339530/75111058-0d285500-5679-11ea-872e-6e50d486e8bf.png"><br>
2.SampleController.class<br>
<img width="844" alt="SampleController" src="https://user-images.githubusercontent.com/44339530/75111057-0994ce00-5679-11ea-9399-01847b984640.png"><br>
3.ex04.jsp<br>
<img width="844" alt="ex04" src="https://user-images.githubusercontent.com/44339530/75111090-7314dc80-5679-11ea-9bb9-990dfb71cc49.png"><br>
4.http://localhost:8080/ex01/sample/ex04?name=aaa&age=11&page=9 호출 후 화면
<img width="844" alt="호출후화면" src="https://user-images.githubusercontent.com/44339530/75111083-4f519680-5679-11ea-9f07-26547b72d8a1.png"><br>
--> SampleDTO만 전달 되고 int 타입(기본 자료형)은 전달 되지 않는다<br>

<h2> @ModelAttribute - 강제로 전달받은 파라미터를 Model에 담아서 전달하도록 하는 어노테이션 </h2>
1.SampleController.class에 어노테이션추가<br>
<img width="844" alt="controller" src="https://user-images.githubusercontent.com/44339530/75111162-f46c6f00-5679-11ea-8a70-b5fc96f78ee6.png"><br>
2.결과 화면<br>
<img width="844" alt="result" src="https://user-images.githubusercontent.com/44339530/75111155-f20a1500-5679-11ea-80ce-066092bf9c50.png"><br>

<h2> RedirectAttributes - 일회성으로 데이터를 전달하는 용도로 사용 </h2>

<h2> Controller의 리턴 타입 </h2>    
① string - jsp를 이용하는 경우에는 jsp파일의 경로와 파일이름을 나타내기 위해서 사용<br>

② void - 호출하는 URL과 동일한 이름의 jsp를 의미<br>
1.SampleController메소드추가
<img width="844" alt="ex05" src="https://user-images.githubusercontent.com/44339530/75111390-7c537880-567c-11ea-85cb-2e5a67ab33ab.png"><br>
2.실행결과<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 40 21" src="https://user-images.githubusercontent.com/44339530/75111403-bd4b8d00-567c-11ea-94d2-7be728ccd040.png">
<br>
--> servlet-context.xml의 설정과 같이 맞물려 URL경로를 View로 처리하기 때문<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 41 26" src="https://user-images.githubusercontent.com/44339530/75111415-e3712d00-567c-11ea-9e4a-ffeea23d244b.png"><br>

③ VO,DTO 타입 - 주로 JSON 타입의 데이터를 만들어서 반환하는 용도로 사용<br>
1.pom.xml에 jackson-databind 라이브러리 추가<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 32 37" src="https://user-images.githubusercontent.com/44339530/75111298-a48ea780-567b-11ea-906e-5599e25469c3.png"><br>
2.SampleController에 메서드 추가<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 35 26" src="https://user-images.githubusercontent.com/44339530/75111337-0c44f280-567c-11ea-9ae3-1cf485142388.png"><br>
3.spring mvc는 자동으로 브라우저에 JSON타입으로 객체를 변환해서 전달함<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 36 27" src="https://user-images.githubusercontent.com/44339530/75111352-2f6fa200-567c-11ea-9ded-7d5605d88283.png"><br>

④ ResponseEntity - response 할 때 Http  헤더 정보와 내용을 가공하는 용도로 사용<br>
1.SampleController에 메서드 추가<br>
<img width="844" alt="ex07" src="https://user-images.githubusercontent.com/44339530/75111517-21bb1c00-567e-11ea-9f82-c25653af85b1.png"><br>
2.실행결과 - ResponseEntity는 HttpHeaders객체를 같이 전달하고 원하는 HTTP헤더 메시지를 가공할 수 있음<br>
<img width="844" alt="스크린샷 2020-02-23 오후 8 50 23" src="https://user-images.githubusercontent.com/44339530/75111516-2089ef00-567e-11ea-8348-82487ed6c964.png"><br>
⑤ Model, ModelAndView - Model로 데이터를 반환하거나 화면까지 같이 지정하는 경우에 사용(최근엔 사용X)<br>
⑥ HttpHeaders - 응답에 내용 없이 Http 헤더 메시지만 전달하는 용도로 사용<br>




