# Spring Annotation 활성화
#### 출처 블로그: https://gmlwjd9405.github.io/2018/12/18/spring-annotation-enable.html

## Annotation 기본 개념
- xml 설정이 너무 길어짐에 따라 그 대안으로 생겨났다.
- 클래스/메서드/필드에 Annotation을 달아 그 자체로 설정이 가능하도록 한다.(단, xml의 우선순위가 더 높다)
- 기본적으로 활성화되지 않기 때문에 xml에 명시적인 활성화 설정이 필요하다.(IDE에서 체크하면 자동으로 추가되도록 지원한다.)

## Annotation 활성화

### Configurtion with Java
~~~
@Configuration
@EnableWebMvc
@ComponentScan("package-name")
public class WebConfig { ... }
https://gmlwjd9405.github.io/2018/12/18/spring-annotation-enable.html
~~~

### Configuration with Xml
~~~
<!-- Annotation 활성화 -->
<mvc:annotation-driven></mvc:annotation-driven> 
<!-- Component 패키지 지정 -->
<context:component-scan base-package="package-name"></context:component-scan>
https://gmlwjd9405.github.io/2018/12/18/spring-annotation-enable.html
~~~

## annotation-driven, component-scan, annotation-config 차이

### mvc:annotation-driven
- Spring MVC 컴포넌트들을 그것의 디폴트 설정을 가지고 활성화하기 위해 사용
- <b>이 태그는 Spring MVC가 @Controller에 요청을 보내기 위해 필요한 HandlerMapping과 HandlerAdapter를 bean으로 등록한다.</b>
    - 이렇게 등록된 bean에 의해 요청 url과 컨트롤러를 매칭할 수 있다.
    - 또한 컨트롤러(@Controller)에서는 @RequestMapping, @ExceptionHandler 등과 같은 어노테이션을 통해 해당 기능을 사용할 수 있도록 한다.
    -  근본적으로 @Controller 없이는 이 태그는 아무것도 하지 않는다고 할 수 있다.
    - bean을 생성하기 위해 xml파일에 context:component-scan을 명시하면 이 태그를 포함하지 않아도 MVC애플리케이션은 동작한다.
- RequestMappingHandlerMapping?
    - 요청 url과 매칭 되는 컨트롤러(@Controller)를 검색하는 역할. 즉, 요청 url을 보고 어떤 컨트롤러가 처리할지 결정한다.
- RequestMapppingHandlerAdapter?
    - 컨트롤러의 실행 결과(요청을 처리한 결과)를 리턴하는 역할. Annotation 기반의 Controller 처리를 위해 반드시 필요하다.

### context:component-scan
- 특정 패키지 안의 <b>클래스들을 스캔하고, Annotation을 확인 후 bean 인스턴스를 생성</b>한다.
- 이것의 장점 중 하나는 @Autowired와 @Qualifier Annotation을 인식할 수 있다.
- component-scan을 선언했다면 context:annotation-config를 선언할 필요가 없다.

### context:annotation-config
- <b>ApplicationContext(Spring container) 안에 이미 등록된 bean들의 Annotation을 활성화</b> 하기 위해 사용된다.
    - bean들이 xml로 등록했는지 혹은 패키지 스캐닝을 통해 등록됐는지는 중요치 않음
- 이미 스프링 컨텍스트에 의해 생성되어 저장된 bean들에 대해서 @Autowired와 @Qualifier Annotation을 인식할 수 있다.
- component-scan 또한 같은 일을 할 수 있는데, context:annotation-config는 bean을 등록하는 작업을 하지 않는다.
    - 즉, bean을 등록하기 위해 패키지를 안의 클래스를 스캔할 수 없다.
- 이 태그를 설정하면 @Required @Autowired @Resource @PostConstruct @PreDestroy @Configuration 기능을 각각 설정하지 않아도 된다.

## 추가적으로 알아두면 좋은 것들
### tx:annotation-driven
- <b>등록된 빈 중에서 @Transactional이 붙은 클래스/인터페이스/메소드를 찾아 트랜잭션 어드바이스를 적용한다.</b>

### mvc:resources mapping
- <b>정적인 data files들의 위치를 mapping</b>
    - Controller가 처리할 필요 없이 해당 위치의 디렉터리에서 바로 접근할 수 있다.
    - HTTP GET 요청에서의 정적인 data에 바로 매핑이 가능하다.

#### 예제1
~~~
 <mvc:resources mapping="/static/**" location="/static/" />
~~~
- WebContent/webapp/static/(ex. image file, css, js, fonts) -> context-root/static/(ex. image file, css, js, fonts)

#### 예제2
~~~
 <mvc:resources mapping="/resources/**" location="/resources/" />
~~~
- WebContent/webapp/resources/(ex. image file, css, js, fonts) -> context-root/resources/(ex. image file, css, js, fonts)
- jsp에서 사용하기 위해서 다음과 같이 추가해주면 된다.
~~~
<link rel="stylesheet" type="text/css" href="/resources/css/main.css"> 
~~~

#### 출처
- https://gmlwjd9405.github.io/2018/12/18/spring-annotation-enable.html
