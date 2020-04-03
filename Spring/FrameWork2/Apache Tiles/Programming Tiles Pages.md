<h1>Programming Tiles Pages</h1>
1.pom.xml에 라이브러리 추가<br>
<img width="606" alt="스크린샷 2020-03-31 오후 5 05 49" src="https://user-images.githubusercontent.com/44339530/78002261-e8a75300-7371-11ea-8e57-fdbf9e785bef.png">
<br>

2.Spring 설정<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 11 57" src="https://user-images.githubusercontent.com/44339530/78002834-bc400680-7372-11ea-88df-44cb8a653b06.png"><br>
-이제 컨트롤러에서 view의 이름을 리턴하는 대신 definition의 이름 리턴<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 33 08" src="https://user-images.githubusercontent.com/44339530/78004865-b1d33c00-7375-11ea-8789-1926f7bacfbc.png"><br>

3./tiles-def/tiles.xml property작성 및 /tiles/menu.jsp, footer.jsp, template.jsp 생성<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 30 07" src="https://user-images.githubusercontent.com/44339530/78004628-543eef80-7375-11ea-92d0-000002abfaf1.png">
<br>

※참고자료 https://tiles.apache.org/framework/tutorial/basic/pages.html<br>

4.추가 definition 정의<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 36 16" src="https://user-images.githubusercontent.com/44339530/78005162-21e1c200-7376-11ea-8ba4-ab8dad576da1.png"><br>

5.layout.jsp작성<br>
<img width="1250" alt="스크린샷 2020-03-31 오후 5 41 56" src="https://user-images.githubusercontent.com/44339530/78005719-ec89a400-7376-11ea-8359-8ce251c5f104.png"><br>

6.home.jsp에서 header부분을 짤라서 header.jsp작성<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 43 36" src="https://user-images.githubusercontent.com/44339530/78005865-278bd780-7377-11ea-8541-4b8c31f68638.png"><br>


7.footer.jsp작성<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 45 25" src="https://user-images.githubusercontent.com/44339530/78006049-691c8280-7377-11ea-9a8c-fbd33d9e725f.png"><br>


8.home.jsp , product.jsp작성<br>

9.테스트<br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 50 40" src="https://user-images.githubusercontent.com/44339530/78006567-23ac8500-7378-11ea-8d9b-b756a4828943.png"><br>
<img width="844" alt="스크린샷 2020-03-31 오후 5 50 55" src="https://user-images.githubusercontent.com/44339530/78006593-2c9d5680-7378-11ea-8762-38f5bb45969f.png"><br>

