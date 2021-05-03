# Spring AOP

### AOP란?
- Aspect Oriented Programming의 약자로 관점 지향 프로그래밍을 뜻함
- 핵심 비지니스 로직과 공통 모듈(횡단 관심사)을 분리함으로써 개발자가 좀 더 비지니스 로직에만 집중해서 처리할 수 있는 방법을 제공하는 프로그래밍 패러다임

![2](https://user-images.githubusercontent.com/44339530/116854223-51769400-ac32-11eb-961a-b66c918bf1e6.jpeg)<br>

- 핵심기능 관점은 위의 그림과 같이 각 Service모듈이 각각 자신만의 역할을 수행하기에 공통된 모듈을 발견할 수가 없을 것이다.<br>

<img width="658" alt="스크린샷 2021-05-03 오후 5 16 14" src="https://user-images.githubusercontent.com/44339530/116854820-46703380-ac33-11eb-8ca5-d8c96a253bee.png"><br>

- 위의 그림처럼 부가기능의 관점에서 바라보면 각각의 Service는 before()메서드와 after()메서드가 공통됨을 알 수 있을 것이다.
- AOP는 기존에 OOP에서 바라보던 관점을 다르게 하여 부가기능적인 측면에서 보았을때 공통된 요소를 추출하자는 것이다.
- 가로(횡단) 영역의 공통된 부분을 잘라냈다고 하여, AOP를 크로스 컷팅(Cross-Cutting)이라고도 부른다.
- 부가기능 모듈을 Aspect라 부르며 <b>애플리케이션 전반적으로 흩어진 부가기능(Aspect)를 모듈화하여 재사용할 수 있도록 지원하는 프로그래밍 기법</b>

### AOP와 OOP의 차이
- OOP와 AOP는 둘 다 공통된 기능을 재사용하는 기법이며 AOP는 OOP를 보완한다.
- 프로그램 구조에 대해서 다른 생각의 방향을 제시하면서 AOP가 OOP를 보완하고 있단 뜻
- OOP는 객체를 재사용함으로써 반복되는 코드의 양을 굉장히 많이 줄일수가 있다.
- 각 객체들이 가지고 있는 로그, 권한 체크, 인증, 예외 처리 등 은 필수적이기에 중복되는 부분이 발생할 수 밖에 없다.
- OOP에서 공통된 기능을 재사용하는 방법으로 상속이나 위임을 사용하지만 전체 애플리케이션에서 사용되는 부가기능들을 상속이나 위임으로 처리하기에는 깔끔하게 모듈화하기가 어려움.
- 즉, AOP는 객체의 공통된 부가 기능(로그, 권한 체크, 인증, 예외 처리 등)을 분리하여 모듈화함으로써 프로젝트 전체의 유지 보수, 재사용을 용이하게도와준다.

- OOP : 비지니스 로직의 모듈화(모듈화의 핵심 단위는 비즈니스 로직)
- AOP : 인프라 혹은 부가기능의 모듈화(각각의 모듈들의 주 목적 외에 필요한 부가적인 기능들)
    - 대표적 예: 로깅, 트랜잭션, 보안 등

### AOP의 두 가지 장점
- 1)어플리케이션 전체에 흩어진 공통 기능이 하나의 장소에서 관리됨
- 2)다른 서비스 모듈들이 본인의 역할에만 충실하고 그외 부가적인 기능들은 신경쓰지 않아도 됨

## AOP 용어
### Target
- <b>부가기능을 부여할 대상</b>
- 예를 들어, Service Layer(getBoards혹은 getUsers를 하는 Service들)

### Aspect
- <b>실제 부가기능 모듈</b>
- 객체지향 모듈을 오브젝트라 부르는 것과 비슷하게 부가기능 모듈을 Aspect라 부름
- 핵심 기능에 부가되어 의미를 갖는 특별한 모듈
- Aspect는 <b>부가될 기능을 정의한 Advice와 Advice를 어디에 적용할지를 결정하는 Point cut으로 구성됨</b>

