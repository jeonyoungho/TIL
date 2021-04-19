# Spring Framework 개요

## 프레임워크란?
### Framework의 개념
- <b>“소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것” - 랄프 존슨(Ralph Johnson) -</b>
- 말 그대로 뼈대나 근간을 이루는 코드들을 묶어놓은 것
- 프레임워크는 반제품으로 애플리케이션 구조 및 코드의 상당 부분을 제공하여 개발자는 핵심 비즈니스로직에만 집중할 수 있다.

### Framework의 장점
- 생산성
    - Framework가 소프트웨어의 구조 및 기반이 되는 클래스를 제공하기에 개발자는 비즈니스 로직에만 집중할 수 있기에 생산성을 높일 수 가 있다.
- 코드 품질
    - 코드의 재사용 및 유지 보수 용이
    - 확장성을 가진 코드 설계 가능

### Framework와 Library의 차이
- <b>가장 중요한 차이점은 "Inversion of Control"</b>
- 프레임워크
    - <b>제어의 주체는 프레임워크: 프레임워크에서 여러분의 코드를 호출(제어의 역전)</b>
    - 소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것
    - ex) 자동차의 프레임, 즉 기본적으로 구성하고 있는 뼈대

- 라이브러리
    - <b>제어의 주체는 개발자: 코드에서 라이브러리 함수를 호출</b>
    - 자주 사용되는 로직을 재사용하기 편리하도록 잘 정리한 일련의 클래스들의 집합
    - ex) 자동차의 기능을 하는 부품

## Spring Framework란?
### 1. Spring Framework의 개념
- Java ①엔터프라이즈 개발을 편리하게 해주는 ②경량급 ③오픈소스 ④애플리케이션 프레임워크이다.
- ① 엔터프라이즈 개발의 편리성
    - 개발자는 복잡하고 실수하기 쉬운 Low Level에 많이 신경쓰지 않으면서 비즈니스 로직 개발에만 전념할 수 있도록 해준다.

- ② 경량급
    - Spring은 20여개의 모듈로 세분화되고 복잡하고 방대한 코드를 가진 프레임워크이다.
    - 하지만 경량급인 이유는 기존 EJB(자바 엔터프라이즈 기술) 의 불필요한 복잡함을 해결해주었기 때문이다.
    - EJB는 고가의 무거운 자바 서버(WAS)가 필요했고, 다루기 힘든 설정파일 구조, 패키징, 불편한 배포 등이 단점이었다.
    - 반면 Spring은 톰캣과 같은 단순한 서버환경에서도 동작하며, 단순한 개발환경으로도 엔터프라이즈 애플리케이션을 개발하는데 충분하다. 또한 EJB에 비해 코드량이 적고 단순하다.
    
- ③ 오픈소스
    - Spring은 오픈소스의 장점을 충분히 취하면서 오픈소스의 단점과 한계를 잘 극복했다.

- ④ 애플리케이션 프레임워크
    - 특정 계층이나 기술, 업무 분야에 국한되지 않고 애플리케이션의 전 영역을 포괄하는 범용적인 프레임워크를 말한다.

### 2. Spring Framework의 전략
![1](https://user-images.githubusercontent.com/44339530/115144750-5f041980-a089-11eb-8270-e31c17d91a97.png)<br>
- 엔터프라이즈 개발의 복잡함을 상대하는 Spring의 전략
    - 1)Portable Service Abstraction(PSA)
    - 2)DI
    - 3)AOP
    - 4)POJO

#### 1) Portable Service Abstraction(PSA)
- 환경의 변화와 관계없이 일관된 방식의 기술로의 접근 환경을 제공하려는 추상화 구조
- 이는 POJO 원칙을 철저히 따른 Spring의 기능으로 Spring에서 동작할 수 있는 Library 들은 POJO 원칙을 지키게끔 PSA형태의 추상화가 되어있음을 의미한다.
- <b>예를 들어, Junit, Mybatis 등의 여러 Java Framework 에서 사용가능한 라이브러리들은 Spring 에서 지원하는 JUnit 이나 MyBatis 라이브러리와 다르다. 또한 JPA를 사용할 떄에 있어서도 Spring JPA가 추상화 시켜준다. 따라서 개발자가 Hibernate 를 사용하건 EclipseLink 를 사용하건 신경쓸 필요가 없다.</b>
- 따라서 이러한 외부 라이브러리들은 Spring에서 사용할 때 내부구현이 달라지더라도 동일한 인터페이스로 동일한 구동이 가능하게끔 설계되어 있으며 의존성을 고려할 필요가 없다.
- Spring은 특정 기술에 직접적 영향을 받지 않게끔 객체를 POJO기반으로 한번씩 더 추상화한 Layer를 갖고 있으며 이를 통해 일관성있는 서비스 추상화를 만들어 낸다.
- 출처: https://jins-dev.tistory.com/entry/Spring-PSAPortable-Service-Abstraction%EC%9D%98-%EA%B0%9C%EB%85%90

