1.security-context.xml에서 security탭에서 form-login추가<br>
-login-page -> 로그인이 필요한 경우에 /login으로 리다이렉트가 이뤄짐<br>
-authentication-failure-url -> 인증 실패시 /login?error로 리다이렉트가 이뤄짐<br>
<img width="844" alt="1-" src="https://user-images.githubusercontent.com/44339530/76519816-0a18cb80-64a5-11ea-999c-bc121d003291.png"><br>
<img width="844" alt="2-" src="https://user-images.githubusercontent.com/44339530/76519825-0f761600-64a5-11ea-8350-356bd02f3b58.png"><br>

2.LoginController추가<br>
<img width="844" alt="6-" src="https://user-images.githubusercontent.com/44339530/76519831-11d87000-64a5-11ea-9a07-17ad9f169d28.png"><br>
3.login.jsp 추가<br>
-&lt;c:url value="/login"&gt; -> ContextRoot가 자동적으로 들어감<br>
-csrf 토큰 추가 해줘야함<br>
-csrf(Cross-Site-Request-Forgery) 라는 웹 공격이 있음<br>
<img width="844" alt="3-" src="https://user-images.githubusercontent.com/44339530/76519826-100eac80-64a5-11ea-9a9d-4df103fce276.png"><br>
-클라이언트가 은행사이트에 접속해서 인증된 상태에서 attack페이지에 접속시 은행사이트에 attacker에게 100달러를 보내는 form태그를 자동적으로 submit하여 자동적으로 내 계좌에서 attacker에게 100달러를 자동이체 시켜주는 Web 공격 중 하나<br>
- 은행사이트 방문시 토큰을 발행하여 Client는 토큰을 가지게 되어 은행사이트 방문할시 토큰을 request마다 전달해줘서 인증해주는 방식으로 구현해야함<br>
-발행받은 토큰을 매번 보내주기 위해 수정해줘야함<br>
<img width="844" alt="4-" src="https://user-images.githubusercontent.com/44339530/76519828-10a74300-64a5-11ea-93bb-c8ee89c707ad.png"><br>

-위에 입력받은 id와 pw를 contextroot/login로 Post 방식으로 보내주면 spring에서 자동적으로 인증절차가 이뤄지게 되있음<br>

4.security-context.xml에 /login에 대해 접근설정<br>
<img width="844" alt="5-" src="https://user-images.githubusercontent.com/44339530/76519830-113fd980-64a5-11ea-913c-162e408f5453.png"><br>

※만약 csrf 토큰을 안 보낼시 서버가 승인을 거부하게됨(forbidden)<br>
<img width="421" alt="스크린샷 2020-03-12 오후 9 07 45" src="https://user-images.githubusercontent.com/44339530/76520076-8ca18b00-64a5-11ea-8116-0807b2436b8f.png"><br>

5.Login Error 설정
-error시 /login?error로 리다이렉트가 이뤄지는데 이때 error param으로 check해서 수행됨<br>
<img width="844" alt="7-" src="https://user-images.githubusercontent.com/44339530/76519832-11d87000-64a5-11ea-90ff-ff5d10100d41.png"><br>

-error 메시지 출력<br>
<img width="844" alt="8-" src="https://user-images.githubusercontent.com/44339530/76519834-12710680-64a5-11ea-8132-f07ecb2f9594.png"><br>

6.테스트<br>
<img width="844" alt="9-" src="https://user-images.githubusercontent.com/44339530/76519840-13099d00-64a5-11ea-9824-760cf646219f.png"><br>

