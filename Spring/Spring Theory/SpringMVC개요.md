# Spring MVC 개요

## Spring MVC란?
- Spring 프레임워크에서 제공하는 웹 모듈이다.
- MVC는 Model, View, Controller의 약자로 기본 시스템 모듈을 MVC로 나누어 구현되어있다.

#### Model
- 애플리케이션 상태(data)를 나타낸다.
- 일반적으로 POJO로 구성된다.
- Java Beans

#### View
- 디스플레이 데이터 또는 프리젠테이션
- Model data의 렌더링을 담당하며, HTML ouput을 생성
- JSP 및 Thymeleaf, Groovy, Freemarker 등 여러 Template Engine이 있다.

#### Controller
- View와 Model사이의 인터페이스 역할
- Model/View에 대한 사용자 입력 및 요청을 수신하여 그에 따라 적절한 결과를 Model에 담아 View에 전달한다.
- 즉, Model Object와 이 Model을 화면에 출력할 View Name을 반환
- Controller -> Service -> Dao -> DB

### Spring Framework가 제공하는 Class
- DispatcherServlet
    - 맨 앞단의 controller역할을 수행
    - 사용자의 요청을 가장 먼저 받아 처리한다.
- HandlerMapping
    - 사용자의 요청을 처리할 Controller를 찾아 실제 request를 처리할 Handler객체를 리턴
    - 요청 url에 해당하는 Controller 정보를 저장하는 table을 가진다.(Controller URL Mapping)
    - 즉, 클래스에 @RequestMapping(“/url”) annotaion을 명시하면 해당 URL에 대한 요청이 들어왔을 때 table에 저장된 정보에 따라 해당 클래스를 처리할 Handler객체를 리턴한다.
- ViewResolver
    - Controller가 반환한 View Name(the logical names)에 prefix, suffix를 적용하여 View Object(the physical view files)를 반환한다.
    - 해당 View Object에서 model data를 이용하여 적절한 페이지를 렌더링 후 사용자에게 보여준다.

## Spring MVC 프로젝트 구조
<img width="699" alt="스크린샷 2021-04-19 오후 3 29 29" src="https://user-images.githubusercontent.com/44339530/115191172-09c91600-a124-11eb-80d7-5f2efa368598.png"><br>

- src
    - 개발자가 작성한 Servlet코드 저장
    - Controller, Model, Service, Dao
    - src/main/java: 실제 수행될 java코드가 들어갈 경로
    - src/main/resources: 서버가 실행될 때 필요한 리소스의 경로
    - src/test/java: 테스트 코드 작성 경로
    - src/test/resource: 테스트 시 사용되는 리소스에 대한 경로
- Libraries
    - Servlet이나 JSP에서 추가로 사용하는 라이브러리 또는 드라이버(jar로 압축한 파일)
- WebContent(전체 Root) - webapp
    - 배포 시 WebContent 디렉토리 전체가 war로 묶여서 보내진다.
    - resources
        - 정적인 데이터(ex. image file, css, js, fonts, etc)
    - WEB-INF
        - classes: 작성한 Java Servlet 파일이 .class로 이곳에 모두 저장된다.
        - lib: 추가한 모든 라이브러리 또는 드라이버가 이곳에 모두 저장된다.
        - props: property file을 저장
        - spring: spring configuration files을 저장한다.(spring과 관련된 모든 설정 파일)
            - dispatcher-servlet.xml 
            - applicationContext.xml
            - dao-context.xml, service-context.xml 등
    - views: Controller와 매핑되는 .jsp 파일들을 저장한다. (JSP 파일의 경로)
    - web.xml: web application의 설정을 위한 web deployment descriptor
        - DispatcherServlet, ContextLoadListener 설정
- pom.xml
    - maven configuration file
    - 어떤 lib를 쓸지 명시한다.

## Spring MVC에서 Model, View, Controller
### Model
- Controller에서 View로 개체를 전달하는데 사용
- 명명된 객체들의 집합이라 할 수 있음
    - Key-Value형식의 하나의 쌍을 명명된 객체라 부른다.
    - 또한 이 명명된 객체는 model attribute라고도 부른다.
    - 여러 개의 attribute가 모여 Table형식을 이룬다.
- view에서 attribute의 key값을 통해 value값을 사용할 수 있다.

|Key|Value|
|-----|-----|
|key1|value1|
|key2|value2|

