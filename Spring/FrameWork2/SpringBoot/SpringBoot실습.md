<h1>Spring Boot프로젝트 시작하기</h1>
1.프로젝트 생성<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 00 41" src="https://user-images.githubusercontent.com/44339530/84145138-4f449d80-aa94-11ea-80ee-408530885237.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 01 07" src="https://user-images.githubusercontent.com/44339530/84145157-58356f00-aa94-11ea-87dc-58ed0522943e.png"><br>

2.프로젝트 구조<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 05 33" src="https://user-images.githubusercontent.com/44339530/84145504-f6c1d000-aa94-11ea-8a4a-68f1b3649437.png"><br>

-src/main/java -> java코드가 들어가는 부분<br>
-src/main/resources<br>
static -> css, javascript, 이미지와 같은 정적인 파일들<br>
templates -> html파일<br>
application.properties -> 설정 파일<br>

jar로 패키징할 경우 /src/main/webapp을 사용하지 마라<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 11 24" src="https://user-images.githubusercontent.com/44339530/84145941-c75f9300-aa95-11ea-8873-b97cd2f1d056.png"><br>

3.pom.xml<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 12 31" src="https://user-images.githubusercontent.com/44339530/84146027-efe78d00-aa95-11ea-9f01-a3a25f4a4fae.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 12 38" src="https://user-images.githubusercontent.com/44339530/84146034-f37b1400-aa95-11ea-9177-3011dac0ff5b.png"><br>

-아래를 꼭 넣어줘야함<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 12 46" src="https://user-images.githubusercontent.com/44339530/84146043-f83fc800-aa95-11ea-8cfe-704564068c96.png"><br>

-Spring Boot Maven Plugin 메이븐 속 스프링부트를 서포트 해준다<br>
-이 플러그인을 통해서 jar나 war파일로 패키징 할 수 잇게 해주고 실제로 이를 실행시켜줄 수 있다.<br>
-아래와 같이 사용 할 수 도 있다.<br>
mvn package (패키징 할 때)<br>
mvn spring-boot:run (이런식으로 실행시켜줄 수도 있다.)<br>

-로컬에 maven이 설치 안되있을 경우 아래와 같이 해줘야한다.<br>
mvnw package<br>
mvnw spring-boot:run<br>

4.Application Endpoints<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 20 05" src="https://user-images.githubusercontent.com/44339530/84146662-fde9dd80-aa96-11ea-8bbc-96a3810baf18.png"><br>

@SpringBootApplication = @EnableAutoConfiguration + @ComponentScan + @Configuration<br>

@EnableAutoConfiguration -> Auto Config를 활성화 시켜준다.<br>
@ComponentScan -> 이 클래스가 있는 패키지 및 하위패키지들을 모두 스캔 해 준다.<br>
@Configuration -> 설정파일에 관련 클래스임을 지정<br>

-Run을 수행하게 되면 톰캣이 시작되면서 어플리케이션 시작되게 되어있음<br>

5.Spring MVC<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 24 15" src="https://user-images.githubusercontent.com/44339530/84146988-93856d00-aa97-11ea-850c-5989513968e6.png"><br>
-thymeleaf -> sever-side에서 돌아가는 엔진<br>

6.Spring Security<br>
-pom.xml에 라이브러리 추가<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 27 04" src="https://user-images.githubusercontent.com/44339530/84147210-f840c780-aa97-11ea-9c82-232d94b136dd.png"><br>

-security 관련 설정<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 27 37" src="https://user-images.githubusercontent.com/44339530/84147252-0b539780-aa98-11ea-91c8-501cb3f46a43.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 28 21" src="https://user-images.githubusercontent.com/44339530/84147315-258d7580-aa98-11ea-887b-8a67dd2cfc6b.png"><br>

7.Application.properties<br>
-application관련 설정 파일<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 34 00" src="https://user-images.githubusercontent.com/44339530/84147787-ef9cc100-aa98-11ea-8420-1af09792d7ba.png"><br>

8.Application Run<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 35 55" src="https://user-images.githubusercontent.com/44339530/84147961-34285c80-aa99-11ea-8be7-08a93374ccdd.png"><br>

-실습<br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 38 23" src="https://user-images.githubusercontent.com/44339530/84148178-8c5f5e80-aa99-11ea-9197-b0c90a16036d.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 39 25" src="https://user-images.githubusercontent.com/44339530/84148264-b153d180-aa99-11ea-85b0-2d4fe796380d.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 39 34" src="https://user-images.githubusercontent.com/44339530/84148276-b6b11c00-aa99-11ea-9c3e-374e4d8db78d.png"><br>
<img width="844" alt="스크린샷 2020-06-09 오후 9 40 45" src="https://user-images.githubusercontent.com/44339530/84148381-e102d980-aa99-11ea-8b84-26bd118944b3.png"><br>