### Advice
- <b>실제 적용될 부가 기능</b>
- 실질적으로 부가기능을 담은 구현체
- 타겟 오브젝트에 종속되지 않기 때문에 순수하게 부가기능에만 집중할 수 있음
- '무엇'을 '언제'를 정의(무엇 -> 해당 어노테이션이 달린 메소드, 언제 -> 어노테이션의 종류)
- Advice의 종류
    - 1)Before : 메서드 호출 이전
    - 2)After : 메서드 호출 이후
    - 3)AfterReturning : 메서드가 예외없이 실행된 이후
    - 4)AfterThrowing : 메서드에 예외가 발생한 이후
    - 5)Around : 메서드의 실행 전/후

### Point cut
- 여러 Join point중에서 Advice가 적용될 대상(Spring에선 메소드)을 선정하는 방법
- 포인트컷은 포인트컷 표현식으로 명시되는데 이 표현식은 포인트컷 지정자와 타겟명세로 구성됨

#### 1)포인트 컷 지정자
- 9가지의 지정자가 있지만 실제로 execution과 @annotation을 주로 사용
- <b>1)excution()</b>
    - 접근제한자, 리턴타입, 인자타입, 클래스/인터페이스, 메소드명, 파라미터타입, 예외타입 등을 전부 조합가능한 가장 세심한 지정자
    - 풀패키지에 메소드명까지 직접 지정할 수도 있으며, 아래와 같이 특정 타입내의 모든 메소드를 지정할 수도 있다.
    - ex) execution(* com.blogcode.service.AccountService.*(..) : AccountService 인터페이스의 모든 메소드

- 2)within()
    - excution 지정자에서 클래스/인터페이스까지만 적용된 경우
    - 즉, 클래스 혹은 인터페이스 단위까지만 범위 지정이 가능
    - ex1) within(com.blogcode.service.*): service패키지 아래의 클래스와 인터페이스가 가진 모든 메소드
    - ex2) within(com.blogcode.service..): service 아래의 모든 <b>하위 패키지까지</b> 포함한 클래스와 인터페이스가 가진 메소드

- 3)args
    - 메소드의 인자가 타겟 명세에 포함된 타입일 경우
    - ex)args(java.io.Serializable): 하나의 파라미터를 갖고, 그 인자가 Serializable타입인 모든 메소드
- 4)@args
    - 메소드의 인자가 타겟 명세에 포함된 어노테이션 타입을 갖는 경우
    - @args(com.blogcode.session.User): 하나의 파라미터를 갖고, 그 인자의 타입이 @User 어노테이션을 갖는 모든 메소드(@User User user같이 인자 선언된 메소드)

- 5)this()
    - 타겟 메소드가 지정된 빈 타입의 인스턴스인 경우
- 6)target()
    - this와 유사하지만 빈 타입이 아닌 타입의 인스턴스인 경우
- 7)@target()
    - 타겟 메소드를 실행하는 객체의 클래스가 타겟 명세에 지저오딘 타입의 어노테이션이 있는 경우

- <b>8)@annotation</b>
    - 타겟 메소드에 특정 어노테이션이 지정된 경우
    - ex) @annotation(org.springframework.transaction.annotation.Transactional): Transactional 어노테이션이 지정된 메소드 전부

#### 2)타겟 명세
- 실제 타겟이 적용될 지점 명시한 것
- excution(* com.blogcode.board.BoardService.getBoards(..))
- <b>AND, OR, NOT과 같은 관계연산자를 이용할 수 있음</b>
~~~
@Around("execution(* com.blogcode.board.BoardService.getBoards(..)) 
|| execution(* com.blogcode.user.UserService.getUsers(..))")
~~~

- <b>아래와 같이 @Pointcut 어노테이션을 활용하여 특정 표현식을 변수처럼 재사용할 수 있음. 즉, 재사용 가능한 포인트컷을 정의할 수 있음</b>
~~~
@Aspect
public class Performance {

    @Pointcut("execution(* com.blogcode.board.BoardService.getBoards(..))")
    public void getBoards(){}

    @Pointcut("execution(* com.blogcode.user.UserService.getUsers(..))")
    public void getUsers(){}


    // 각각의 표현식을 getBoards() 메소드와 getUsers() 메소드로 미리 선언 후 메소드명으로 표현식을 그대로 사용할 수 있음
    @Around("getBoards() || getUsers()")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            System.out.println("수행 시간 : "+ (end - start));
        } catch (Throwable throwable) {
            System.out.println("exception! ");
        }
        return result;
    }
}
~~~

