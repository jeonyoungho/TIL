1.기본 pom.xml의 기본구조<br>
<img width="844" alt="스크린샷 2020-04-14 오후 6 49 06" src="https://user-images.githubusercontent.com/44339530/79211185-de607b00-7e80-11ea-9039-c318ba46bde4.png"><br>
-jcl-over-slf4j : 스프링이나 다양한 라이브러리들이 jcl을 사용하기 때문에 SLF4J로 통합하기 위해서 추가하는 라이브러리 (중간다리역할)<br>

2.logback으로 migration<br>
<img width="844" alt="스크린샷 2020-04-14 오후 6 54 42" src="https://user-images.githubusercontent.com/44339530/79211592-68a8df00-7e81-11ea-9fd2-797d19e9400e.png"><br>

2-1.pom.xml에 라이브러리 추가<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 19 43" src="https://user-images.githubusercontent.com/44339530/79213873-e6221e80-7e84-11ea-8b4a-045aa34a02a9.png"><br>

2-2.log4j.xml에 삭제 후 logback.xml파일 추가 및 설정<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 19 59" src="https://user-images.githubusercontent.com/44339530/79213900-efab8680-7e84-11ea-9dd7-5472ae7d3144.png"><br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 20 10" src="https://user-images.githubusercontent.com/44339530/79213917-f6d29480-7e84-11ea-8e06-ab39097f7dec.png"><br>

2-3.logback.xml설정 확인<br>
-Run -> Run configurations<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 07 58" src="https://user-images.githubusercontent.com/44339530/79212927-4e700080-7e83-11ea-8fac-0e9a54174d7f.png"><br>
-logback의 디버그 기능을 활성화 시켜서 실행<br>

2-4.run on server<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 18 27" src="https://user-images.githubusercontent.com/44339530/79213786-ba069d80-7e84-11ea-982f-05d7788e6435.png"><br>
-설정한 경로에 log파일 생성<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 21 08" src="https://user-images.githubusercontent.com/44339530/79214028-1964ad80-7e85-11ea-9756-61d902b9cd6d.png"><br>

3.Programming<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 29 19" src="https://user-images.githubusercontent.com/44339530/79215198-3d74be80-7e86-11ea-9952-441e984de474.png"><br>

-콘솔 뿐만 아니라 파일에도 출력함<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 31 23" src="https://user-images.githubusercontent.com/44339530/79215457-875da480-7e86-11ea-9b24-d6fa716fab63.png"><br>

-접속한 클라이언트들의 ip주소와 url을 출력하는 로그 설정<br>
<img width="844" alt="스크린샷 2020-04-14 오후 7 38 47" src="https://user-images.githubusercontent.com/44339530/79216083-91cc6e00-7e87-11ea-8c6b-c14628d3f721.png">
<br>
<img width="844 " alt="스크린샷 2020-04-14 오후 7 40 50" src="https://user-images.githubusercontent.com/44339530/79216267-d9eb9080-7e87-11ea-895f-12792225cb7f.png"><br>
-ip주소만 보면 어떤지역인지 파악 할 수 있음<br>

※참고자료<br>
http://www.slf4j.org/manual.html<br>
https://www.codepedia.org/ama/how-to-log-in-spring-with-slf4j-and-logback/<br>