#### 2) 객체지향과 DI(Dependency Injection)
- 객체지향에 충실한 설계가 가능하도록 단순한 객체형태로 개발할 수 있다.
- POJO 객체들 사이의 의존 관계를 Spring이 알아서 연관성을 맺어준다.
~~~
1. 의존성(Dependency)이란?
- 하나의 객체가 다른 객체 없이 제대로 된 역할을 수행 할 수 없다는 것을 의미
- 의존성은 하나의 객체가 다른 객체의 상태에 따라 영향을 받는 것을 의미
- 흔히 A객체가 B객체 없이 동작이 불가능한 상황을 'A가 B에 의존적'이라고 표현
2. 주입(Injection)이란?
- 말 그대로 외부에서 '밀어 넣는 것'을 의미
~~~
- 의존성과 주입을 결합하여 생각해보면 '어떤 객체가 필요한 객체를 외부에서 밀어 넣는다'는 의미
- Spring에서는 <b>'ApplicationContext'</b>라는 존재가 필요한 객체들을 생성하고, 필요한 객체들을 주입하는 역할을 한다.
- 즉, Spring을 활용하면 객체와 객체를 분리해서 생성하고 이러한 객체들을 엮는(wiring) 작업을 하는 형태의 개발을 하게 됨
- <b>'ApplicationContext'</b>가 관리하는 객체들을 <b>'빈(Bean)'</b> 이라 부르며 빈과 빈사이의 의존 관계를 처리하는 방식으로 <b>XML설정, 어노테이션 설정, Java 설정 방식</b>이 있다.

#### 3) AOP(Aspect Oriented Programming)
<img width="725" alt="스크린샷 2021-04-18 오후 9 21 26" src="https://user-images.githubusercontent.com/44339530/115145345-097d3c00-a08c-11eb-805a-18e421b67a30.png"><br>

- <b>관점 중심 프로그래밍</b>
- Spring은 핵심적인 비즈니스 로직과 관련이 없으나 여러 곳에서 공통적으로 쓰이는 기능들을 분리(공통 관심사를 분리)하여 개발하고 실행 시에 서로 조합 할 수 있는 AOP를 지원한다.
- 이를 통해 코드를 단순하고 깔끔하게 작성할 수 있다.
- 예를 들어, 대부분의 시스템이 공통으로 가지고 있는 보안이나 로그, 트랜잭션과 같이 비즈니스 로직은 아니지만 반드시 처리가 필요한 부분을 스프링에서는 '횡단 관심사(cross-concern)'라고 정의한다.
- AOP는 이러한 횡단 관심사를 모듈로 분리하는 프로그래밍의 패러다임으로 스프링의 핵심 기능 중 하나로 개발자는 아래와 같은 장점이 있다.
    - 핵심 비즈니스 로직에만 집중해서 코드 개발
    - 각 프로젝트마다 다른 관심사를 적용할 때 코드 수정을 최소화
    - 원하는 관심사의 유지보수가 수월한 코드를 구성
#### AOP를 활용한 Transaction(트랜잭션) 지원
- 데이터 베이스를 이용할 때 반드시 신경써야 하는 부분은 하나의 업무가 여러 작업으로 이루어지는 경우의 트랜잭션 처리이다.
- 스프링은 어노테이션(Annotation)이나 XML설정으로 간편한 트랜잭션 처리를 지원한다.

