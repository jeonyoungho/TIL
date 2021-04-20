# Spring Interceptor 개념 및 흐름


## Spring Request Flow
![1](https://user-images.githubusercontent.com/44339530/115347493-6c85e480-a1ec-11eb-9099-b4a52d254037.png)

### Filter
- 사용자의 어떠한 요청이 있을 경우, 가장 앞단에서 거치는 곳.
- 예를 들어, UTF-8 인코딩을 처리하는 기본 필터 중에 CharacterEncodingFilter를 호출 할 수 있다.
- Spring MVC 기준으로 web.xml에서 설정

### DispatcherServlet
- 서블릿과 마찬가지로 Java 구성 또는 web.xml을 사용하여 서블릿 규격에 따라 선언 및 매핑한다. 그 다음, DispatcherServlet은 Spring Config를 사용하여 Requset Mapping, View Solution, Exception Handling 등에 필요한 대리 구성 요소를 검색한다.
- Spring MVC기준으로 servlet-context.xml에서 설정

### HandlerInterceptor
- 흔히 말하는 Spring Interceptor의 정식명칭은 HandlerInterceptor이다.
- 실제로는 그림과 다르게 Controller의 외부를 감싸고 있다.
- Controller의 실행 전, 실행 후 처리를 할 수 있다.

## HandlerInterceptor Flow
![2](https://user-images.githubusercontent.com/44339530/115347993-09e11880-a1ed-11eb-9283-fafaf0d363bd.png)

- 요청에 따른 특정 기능을 적용하려는 경우에 Interceptor는 Controller로 요청이 처리되기 전에 요청을 가로챌 수 있다.
    - preHandle(): 실제 핸들러가 실행되기 전에 실행되며, boolean 값을 리턴한다. 리턴 값이 true일 경우 계속 실행되고 false일 경우, DispatcherServlet은 Interceptor 자체가 요청을 처리한 것으로 간주하여 다른 Interceptor 및 핸들러를 계속 실행하지 않는다.
    - postHandler(): 핸들러가 실행된 후 실행되는 메서드
    - afterCompletion(): 전체 요청이 끝난 후 실행되는 메서드

#### 출처
- https://velog.io/@y_dragonrise/Spring-Interceptor-%EA%B0%9C%EB%85%90-%EB%B0%8F-%ED%9D%90%EB%A6%84