#### Model을 구현하는 3가지 방법
- Model을 표현하기 위해 여러 자료구조를 사용할 수 있다.
- Controller 메서드에 input argument로 값을 넣어주면 Spring Frmework가 자동으로 Model을 만들어주고 해당 Model의 주솟값만 넘겨준다.

- <b>1)java.util.map의 구현</b>
~~~
@RequestMapping("/greeting")
public String getGreeting(Map<String, Object> model) { 
 String greeting = service.getRandomGreeting();
 model.put("greeting", greeting);

 return "home";
}
~~~

- <b>2)Spring에서 제공하는 Model인터페이스 구현</b>
~~~
@RequestMapping("/special-deals")
public String getSpecialDeals(Model model) { 

 List<SpecialDial> specialDeals = service.getSpecialDeals();
 model.addAttribute(specialDeals); // value만 넣으면 name은 자동 생성

 return "home";
}
~~~

- <b>3)Spring에서 제공하는 ModelMap객체(추가적인 기능을 제공, chain으로 사용 가능)</b>
~~~
@RequestMapping("/fullname")
public String getFullname(ModelMap model) { 

 // chained calls are handy!
 model.addAttribute("name", "Jon")
      .addAttribute("surname", "Snow");

 return "home";
}
~~~

### Controller
~~~
@Controller
public class HomeController {
    private static final Logget Logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        Logger.info("Welcome {}.", locale);

        // Business Logic
        Date date = new Date();
        DateFormat = dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);

        // BL의 결과를 Model에 저장 
        model.addAttribute("serverTime", formattedDate);

        // Return logical view name
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        ...
	    return success;
    }
}
~~~

- @Controller
    - bean으로 등록
    - 해당 클래스가 Controller로 사용됨을 Spring에게 알림
    - @Component의 구체화 -> @Controller, @Service, @Repository
- @RequestMapping
    - value: 해당 url로 요청이 들어오면 이 메서드가 수행된다.
    - method: 요청 method를 명시한다.
    - 즉, 위의 예시에서는 “/home” url로 HTTP GET 요청이 들어오면 home() 메서드가 실행된
    ~~~
    @Controller
    @RequestMapping("/home") // 1) Class Level
    public class HomeController {
        /* an HTTP GET for /home */ 
        @RequestMapping(method = RequestMethod.GET) // 2) Handler Level
        public String getAllEmployees(Model model) {
            ...
        }
        /* an HTTP POST for /home/employees */ 
        @RequestMapping(value = "/employees", method = RequestMethod.POST) 
        public String addEmployee(Employee employee) {
            ...
        }
    }
    ~~~
    - 1)Class Level Mapping
        - 모든 메서드에 적용되는 경우
        - “/home”로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미한다.
    - 2)Handler Level Mapping
        - 요청 url에 대해 해당 메서드에서 처리해야 되는 경우
        - “/home/employees” POST 요청에 대한 처리를 addEmployee()에서 한다는 것을 의미한다.

- model.addAttribute()
    - Business Logic의 처리 결과 값을 model attribute에 지정하면 Spring이 Model 객체를 만들어 해당 Model의 주솟값을 넘겨준다.
    - 하나의 요청 안에서만 Controller와 View가 Model을 공유한다.
- @RequestParam
    - HTTP GET 요청에 대해 매칭되는 request parameter 값이 자동으로 들어간다.
    - Ex) 'http://localhost:8080/login?username=scott&password=tiger'

### View
- View를 생성하는 방법은 여러 가지가 있다.
    - JSP 이외에도 Thymeleaf, Groovy, Freemarker 등 여러 Template Engine이 존재한다.
