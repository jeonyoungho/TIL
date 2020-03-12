<h1>Spring Security</h1>
- spring security는 filter에 의해 구현됨<br>
(오고가는 request response를 가로채서 필터에서 거르는식으로 구현됨)<br>
<img width="844" alt="스크린샷 2020-03-12 오후 7 16 55" src="https://user-images.githubusercontent.com/44339530/76511174-1ea19780-6496-11ea-9139-20a63d65b16b.png"><br><br>

1.filter패키지 및 filter 추가<br>
<img width="844" alt="스크린샷 2020-03-12 오후 7 18 46" src="https://user-images.githubusercontent.com/44339530/76511284-50b2f980-6496-11ea-881c-7f0c2e2f5433.png"><br>

2.관련 라이브러리 추가<br>
(config,web,core)<br>
<img width="844" alt="스크린샷 2020-03-12 오후 7 14 59" src="https://user-images.githubusercontent.com/44339530/76511084-f5810700-6495-11ea-9c31-bbc18ea055da.png"><br>

3.web.xml에 springSecurityFilterChain등록<br>
<img width="844" alt="1-filter등록" src="https://user-images.githubusercontent.com/44339530/76516166-4c8ada00-649e-11ea-8542-b14739b072ac.png"><br>
4.web.xml에 명시했던 springSecurityFilterChain이 request를 가로채서 권한이 없는지 없는지 체크하는데 이를 위해 Autentication(인증)과 Authorization(권한)을 명시<br>

4-1.security-contxt.xml 생성 후 namespace에서 security와 관련된거 추가<br>
<img width="844" alt="2-namespcae" src="https://user-images.githubusercontent.com/44339530/76516179-501e6100-649e-11ea-98cf-a480ab6397bd.png"><br>

※ 에러 나는 경우가 있어서 spring공식 홈페이지에서 제공하는 namespace사용 추천<br>
<img width="844" alt="3-" src="https://user-images.githubusercontent.com/44339530/76516182-5280bb00-649e-11ea-9ecf-9e716b23c30d.png"><br>

4-2.web.xml에 ContextLoaderListener가 읽어들이도록 추가<br>
<img width="844" alt="4-" src="https://user-images.githubusercontent.com/44339530/76516185-53195180-649e-11ea-951e-fae702562ce5.png"><br>
4-3.메모리상에 유저의 정보 추가<br>
-password설정은 스프링4는 plain text형식이 상관이 없는데 5부터는 허용이 안된다. encoded text형식을 사용해야함<br>
-password에 {id}encodedPassword형식으로 어떤encode를 사용했는지의 id와 실제 password형식으로 넣어줘야함<br>
-encoding은 encryption(암호화)와 hashing(해쉬)방법이 있다.<br>
-{noop}는 암호화 되있지 않았음을 no operaion
<img width="844" alt="5-" src="https://user-images.githubusercontent.com/44339530/76516187-544a7e80-649e-11ea-8e58-3aea08b229f9.png"><br>

4-4.유저의 권한 추가<br>
-auto-config -> 로그인이나 로그아웃같은 부분에 대해서 자동설정<br>
-use-expressions -> expression 사용 설정<br>
-isAuthenticated() ->인증된 사람만 허용
-permitAll -> 전부 허용
-dennyAll -> 전부 거부
-if~ else if~ else if~ 형식<br>
<img width="844" alt="6-" src="https://user-images.githubusercontent.com/44339530/76516190-54e31500-649e-11ea-837b-ab83354944b6.png"><br>

5.DB에 유저의 정보를 저장하는 방식으로 변경<br>
-data-source-ref -> DB연동이므로 datasource를 지정해줘야함<br>
-users-by-username-query -> 유저 이름을 스프링이 파악하기 위해 sql문 지정<br>
-authorities-by-username-query -> 그유저에 대한 권한을 스프링이 파악하기 위해 sql문지정<br>
<img width="844" alt="7-" src="https://user-images.githubusercontent.com/44339530/76516192-54e31500-649e-11ea-8456-8a0b570c7530.png"><br>

6.DB Table 생성 및 레코드 삽입<br>
<img width="844" alt="8-" src="https://user-images.githubusercontent.com/44339530/76516194-557bab80-649e-11ea-966e-ff61e5eb0c00.png"><br>
<img width="844" alt="9-" src="https://user-images.githubusercontent.com/44339530/76516199-56144200-649e-11ea-819c-4b76e870838c.png"><br>
<img width="844" alt="10-" src="https://user-images.githubusercontent.com/44339530/76516200-56144200-649e-11ea-8ca2-31758332de7f.png"><br>

7.hasRole('ROLE_USER') -> ROLE_USER의 권한이 있는 사람만 접근하도록 허용<br>
<img width="844" alt="11-" src="https://user-images.githubusercontent.com/44339530/76516202-56acd880-649e-11ea-9f15-e639100a292c.png"><br>

8.테스트