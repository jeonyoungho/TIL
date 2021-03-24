# Logging

## Logging이란?
- 애플리케이션 실행을 추적하기 위해 콘솔, 파일, DB와 같은 어딘가에 메시지를 기록하는 것이다.
- 일반적으로 디버깅이나 사용자의 상호작용을 기록하기 위해 사용한다.

## Logging (vs. debugger)

#### 장점
- 애플리케이션의 정확한 수행 상태를 파악 할 수 있다.
- 코드에 로깅하는 코드만 삽입 되어있다면 사용자의 개입이 필요 없다.
- 영구적인 매체에 저장 할 수 있다.
- 로깅 프레임워크는 간단하고 배우기 쉽고 사용하기 쉽다.

#### 단점
- 기록을 해야되기 때문에 느려질 수 있다.
- 너무 로깅 메시지가 많이 나타나 장황해 질 수 있다.
- 고급 사용은 배워야 할 필요가 있다.

## Logging (vs. Plain output)
- 더욱 높은 유연성을 제공한다.
    - 특정 수준이상의 메시지만 출력할 수 있음
    - 특정한 모듈 및 클래스에 대해서 선택적으로 출력가능함
    - 어디에 출력할지 지정가능

## Logging Framework
- 1. java.util.logging: 흔히 사용 되지 않음
- 2. Log4j: 몇 년전 까지만 해도 가장 흔히 사용 되지 사실상 표준이였음
- 3. Logback: Log4j의 후계자로서 같은 개발자에 의해 성성됨, 현재 많은 프로젝트에서 사용됨
- 4. Log4j2

### facade pattern란?
![image](https://user-images.githubusercontent.com/44339530/112259670-0a4cc900-8cac-11eb-9f91-dc6065de264d.png)

- 클라이언트가 개별적인 시스템을 이해할 필요가 없고 Façade가 제공하는 공통적인 api만 사용하면 내부적인 변형을 통해서 알아서 변환을 시켜준다.
- 비록 subsystem이 실제 작업을 수행하짐나, facade는 subsytem인터페이스로 변화하여 작동해야만 한다.
- 클라이언트는 subsystem에 직접적으로 접근해서 사용 할 필요가 없다.
- 컴파일시에는 Façade만 필요하고 배포해서 수행할 때 필요한 subsystem이 작동한다

## SLF4J(Simple Logging Facade for Java)
- Log4J 또는 Logback과 같은 백엔드 로깅 프레임워크의 facade pattern(사실상 표준)
- Log4j 또는 Logback 앞단에 존재하는 패턴이다.
- java.util.logging, Logback or Log4j와 같은 다양한 로깅 프레임워크의 추상체이다.
- 프로그래머는 배포시에 원하는 로깅 프레임워크만 연결해주면 된다.
- 로깅 프레임워크의 인터페이스 역할을 하기 떄문에 로깅 구현체가 바뀌더라도 생각보다 어렵지 않게 변경 할 수 있다.
- SLF4J를 사용하려면 애플리케이션에 단일 필수 dependency만 추가하면 된다.(2019년 기준: slf4j-api-1.7.30.jar)
- 바인되있는 서브시스템이 없으면 아무것도 출력하지 않는다.

### SLF4j를 사용한 "Hello World"출력 예제
- slf4j-api-1.7.30.jar를 classpath에 추가해주고 컴파일 후 실행한다.
~~~
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HelloWorld {   
    public static void main(String[] args) {      
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);      
    logger.info("Hello World");   
    }
}
~~~

- 실행 결과<br>
<img width="610" alt="스크린샷 2021-03-24 오후 2 35 02" src="https://user-images.githubusercontent.com/44339530/112260880-1fc2f280-8cae-11eb-8220-6309fdd49742.png"><br>

- class path에서 slf4j 구현체를 찾을 수 없기 때문에 출력된다.
- 즉, class path에 사용하길 원하는 Logging Framework에 대한 slf4j 바인딩(.jar)을 추가해야 한다.
- 이때, 둘 이상의 slf4j 바인딩(반드시 하나만)을 두면 안된다.