#### 4)POJO(Plain Old Java Object) 기반의 구성
- POJO란 상속, 인터페이스가 필요없는 아주 단순하고 가벼운 객체를 의미
- 특정 환경이나 규약에 종속되지 않고 필요에 따라 재활용될수 있는 방식으로 설계된 객체이다.
- 원하는 비즈니스로직만 넣을 수 있도록 돕는다.


    
### 3. Spring Framework의 특징
#### 1) 컨테이너 역할
- Spring 컨테이너는 Java객체(Bean)의 LifeCycle을 관리하며, Spring 컨테이너로부터 필요한 객체(Bean)를 가져와 사용할 수 있다.

#### 2) DI(Dependency Injection) 지원
- Spring은 설정 파일이나 어노테이션을 통해 객체 간의 의존관계를 적절히 연관 맺어준다.

#### 3) AOP지원
- Spring은 트랜젝션이나 로깅, 보안과 같이 공통적으로 필요로 하는 모듈들(공통 관심사)을 실제 핵심 모듈에서 분리하여 적용 할 수 있다.

#### 4) POJO지원
- Spring 컨테이너에 저장되는 Java객체는 특정한 인터페이스를 구현하거나, 특정클래스를 상속받지 않아도 된다.

#### 5) 트랜잭션 처리를 위한 일관된 방법을 지원
- JDBC, JPA등 어떤 트랜젝션을 사용하던 설정을 통해 정보를 관리하며 트랜젝션 구현에 상관없이 동일한 코드를 사용할 수 있다.

#### 6) 영속성과 관련된 다양한 API지원
- Spring은 MyBatis, Hibernate등 데이터베이스 처리를 위한 ORM(Object Relational Mapping)프레임워크들과의 연동을 지원한다.

### 4. Spring Framework의 기능요소
![2](https://user-images.githubusercontent.com/44339530/115145652-95439800-a08d-11eb-95d2-6f96aa1e1d1f.gif)<br>

#### 1) Spring Core
- Core컨테이너는 Spring프레임워크의 기본 기능을 제공한다. 이 모듈에 있는 BeanFactory는 Spring의 기본 컨테이너이면서 스프링 DI의 기반이다.

#### 2) Spring Context
- Context모듈은 BeanFactory의 개념을 확장한 것으로 국제화 메시지, 애플리케이션의 생명주기 이벤트, 유효성 검증을 지원한다.

#### 3) Spring DAO
- DAO 패키지는 JDBC에 대한 추상화 계층으로 JDBC코딩이나 예외처리 하는 부분을 간편화 시켰으며, AOP모듈을 이용해 트랜젝션 관리 서비스도 제공한다.

#### 4) [Spring ORM](https://github.com/jeonyoungho/TIL/tree/master/Spring/JPA)
- Mybatis, Hibernate, JPA 등 널리 사용되는 ORM 프레임워크와의 연결고리를 제공한다. ORM 프레임워크의 기능을 Spring의 기능과 조합해서 사용할 수 있도록 해준다.

#### 5) [Spring AOP](https://github.com/jeonyoungho/TIL/tree/master/Spring/Spring%20AOP)
- AOP 모듈을 통해 관점 지향 프로그래밍을 지원한다.
- AOP 모듈은 Spring 애플리케이션에서 Aspect를 개발할 수 있는 기반을 지원한다.

#### 6) Spring Web
- 일반적인 웹 애플리케이션 개발에 필요한 기능을 제공하고, 다른 웹 애플리케이션 프레임워크와의 통합을 지원한다.

#### 7) [Spring WebMVC](https://github.com/jeonyoungho/TIL/tree/master/Spring/Spring%20MVC)
- MVC(Model/View/Controller) 패러다임은 사용자 인터페이스가 애플리케이션 로직과 분리되는 웹 애플리케이션을 만드는 경우에 일반적으로 사용되는 패러다임이다. 이 패러다임을 바탕으로 웹 계층에서 결합도를 낮추는 Spring MVC 프레임워크가 있다.

## 출처
- https://gmlwjd9405.github.io/2018/10/26/spring-framework.html
- https://velog.io/@yeun/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%ACSpring-Framework
- https://shlee0882.tistory.com/200
- https://berrrrr.github.io/programming/2019/08/10/what-is-spring-framework/
- https://hoonmaro.tistory.com/32
- https://khj93.tistory.com/entry/Spring-Spring-Framework%EB%9E%80-%EA%B8%B0%EB%B3%B8-%EA%B0%9C%EB%85%90-%ED%95%B5%EC%8B%AC-%EC%A0%95%EB%A6%AC