- JSP(Java Server Pages)
    - [JSP제한 사항](https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#boot-features-jsp-limitations)
    - Java EE에 종속적이라는 단점이 있음
    - <b>SpringBoot는 공식적으로 JSP를 지원하지 않는다.</b>
        - SpringBoot의 내장 톰캣에 하드코딩 패턴때문에 jar형식으로는 webapp 내용을 가져올 수 없다.
        - 따라서 SpringBoot에서는 war가 아닌 jar로 사용할 때는 jsp를 사용할 수 없다.
- JSTL(JSP Standard Tag Library)
    - 많은 JSP 애플리케이션의 공통적인 핵심 기능을 캡슐화하는 유용한 JSP 태그 모음
    - 즉, JSP 페이지를 작성할 때 유용하게 사용할 수 있는 여러 가지 Action과 함수가 포함된 라이브러리
    - 가장 많이 사용하는 태그 확장 라이브러리
    - 자신만의 Custom Tag를 추가할 수 있는 기능을 제공한다.
    - <b>사용하는 이유?</b>
        - JSP에 Java Code가 들어가는 것을 막기 위해서 사용한다.
        - 즉, Java Code(JSP Scriptlet)대신 Tag를 사용하여 프로그래밍할 수 있도록 하기 위해 도입되었다.

## Spring MVC 를 위한 필수 설정
### 1. Maven Configuration(pom.xml)
- 자신의 프로젝트에 대한 고유의 좌표 설정
    - 1)groupId
        - 자신의 프로젝트를 고유하게 식별하게 해주는 것으로, 최소한 내가 컨트롤하는 domain name이어야 한다.
        - package 명명 규칙을 따른다.
        - 하위 그룹은 얼마든지 추가할 수 있다.
    - 2)artifactId
        - 제품의 이름으로, 버전 정보를 생략한 jar파일의 이름이다.
        - 프로젝트 이름과 동일하게 설정
        - 소문자로만 작성하며 특수문자는 사용하지 않는다.
    - 3)version
        - SNAPSHOT: 개발용 버전, RELEASE: 배포용 버전
        - 숫자와 점을 사용하여 버전 형태를 표현(1.0, 1.1, 2.0, 2.1 ...)
~~~
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.hee</groupId>
    <artifactId>projectName</artifactId>
	<name>projectName</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
    <!-- properties에 명시한 version이 알아서 placeholder에 주입된다. -->
	<properties>
		<java-version>1.8</java-version>
		<org.springframework-version>4.2.5.RELEASE</org.springframework-version>
		<spring-security-version>4.0.4.RELEASE</spring-security-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
    <dependencies>
    <!-- Spring -->
		<dependency>
            <!-- Spring core dependency -->
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
            <!-- Spring MVC dependency -->
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
    </dependencies>
</project>
~~~

#### Maven
- Maven이란?
    - Maven은 프로젝트를 빌드하고 라이브러리를 관리해주는 도구이다.
    - pom.xml에 명시한 라이브러리를 자동으로 다운받아준다.
- maven의 장점
    - pom.xml문서 하나만으로도 개발자들끼리 쉽게 라이브러리를 공유할 수 있어 협업하는데 있어 편리하다.
    - 필요한 라이브러리의 하위 라이브러리까지 버전에 맞게 받아주어 의존성을 자동으로 해결해준다.
    - build process 자동화
        - compile -> test -> packaging(.war) -> install -> deploy
- 출처: https://myjamong.tistory.com/154

### 2. Web Deployment Descriptor (web.xml)
- 개념
    - web application의 설정을 위한 deployment descriptor
    - SUN에서 정해놓은 규칙에 맞게 작성해야 하며 모든 WAS에 대하여 작성 방법이 동일하다.
- 역할
    - deploy할 때 Servlet의 정보를 설정해준다.
    - 브라우저가 Java Servlet에 접근하기 위해서는 WAS(Ex. Tomcat)에 필요한 정보를 알려줘야 해당하는 Servlet을 호출할 수 있다.
        - 정보1) 배포할 Servlet이 무엇인지
        - 정보2) 해당 Servlet이 어떤 URL에 매핑되는지
- 구체적인 설정 내용
    - DispatcherServlet
    - ContextLoaderListener
    - Filter: encodingFilter, springSecurityFilterChain
~~~
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee; http://java.sun.com/xml/ns/javaee/web-app_2.5.xsd">

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/spring/service-context.xml
			/WEB-INF/spring/dao-context.xml
			/WEB-INF/spring/security-context.xml
			/WEB-INF/spring/applicationContext.xml
		</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/dispatcher-servlet.xml
			</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy
		</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
</web-app>
~~~