### SLF4J와 로깅 프레임워크의 바인딩
![image](https://user-images.githubusercontent.com/44339530/112261036-60bb0700-8cae-11eb-8d8c-80474c93ee97.png)<br>

- SLF4J가 나오고 logback-classic은 이를 구현하여 나왔기 때문에 Adaptation Layer가 굳이 필요없다.
- log4j.jar가 SLF4J보다 먼저 나왔기 때문에 둘의 인터페이스가 다르므로 Adaptation Layer가 필요하다.
- <b>log4j와 JVM runtime은 Adaption Layer가 필요하다</b>
- 로깅 프레임워크를 교체 하기 위해선 단지 classpath의 slf4j를 교체해주기만 하면 된다.
    - 예를 들어, java.util.logging을 log4j로 변경하기 위해선 slf4j-jdk14-1.7.25.jar -> slf4j-log4j12-1.7.25.jar로 변경만 해주면 된다.

### SLF4J와 logback의 바인딩
- pom.xml에 "ch.qos.logback:logback-classic" 디펜던시를 추가해주면 된다.
~~~
<dependency>   
    <groupId>ch.qos.logback</groupId>  <artifactId>logback-classic</artifactId>  
    <version>1.2.3</version>
</dependency>
~~~
- 이를 추가해주면 logback-core-1.2.3.jar 뿐만 아니라 slf4j-api-1.7.30.jar도 자동으로 추가 될 것이다. (maven을 사용하면 장점이 디펜던시를 자동으로 해결 해주기 때문에)

### SLF4J를 사용한 로깅 기능 통합
<img width="586" alt="스크린샷 2021-03-24 오후 3 22 37" src="https://user-images.githubusercontent.com/44339530/112264814-c6aa8d00-8cb4-11eb-9cf0-df591d4988a3.png"><br>

- 애플리케이션이 있으면 이안에 다양한 라이브러리들이 있을 것이다. (Lombok, Spring 등등)
- 다양한 라이브러리들은 각각 자신의 로깅 프레임워크를 사용하여 구현되어 있을 것이다.
- <b>코드에서 사용하는 로깅 기능과 다양한 라이브러리에서 사용되는 로깅 기능 등을 SLF4J(single channel)를 사용하여 통합해줘야 한다.</b>
- bridging modules이라는 것을 사용해야 한다.

#### Bridging Legacy API
<img width="650" alt="스크린샷 2021-03-24 오후 3 30 40" src="https://user-images.githubusercontent.com/44339530/112265476-e5f5ea00-8cb5-11eb-969b-3f7626134275.png"><br>

- 애플리케이션의 다양한 라이브러리의 로깅 기능을 사용하더라도 jcl-over-slfj4.jar, log4j-over-slf4j.jar, jul-to-slf4j.jar 라는 브릿지를 둬서 브릿지가 slf4j로 변환시키고 slf4j가 실제로 logback을 호출 하게 됨

## Logback
- Logback의 개요
    - Log4j프로젝트의 뒤에 나온 후계자이다.
    - 속도가 빠르고 메모리를 적게 사용한다.
- Logback의 구성
    - Logback-classic 모듈은 다음 세가지 모듈이 classpath에 존재해야함
        - 1. slf4j-api.jar
        - 2. logback-core.jar: 나머지 두 모듈에 대한 뼈대 역할
        - 3. logback-classic.jar: core모듈을 확장한 log4j의 확장 버전(SLF4J를 implementation)
        - ![image](https://user-images.githubusercontent.com/44339530/112266093-e6db4b80-8cb6-11eb-86a6-2bb2d5cee639.png)<br>
        - pom.xml에는 logback-classic.jar만 추가하면 나머지 두개도 자동으로 다운받아줌(의존성 해결)

- 3 가지 주요한 클래스
    - logback-core: Appender, Layout
    - logback-classic: Logger
    - 이 3가지가 서로서로 연관이 되서 메시지 타입이나 레벨에 따라서 메시지 기록을 남김
    - 이 3가지가 서로서로 연관이 되서 메시지가 어떻게 포맷이 될 것이냐 어디에 출력할 것이냐를 컨트롤 할 수가 있음

#### Hello World 출력 예제
<img width="624" alt="스크린샷 2021-03-24 오후 3 42 15" src="https://user-images.githubusercontent.com/44339530/112266547-83055280-8cb7-11eb-91e4-f703bfed3d07.png"><br>

- slf4j의 Logger와 LoggerFactory를 사용한다.
- getLogger함수에 인자로 로거 이름을 줘서 Logger객체를 생성한다.
- 애플리케이션 코드에선 logback을 쓰든 log4j를 쓰든 신경 쓸 필요없고 slf4j에 대한 api를 사용한다.
- slf4j에 대한 클래스만 import해서 slf4j가 제공하는 api만 사용하면 된다.
- logback의 존재에 대해선 무시해도 된다.
- logback에 대한 별도 설정 파일(logback.xml)이 없다면 디폴트로 적용된다.
 
 #### Logger 메소드
 <img width="562" alt="스크린샷 2021-03-24 오후 3 51 18" src="https://user-images.githubusercontent.com/44339530/112267419-c6ac8c00-8cb8-11eb-8766-323eacd120c8.png"><br>

- 각 메소드는 메시지의 등급을 뜻함(trace -> debug -> info -> warn -> error)
- 로거에 대한 기준만 설정해주면 기준보다 높거나 같아야지만 출력이 된다.
- 예를 들면, 로거에 대한 기준이 info라면 로깅 메시지가 info, warn, error의 경우만 출력이 된다.

#### Parameterized Logging
- Case1
    ~~~
    logger.debug("Entry number: " + i + " is " + String.valueOf(entry[i]) );
    ~~~
    - 이렇게 호출하게 되면 두 단계로 이루어짐
        - 1. i, 와 entry를 String으로 변환(인자를 만드는 과정)
        - 2. debug라는 메소드를 호출
    - <b>기껏 인자를 만드는 과정을 수행했는데 설정 등급보다 낮으면 출력이 안되는 문제점 발생</b>

- Case2
    ~~~
    if(logger.isDebugEnabled()) {     
        logger.debug("Entry number: " + i + " is " + String.valueOf(entry[i]) );
    }
    ~~~
    - if문을 통해 logger의 등급을 확인하고 출력하면 불필요한 인자를 생성하는 과정을 줄일 수 있음
    - <b>하지만 logger의 등급이 활성화 되어있다면 if문을 체크해야하는 불필요한 과정이 추가되는 문제이 있다.(두 번 logger의 등급을 체크하는 과정이 생김)</b>

- Case3
    ~~~
    Object entry = new SomeObject(); 
    logger.debug("The entry is {}.", entry);
    ~~~
    - 오버헤드를 줄일 수 있는 <b>가장 바람직한 방법</b>이다.
    - debug메소드의 메시지와 argument가 바로 조합되는게 아니라 만약에 디버그 모드가 활성화 되있을 경우에만 메시지에 argument를 조합시키게 된다.
    - 만약에 디버그 비활성화 되있을 경우엔 무시하고 넘어가게 된다.
    - parameter가 3개 있을 경우엔 다음과 같이 배열을 만들어 넘겨줘도 됨
    ~~~
    Object[] paramArray = {newVal, below, above};
    logger.debug("Value {} was inserted between {} and {}.", paramArray);
    ~~~

## Logger
~~~
Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
~~~
- LoggerFactory를 사용해서 만든다.
- 이름이 부여된다.(대소문자를 구분)
- 이름에 따라 계층 구조를 이루고 있다(com.example 은 com.example.computer의 부모)
- 위의 코드처럼 root logger도 얻을 수 잇다.

~~~
package myPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Heater {
   static Logger logger = LoggerFactory.getLogger("myPackage.Heater");
      //...
}

public class Boiler extends Heater {
static Logger logger =
              LoggerFactory.getLogger("myPackage.Heater.Boiler");
               //...
}
~~~
- root -> myPackage.Heater -> myPackage.Heater.Boiler 의 순으로 계층 구조를 이룬다.
- Logger 마다 기준이 되는 레벨이 할당 된다.(TRACE< DEBUG < INFO < WARN< ERROR)
- <b>만약에 로거의 레벨이 할당되있지 않는다면 레벨을 가지고 있는 가장 가까운 조상 로거로 부터 상속을 받게 된다.</b>

- Example1<br>
    - <img width="580" alt="스크린샷 2021-03-24 오후 4 28 58" src="https://user-images.githubusercontent.com/44339530/112271247-09bd2e00-8cbe-11eb-8177-7fd391b49515.png"><br>
    - root logger도 레벨을 지정해줄 수 잇는데 default는 DEBUG이다.<br>

- Example2<br>
    - <img width="622" alt="스크린샷 2021-03-24 오후 4 30 21" src="https://user-images.githubusercontent.com/44339530/112271408-3b35f980-8cbe-11eb-81de-85844edd4a2c.png"><br>
    - 만약 레벨이 할당 되있으면 상속이 적용 되지 않는다.

- Example3<br>
    - <img width="622" alt="스크린샷 2021-03-24 오후 4 32 08" src="https://user-images.githubusercontent.com/44339530/112271597-7afce100-8cbe-11eb-9fcf-7396775a9bef.png">
    - X.Y는 할당이 안되어있기에 가장 가까운 X로 부터 상속을 받아 INFO레벨이 된다.

## Printing method
- 출력 메소드가 로깅 등급을 결정한다.
~~~
logger.setLevel(Level.INFO); // Setting Logger's level
logger.warn(“hello”);  // level of request=WARN
logger.debug(“world”); // level of request=DEBUG
~~~
<img width="610" alt="스크린샷 2021-03-24 오후 4 36 00" src="https://user-images.githubusercontent.com/44339530/112272046-05454500-8cbf-11eb-9bc6-7689a109960a.png"><br>

- 로깅 등급이 로거의 레벨보다 높거나 같아야지만 로깅 request가 활성화된다.

## Appenders
- 로깅 request는 한 개이상의 매체에 출력이 될 수 있다.
- 각 출력 매체는 appender로 표현 될 수 있다.
    - console
    - files(plain text, HTML)
    - remote socket servers
    - databases(MySQL, Oracle)

![image](https://user-images.githubusercontent.com/44339530/112272554-b3e98580-8cbf-11eb-944d-e364e2854ef6.png)<br>

- Appender도 상속이 이루어진다.
- Logger에 대해 활성화 되어있는 logging request를 보내게 되면 해당 logger에 속해 있는 모든 appender들과 계층 구조상 높은 logger에 있는 appender에 다 전송이 된다.

<img width="234" alt="스크린샷 2021-03-24 오후 4 45 31" src="https://user-images.githubusercontent.com/44339530/112273130-599cf480-8cc0-11eb-93af-e76765ee1fac.png"><br>

- Logger B에 Logging request를 날리면 File Appender 뿐만 아니라 상속받은 Logger A의 Console Appender에도 출력이 된다.
- 만약 root로그에 console appender를 넣어놓으면 나머지 로거들도 다 상속을 받아서 콘솔에 출력이 되게 됨
- additivity flag를 사용하여 상속 여부를 설정 할 수 있다.<br>
<img width="637" alt="스크린샷 2021-03-24 오후 4 48 37" src="https://user-images.githubusercontent.com/44339530/112273515-c87a4d80-8cc0-11eb-8af4-38917ffd4cab.png"><br>

### ConsoleAppender
- root로거에 ConsoleAppender를 추가하게 되면 모든 로거에서 콘솔에 출력하게 됨<br>
<img width="635" alt="스크린샷 2021-03-24 오후 4 52 11" src="https://user-images.githubusercontent.com/44339530/112273969-48a0b300-8cc1-11eb-885e-e195124ff877.png"><br>

### FileAppender
<img width="611" alt="스크린샷 2021-03-24 오후 4 52 48" src="https://user-images.githubusercontent.com/44339530/112274036-5e15dd00-8cc1-11eb-9249-7e63e63e172d.png"><br>

### RollingFileAppender
- 특정한 조건이 되면 새로운 파일에 출력을 하게 된다.
- 파일에 1년 동안 기록하게 되면 파일이 너무 커지다보니깐 일별단위로 쪼개서 출력을 한다던가 한달단위로 쪼개서 출력을 한다던가 할 떄 사용(FileAppender를 상속받음)
- Rolling Policies에 의해 조건이 설정 됨
    - TimeBasedRollingPolicy: 매 월, 주, 일, 시간, 마다 새로운 파일로 변경한다.<br>
<img width="585" alt="스크린샷 2021-03-24 오후 4 55 25" src="https://user-images.githubusercontent.com/44339530/112274353-bb119300-8cc1-11eb-89b3-1dff4c1f6c97.png"><br>

## Logback configuration
- 설정 파일이 없다면 자체적으로 디폴트로 적용 된다.
- root logger의 디폴트 설정
    - appender: ConsoleAppender
    - output format: 
    ~~~
    %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n. 
    ~~~
    - level: DEBUG
- class path에 설정 파일(logback.xml)을 다음과 같이 추가해준다.<br>
<img width="655" alt="스크린샷 2021-03-24 오후 5 01 12" src="https://user-images.githubusercontent.com/44339530/112275070-8a7e2900-8cc2-11eb-835d-d12eafcc5703.png"><br>

