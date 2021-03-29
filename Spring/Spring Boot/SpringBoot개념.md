<h1>Spring Boot</h1>
<strong>생산성 높은 코드란?</strong><br>
-Cost -> 비용을 줄여 줄 수 있음<br>
-Time to Market (Perfection vs Done) ->덜 완전하더라도  적시에 출시하는게 중요하다<br>

Spring MVC -> Spring Boot<br>
-스프링을 사용하는 것 보단 좀 더 생산성을 높여 주기 위해<br>

1)수동적인 설정 부분들을 줄여줄 수 있다.<br>
2)dependency 충돌 해결을 도와줄 수 있다.<br>
3)내장 서버를 포함하고 있다.<br>
4)Spring Boot는 Spring Framework의 technology들을 그대로 활용한다.
<img width="844" alt="스크린샷 2020-06-09 오후 8 08 35" src="https://user-images.githubusercontent.com/44339530/84140712-02a99400-aa8d-11ea-9d65-112be08693bd.png"><br>


<h1>Spring Boot의 특징</h1>
1.Auto Configuration<br>
-기존의 스프링 프레임워크는 아래와 같은 복잡한 설정들을 매번 해줘야한다<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 11 09" src="https://user-images.githubusercontent.com/44339530/84140962-5e741d00-aa8d-11ea-8209-38667f1643a2.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 12 11" src="https://user-images.githubusercontent.com/44339530/84141039-819ecc80-aa8d-11ea-843b-7b9431495d49.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 12 19" src="https://user-images.githubusercontent.com/44339530/84141050-86638080-aa8d-11ea-973f-3233f43a4f53.png"><br>

-매번 프로젝트를 시작 할 때마다 이런 설정들을 복붙해야하는데 이는 좋지 않다.<br>
-그래서 Spring이 이런한 bean설정들을 자동으로 설정해주고 그 다음 우리가 필요하면 추가해주는 방식으로 이뤄진다.<br>
-CLASSPATH에 어떠한 라이브러리들이 있는 쭉 살펴서 필요한 bean들을 자동 설정해준다.<br>
-예시1)Spring MVC jar라이브러리가 classpath에 있다? -> Dispatcher Servlet을 자동 설정 해줌<br>
-예시2)Hibernate jar라이브러리가 classpath에 있따? -> DataSource에 대한 부분들을 자동 설정 해줌<br>
-필요하다면 직접 설정을 해줄 수 도 있다.<br><br>

<img width="844" alt="스크린샷 2020-06-09 오후 8 19 32" src="https://user-images.githubusercontent.com/44339530/84141681-887a0f00-aa8e-11ea-8c9c-d652f4f3f73e.png"><br>
-pom.xml에 라이브러리 의존성을 추가해주면 spring boot가 알아서 이와 관련된 bean들을 생성해주고 application.properties에 필요한 property만 세팅해주면 된다.<br>


2.Easy dependency Management<br>
-기존에 spring mvc를 위해 아래와 같은 라이브러리들을 일일이 설정해줘야했다.<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 22 00" src="https://user-images.githubusercontent.com/44339530/84141913-e0187a80-aa8e-11ea-96b8-109288670e98.png"><br>

-그렇지만 버전의 호환성까지 알아서 설정해주는게 번거로웠다.<br>
-이를 위해 spring-boot-starter-를 통해서 미리 설정된 대부분의 흔하게 사용되는 라이브러리 의존성들을 한 번에 해결 해줄 수 있다.<br>
-만약 web프로젝트를 진행한다? 아래와 같이 spring-boot-starter-web하나만 추가해주면 이안에 아래 6가지 라이브러리들의 버전까지 알아서 모두 포함된다.<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 25 56" src="https://user-images.githubusercontent.com/44339530/84142261-6d5bcf00-aa8f-11ea-8c8f-1e5003c5204a.png"><br>

-30개이상의 spring boot starter들이 존재한다.<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 27 59" src="https://user-images.githubusercontent.com/44339530/84142416-b6ac1e80-aa8f-11ea-8cb0-0674ba941954.png"><br>

-pom.xml도 계층 구조로 이뤄져있다. 이로 인해 상속의 개념이 적용가능하다.<br>

3.Embedded Servlet Container<br>
-war가 아닌 jar로 패키징하게 되면 tomcat과 같은 서블릿 컨테이너가 내장 되어 있게됨<br>

4.SpringBoot Actuator<br>
-Rest 자체 API를 제공하기 때문에 application을 모니터링하고 tracing할수가 있다.<br>
-/beans로 모든 bean들을 조회할 수 가 있다.<br>
-/mappings로 URL을 볼 수 가있다.<br>
-/health로 application의 상태를 볼 수 가 있다.<br>
-아래 라이브러리들을 pom.xml에 추가해줘야한다.<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 38 24" src="https://user-images.githubusercontent.com/44339530/84143224-2a9af680-aa91-11ea-9497-c7e2252e3f5f.png"><br>

-디폴트로 /health와 /info가 노출이 되고 prefix로는 /actuator가 있다.<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 48 10" src="https://user-images.githubusercontent.com/44339530/84144055-93cf3980-aa92-11ea-9c0d-144fe3e3102d.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 47 16" src="https://user-images.githubusercontent.com/44339530/84143960-68e4e580-aa92-11ea-9def-76846aae62fc.png"><br>



-아래와 같이 endpoint를 직접 설정해주면 됨<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 49 48" src="https://user-images.githubusercontent.com/44339530/84144167-c37e4180-aa92-11ea-9864-e52870533eaa.png"><br>

-Request매핑 정보도 볼 수 있음<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 52 07" src="https://user-images.githubusercontent.com/44339530/84144357-1657f900-aa93-11ea-8bf1-1d3611f61d58.png">

-bean들의 정보들을 전부 볼 수도 있음<br>
<img width="844" alt="스크린샷 2020-06-09 오후 8 52 49" src="https://user-images.githubusercontent.com/44339530/84144424-2f60aa00-aa93-11ea-8bca-9860db09402e.png"><br>
