1.pom.xml에 hibernate validator 라이브러리 추가<br>
<img width="844" alt="스크린샷 2020-04-06 오후 5 19 57" src="https://user-images.githubusercontent.com/44339530/78537710-d7a38980-782a-11ea-8b7d-92b4d27382d4.png"><br>

2.Model에 제약조건 및 에러메시지 정의<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 10 17" src="https://user-images.githubusercontent.com/44339530/78542259-e04b8e00-7831-11ea-8b57-165dbc81f79e.png"><br>

3.controller에 @Valid어노테이션 및 BindingResult객체 추가<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 09 59" src="https://user-images.githubusercontent.com/44339530/78542227-d4f86280-7831-11ea-93bb-f32d96e39ccb.png"><br>

4.View에 에러메시지 출력<br>
-BindingResult객체도 model에 들어가므로 이 객체를 이용해 view에 출력<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 00 22" src="https://user-images.githubusercontent.com/44339530/78541375-7da5c280-7830-11ea-9a4f-816ceed0afcd.png"><br>

-구현<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 01 55" src="https://user-images.githubusercontent.com/44339530/78541516-b5146f00-7830-11ea-9482-e069c9319496.png">

5.예시)<br>
-Controller<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 14 04" src="https://user-images.githubusercontent.com/44339530/78542624-6667d480-7832-11ea-9881-0b49f3e3bdf5.png"><br>

-Model<br>
<img width="844" alt="스크린샷 2020-04-06 오후 6 15 09" src="https://user-images.githubusercontent.com/44339530/78542728-8dbea180-7832-11ea-9e16-6656b8fc38f0.png"><br>

-JSP<br>
<img width="649" alt="스크린샷 2020-04-06 오후 6 04 58" src="https://user-images.githubusercontent.com/44339530/78541770-218f6e00-7831-11ea-8e66-df52227c090b.png">


