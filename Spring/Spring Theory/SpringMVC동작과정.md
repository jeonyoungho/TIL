# Spring MVC 동작과정

![2](https://user-images.githubusercontent.com/44339530/115183554-f499ba80-a116-11eb-9592-5404d245e173.png)<br>

- Spring MVC는 DispatcherServlet, View Resolver, Interceptor, Handler, View 등으로 구성되어있다.
- 이중에 DispatcherServlet이 가장 앞단의 front controller역할을 하며 가장 핵심적인 역할을 한다.
- Spring MVC의 구체적인 동작 과정은 다음과 같다.
    - 1)DispatcherServlet이 모든 웹 브라우저로부터의 요청을 받는다.
    - 2)DispatcherServlet은 HandlerMapping으로 부터 주어진 request를 처리할 수 있는 Handler객체를 가져온다.
    - 3)가져온 Handler를 실행(invoke) 시킬 수 있는 HandlerAdapter객체를 가져온다.
    - 4)만약 해당 Controller를 처리할 Handler객체에 적용할 interceptor가 존재한다면 모든 interceptor객체의 preHandle메소드를 호출한다.
    - 5)HandlerAdapter객체를 통해 실제 컨트롤러의 메소드를 실행 후 ModelAndView를 얻는다.
    - 6)만약 해당 Controller를 처리할 Handler객체에 적용할 interceptor가 존재한다면 모든 interceptor객체의 postHandle메소드를 호출한다.
    - 7)DispatcherServlet은 5번 과정에서 얻은 ModelAndView를 통해 view name을 ViewResolver에게 전달하여 응답에 필요한 View객체를 얻어온다.
    - 8)DispatcherServlet은 7번 과정에서 얻은 View객체에 5번 과정에서 얻은 ModelAndView의 Model을 파라미터로 넘겨주어 render메소드를 호출하여 페이지 렌더링을 수행한다.
    - 9)DispatcherServlet은 렌더링된 페이지를 response로 사용자에게 리턴한다.

- 참고한 블로그의 정리 내용(출처: https://jess-m.tistory.com/15)
~~~
1. 배포서술자에 DispatcherServlet 등록한다.

2. 웹 컨테이너가 뜰때 DispatcherServlet의 전략 빈들이 생성된다.

3. 실제 사용자의 Request가 들어온다.

4. 웹 컨테이너는 DispatcherServlet 객체를 생성하고 초기화 한다. (첫 호출시에만)

5. 웹 컨테이너가 Request, Response 객체를 생성한다.

6. 웹 컨테이너의 쓰레드 풀에서 쓰레드 하나를 할당하여 DispatcherServlet 실행.

7. request uri에 알맞은 핸들러(메소드)를 가져온다. (실제 핸들러를 매핑하는 전략은 DispatcherServlet에 주입된 HandlerMapping 구현체에 의해)
 
8. 핸들러를 실행시키기 위하여 핸들러 아답터를 가져온다. (핸들러 클래스 타입마다 적용시킬 아답터가 다르므로)

9. 인터셉터의 preHandle 실행

10. 실제 컨트롤러 핸들러(메소드) 실행  

11. 인터셉터의 postHandle 실행

12. 개발자가 설정한 View 에 알맞도록 렌더링을 실행하여 결과를 write

13. 웹 컨테이너가 response를 보내주고 유저 스레드 반환
~~~

#### 출처
- https://jess-m.tistory.com/15
- https://syundev.tistory.com/166
- https://galid1.tistory.com/526