- <b>타겟 메소드에 전달된 인자를 사용할 수 있음</b>
~~~
@Aspect
@Component // @Bean과 동일하게 Spring Bean 등록 어노테이션
public class UserHistory {

    @Autowired
    private HistoryRepository historyRepository;

    // updateUser라는 포인트컷이 user라는 인자를 사용하도록 args(user) 표현식으로 지정
    @Pointcut("execution(* com.blogcode.user.UserService.update(*)) && args(user)")
    public void updateUser(User user){}

    @AfterReturning("updateUser(user)")
    public void saveHistory(User user){
        historyRepository.save(new History(user.getIdx()));
    }
}
~~~
- 표현식의 "args(user)"로 인해 타겟 메소드의 인자와 어드바이스의 인자가 매칭됨
- 위의 코드는 유저 정보의 변경이 정상적으로 실행될시 변경 기록에도 추가하는 Aspect


### Join point
- <b>메서드</b>, 필드, 객체, 생성자 등 실제 advice가 적용될 수 있는 지점
- <b>다른 AOP 프레임워크와 달리 Spring에서는 메소드 Join point만 제공(그래서 여러 책이나 문서에서 조인포인트에 대해 생략하기도 합니다. 무조건 메소드 단위로만 지정하기 때문입니다.)</b>

### Proxy(프록시)
- Target을 감싸서 Target의 요청을 대신 받아주는 랩핑(Wrapping) 오브젝트
- Spring AOP는 프록시 패턴이라는 디자인 패턴을 사용해서 AOP효과를 낸다.
    - 프록시 패턴을 사용하면 어떤 기능을 추가하려 할때 기존 코드를 변경하지 않고 기능을 추가할 수 있음
    - 어떤 클래스가 Spring AOP의 대상이라면 그 기존 클래스의 Bean이 생성될때 Spring AOP가 프록시(기능이 추가된 클래스)를 자동으로 만들고 원본 클래스 대신 프록시를 Bean으로 등록한다. 그리고 원본 클래스가 사용되는 지점에 프록시를 대신 사용한다.
- 예를 들어, OwnerRepository인터페이스의 @Transactional 어노테이션이 사용된다고 가정해보자.<br>
![5](https://user-images.githubusercontent.com/44339530/116864160-5fccac00-ac42-11eb-87f9-fd3d4cf1613f.png)<br>
- @Transactional 어노테이션이 붙어있으면 OwnerRepository 타입의 프록시가 새로 만들어지고 Spring AOP에 의해 자동으로 생성되는 OwerRepository의 프록시에는 @Transactional 어노테이션이 지시하는 코드가 삽입된다.
- 즉, JDBC에서 트랜잭션 처리를 하려면 SQL 실행문 앞 뒤에 setAutoCommit()와 commit()/rollback() 코드가 항상 붙게 되는데 @Transactional 어노테이션은 프록시에 자동으로 그 코드를 넣어서 반복, 중복되는 코드를 생략할 수 있다. <b>이로소 개발자는 비즈니스 로직에만 집중할 수 있게 된다.</b>
- 호출자에서 OwerRepository(타겟)를 호출하게 되면 이를 감싸고 있는 프록시가 호출되어, 메소드에 대한 선처리 및 후처리를 실행시키도록 한다.
- 즉, AOP에서 프록시는 호출을 가로챈 후, Advice에 등록된 기능을 수행 후 타겟 메소드를 호출한다.<br>
![3](https://user-images.githubusercontent.com/44339530/116859148-7d961300-ac3a-11eb-8e2b-c9a70b0ad369.png)<br>

### Introduction(인트로덕션)
- Target 클래스에 코드 변경없이 신규 메소드나 멤버변수를 추가하는 기능

### Weaving(위빙)
- 지정된 객체에 Aspect를 적용하여 새로운 프록시 객체를 생성하는 과정
- 예를 들어, A라는 객체에 트랜잭션 Aspect가 지정되어 있다면, A라는 객체가 실행되기전 커넥션을 오픈하고 실행이 끝나면 커넥션을 종료하는 기능이 추가된 프록시 객체가 생성되고, 이 프록시 객체가 앞으로 A객체가 호출되는 시점에 사용된다. <b>이때의 프록시 객체가 생성되는 과정을 위빙이라 생각하면 된다.</b>
- 컴파일 타임, 클래스로드 타임, 런타임과 같은 시점에서 실행되지만, <b>Spring AOP는 런타임에서 프록시 객체가 생성 됩니다.</b>

## 실습
### 1. Aspect를 선언
- Performance.java
~~~
@Aspect
public class Performance {

    @Around("execution(* com.blogcode.board.BoardService.getBoards(..))")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            System.out.println("수행 시간 : "+ (end - start));
        } catch (Throwable throwable) {
            System.out.println("exception! ");
        }
        return result;
    }
}
~~~