### 3. Spring MVC Configuration Files
- 1)dispatcher-servlet.xml
    - 주요 설정 내용: Controller 관련, ViewResolver, mvc:annotation-driven설정 등
    - ①Annotation 활성화
        ~~~
        <mvc:annotation-driven />
        ~~~
    - ②Component를 등록하기 위해 스캔할 패키지 지정
        ~~~
        <context:component-scan base-package="controller"/>
        ~~~
        - 이 패키지를 스캔하며 annotaion이 달린 것을 bean으로 생성하여 Container에 담아둔다.
        - 참고) 이 내용은 service, dao 설정에도 필요하다.
        ~~~
        <context:component-scan base-package="service"/>
        <context:component-scan base-package="dao"/>
        ~~~
    - ③정적 리소스 위치 mapping
        ~~~
        <mvc:resources mapping="/resources/**" location="/resources/" />
        또는 
        <mvc:resources mapping="/static/**" location="/static/" />
        ~~~ 
        - web/resources/ 하위에 정적인 데이터(css, js, img, font)가 존재
        - Controller가 처리할 필요 없이 해당 위치의 디렉터리에서 바로 접근할 수 있다.
        - HTTP GET 요청에서의 정적인 data에 바로 매핑이 가능하다.
    - ④ViewResolver
        ~~~
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/views/"/>
            <property name="suffix" value=".jsp"/>
        </bean>
        ~~~

- 2)applicationContext.xml
    - 주요 설정 내용: DataSource 관련, properties 등록, SessionFactory, TransactionManager 등 
    - ①properties 등록
        ~~~
        <context:property-placeholder location="/WEB-INF/props/jdbc.properties" />
        동일 
        <context:property-placeholder location="classpath:props/jdbc.properties
        ~~~
        - properties file에서 읽어와 주입한다.
    - ②DataSource주입
        ~~~
        <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
      destroy-method="close">
            <property name="driverClassName" value="${jdbc.driverClassName}" />
            <property name="url" value="${jdbc.url}" />
            <property name="username" value="${jdbc.username}" />
            <property name="password" value="${jdbc.password}" />
        </bean>
        ~~~
    - ③어노테이션에 기반한 트랜잭션 동작의 설정을 활성화
        ~~~
        <tx:annotation-driven />
        ~~~
    - ④Session Factory 등록 및 Transaction Manger 설정
        ~~~
        <bean id="sessionFactory"
      class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
            <property name="dataSource" ref="dataSource"></property>
            <property name="packagesToScan">
                <list>
                    <value>com.spring.model</value>
                </list>
            </property>
            <property name="hibernateProperties">
                <props>
                    <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
                    <prop key="hibernate.hbm2ddl.auto">update</prop>
                    <prop key="hibernate.show_sql">true</prop>
                    <prop key="hibernate.format_sql">false</prop>
                </props>
            </property>
        </bean>

        <bean id="transactionManager"
            class="org.springframework.orm.hibernate5.HibernateTransactionManager">
            <property name="sessionFactory" ref="sessionFactory"></property>
        </bean>
        ~~~

- 3)service-context.xml
    - 주요 설정 내용: Service Layer 관련
    - Component를 등록하기 위해 스캔할 패키지 지정
        ~~~
        <context:component-scan base-package="service" />
        ~~~
        - 이 패키지를 스캔하며 annotation이 달린 것을 bean으로 생성하여 container에 담아준다.

- 4)dao-context.xml
    - 주요 설정 내용: Dao Layer 관련
    - Component를 등록하기 위해 스캔할 패키지 지정
        ~~~
        <context:component-scan base-package="dao" />
        ~~~
        - 이 패키지를 스캔하며 annotation이 달린 것을 bean으로 생성하여 container에 담아준다.
- 5)security-context.xml
    - 주요 설정 내용: Security관련, BCryptPasswordEncoder 등
    ~~~
    <security:authentication-manager>
        <security:authentication-provider>
            <security:jdbc-user-service
                data-source-ref="dataSource"
                users-by-username-query="select username, password, enabled from users where username=?"
                authorities-by-username-query="select username, authority from users where username=?" />
            <security:password-encoder ref="passwordEncoder"></security:password-encoder>
        </security:authentication-provider>
    </security:authentication-manager>

    <security:http auto-config="true" use-expressions="true">
        <security:intercept-url pattern="/admin/**" access="hasRole('ROLE_ADMIN')" />
        <security:form-login login-page="/login" authentication-failure-url="/login?error" />
    </security:http>

    <bean id="passwordEncoder"
        class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder">
    </bean>
    ~~~

#### 출처
- https://gmlwjd9405.github.io/2018/12/20/spring-mvc-framework.html
