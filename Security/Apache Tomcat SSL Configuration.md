<h1> Apache Tomcat SSL Configuration </h1>

1.Creating the KeyStore
-서버는 인증서가 반드시 있어야하는데 KeyStore를 만들고 인증서를 저장한다<br>
-자바에서는 keytool이라 불리는 command-line tool을 제공한다<br>
-이를 사용해서 KeyStore를 만들수 있고 여기에 인증서를 넣어 둘 수 있다<br>
-인증서로서 test목적으로 self-signed인증서를 사용한다 ( 자기 자신이 직접 서명한 인증서)<br>
-keytool을 사용해서 self-signed인증서를 만들어서 KeyStore에 저장<br>

1-1)keystore라는 디렉토리 생성<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 04 07" src="https://user-images.githubusercontent.com/44339530/82656616-03ee5a80-9c5f-11ea-818f-fe99898bc7fb.png"><br>
1-2)KeyStore 생성<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 09 59" src="https://user-images.githubusercontent.com/44339530/82657140-d5bd4a80-9c5f-11ea-815e-9818b152c15d.png"><br>

1-3)확인<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 12 20" src="https://user-images.githubusercontent.com/44339530/82657340-29c82f00-9c60-11ea-9dd6-fa7355bad3e2.png"><br>

※ JKS : Java에서 사용되는 KeyStore format<br>

※ 참고자료<br>
<img width="844" alt="스크린샷 2020-05-22 오후 6 58 41" src="https://user-images.githubusercontent.com/44339530/82656160-43687700-9c5e-11ea-929f-aec14cb72356.png"><br>

2.Configuring Tomcat to use SSL<br><br>

2-1)server.xml의 SSL과 관련된 부분 주석 해제<br>

<img width="844" alt="스크린샷 2020-05-22 오후 7 20 25" src="https://user-images.githubusercontent.com/44339530/82658012-4a44b900-9c61-11ea-90c4-dca6af9eed9c.png"><br>

<img width="844" alt="스크린샷 2020-05-22 오후 8 17 04" src="https://user-images.githubusercontent.com/44339530/82662655-356c2380-9c69-11ea-98cd-a27856fe00ce.png"><br>

-이렇게 설정해주면 tomcat이 keystore를 접근해서 서버인증서를 가져 오게 됨<br>

2-2) tomcat 재시작<br>
$ cd /opt/tomcat/logs<br>
$ cat catalina.out<br>
->이런식으로 로그를 보면 길기 때문에 아래와 같이 새로 생성되는 로그 메시지들만 확인<br>
$ tail -f /opt/tomcat/logs/catalina.out <br>

<img width="567" alt="스크린샷 2020-05-22 오후 7 28 37" src="https://user-images.githubusercontent.com/44339530/82658691-70b72400-9c62-11ea-9f67-7f13856720bb.png"><br>

-터미널 하나 더 생성해서 sudo systemctl restart tomcat으로 tomcat재시작 수행 후 log메시지 확인<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 29 53" src="https://user-images.githubusercontent.com/44339530/82658803-9e9c6880-9c62-11ea-945a-4aa8621ed51e.png"><br>

-AWS 보안 그룹에서 8443포트 번호 열어주기<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 31 08" src="https://user-images.githubusercontent.com/44339530/82658913-cab7e980-9c62-11ea-8781-d728da7ca160.png"><br>
<img width="844" alt="스크린샷 2020-05-22 오후 8 48 33" src="https://user-images.githubusercontent.com/44339530/82664852-9b5aaa00-9c6d-11ea-82b0-871da86ae2c4.png"><br>

2-3)8443포트로 HTTPS를 이용한 접속<br>
<img width="844" alt="스크린샷 2020-05-22 오후 8 44 38" src="https://user-images.githubusercontent.com/44339530/82664622-0f488280-9c6d-11ea-9a04-5530d84a07f8.png"><br>
<img width="844" alt="스크린샷 2020-05-22 오후 8 45 10" src="https://user-images.githubusercontent.com/44339530/82664656-21c2bc00-9c6d-11ea-94dc-9be60647cdeb.png"><br>

※ 나중에 인증기관에서 정식으로 발급 받아야함!!!<br>
※ Mac OS로 인하여 크롬 자체에서 안전한 페이지로 이동하는 것 조차 차단시킴<br>
<img width="775" alt="스크린샷 2020-05-22 오후 8 46 41" src="https://user-images.githubusercontent.com/44339530/82664741-5898d200-9c6d-11ea-97db-f75466675b17.png"><br>

※ 참고자료<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 21 08" src="https://user-images.githubusercontent.com/44339530/82658084-647e9700-9c61-11ea-9137-308cbab7dca4.png"><br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 21 58" src="https://user-images.githubusercontent.com/44339530/82658171-82e49280-9c61-11ea-8a17-05bdfd59aed9.png"><br>

3.Web.xml (Limiting SSL Usage)<br>
-한 사이트로 htp, https 모두로 접근 할 수 있는건 바람직하지 않다<br>
-이에 대해서 Https만 제공하기 위해 설정이 필요함<br><br>
3-1)Web.xml에 다음과 같이 설정<br>
<img width="844" alt="스크린샷 2020-05-22 오후 8 54 13" src="https://user-images.githubusercontent.com/44339530/82665264-6569f580-9c6e-11ea-9845-f68db65a159a.png"><br>

<img width="844" alt="스크린샷 2020-05-22 오후 8 58 50" src="https://user-images.githubusercontent.com/44339530/82665578-0bb5fb00-9c6f-11ea-9450-8aea8c19fd20.png"><br>

3-2)Tomcat 재시작<br>
<img width="474" alt="스크린샷 2020-05-22 오후 8 59 15" src="https://user-images.githubusercontent.com/44339530/82665604-1bcdda80-9c6f-11ea-8fa6-b4526c6110cd.png"><br>

-다음과 같이 HTTP 8080으로 접속해도 HTTPS 8443포트로 Redirection이 일어남<br>
<img width="844" alt="스크린샷 2020-05-22 오후 9 01 16" src="https://user-images.githubusercontent.com/44339530/82665731-62233980-9c6f-11ea-8e36-0db711771f40.png"><Br>

<img width="844" alt="스크린샷 2020-05-22 오후 9 03 04" src="https://user-images.githubusercontent.com/44339530/82665878-a4e51180-9c6f-11ea-905b-9c4c8609c09e.png"><br>

-conf/server.xml에 다음과 같이 Redirection을 설정하였기 때문에 Redirection이 일어남<br>
<img width="844" alt="스크린샷 2020-05-22 오후 9 04 14" src="https://user-images.githubusercontent.com/44339530/82665947-cba34800-9c6f-11ea-9a61-d760e924c395.png">

※ 참고자료<br>
<img width="844" alt="스크린샷 2020-05-22 오후 8 51 16" src="https://user-images.githubusercontent.com/44339530/82665072-fbe9e700-9c6d-11ea-99a4-1f5f3d5aef19.png"><br>


※ http로 구현시 password 메시지가 노출이 될 수 있기에 최근의 대부분 사이트들은 ssl을 사용하는 https로 연결함<br>
※ CA에서 정식으로 인증서만 발급받아서 KeyStore에 저장하고 위와 같이 설정해주면 됨<br>