### 2. Spring 컨테이너의 Bean으로 등록
- Application.java
~~~
@Bean
public Performance performance() {
    return new Performance();
}
~~~

### 3. Bean으로 등록된 Aspect의 프록시를 생성하고, 어노테이션을 해석할 수 있도록 활성화
- Application.java
~~~
@SpringBootApplication
@RestController
@EnableAspectJAutoProxy //오토 프록싱
public class Application implements CommandLineRunner{
    ...
}
~~~

### 설명
<img width="410" alt="4" src="https://user-images.githubusercontent.com/44339530/116859960-c26e7980-ac3b-11eb-80a0-e0cc052a2d81.png"><br>

- Advice(@Around)
    - Advice는 '무엇을', '언제' 할지를 의미
    - 무엇: calculatePerformanceTime() 메소드
    - 언제: @Around로 타겟 메소드 호출 전과 후에
    - 예를 들어, 타겟 메소드의 호출 전에만 적용하려면
    ~~~
    @Before("포인트컷 표현식")
    public void 어드바이스메소드() {
        ....
    }
    ~~~
    - <b>주의할 점은 @Around의 경우 반드시 proceed() 메소드가 호출되어야 한다는 것</b>
    - proceed() 메소드는 타겟 메소드를 지칭하기에 proceed메소드를 실행시켜야만 타겟 메소드가 수행이된다는 것에 주의

- Point cut("execution(* com.blogcode.board.BoardService.getBoards(..))")
    - Advice의 value로 들어간 문자열을 <b>포인트컷 표현식</b>이라고 함
    - 포인트컷 표현식은 2가지로 나눠짐
        - 1)포인트컷 지정자: execution
        - 2)타겟 명세: (* com.blogcode.board.BoardService.getBoards(..))

## AOP의 활용 방법
- 1)트랜잭션
- 2)캐시 추상화
- 3)Dao에서 Service를, Service에서 Controller를 호출하지 못하도록 막을때(계층형 구조에서 하위계층이 상위 계층을 호출하는 것은 금지하므로)

#### 출처
- https://jojoldu.tistory.com/71?category=635883
- https://www.youtube.com/watch?v=Hm0w_9ngDpM&list=RDCMUC-mOekGSesms0agFntnQang&index=6
- https://dailyheumsi.tistory.com/202
- https://atoz-develop.tistory.com/entry/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-AOP-%EA%B0%9C%EB%85%90-%EC%9D%B4%ED%95%B4-%EB%B0%8F-%EC%A0%81%EC%9A%A9-%EB%B0%A9%EB%B2%95
- https://doublesprogramming.tistory.com/115