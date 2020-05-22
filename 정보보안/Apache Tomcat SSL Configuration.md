<h1> Apache Tomcat SSL Configuration </h1>

1.Creating the KeyStore
-서버는 인증서가 반드시 있어야하는데 KeyStore를 만들고 인증서를 저장한다<br>
-자바에서는 keytool이라 불리는 command-line tool을 제공한다<br>
-이를 사용해서 KeyStore를 만들수 있고 여기에 인증서를 넣어 둘 수 있다<br>
-인증서로서 test목적으로 self-signed인증서를 사용한다 ( 자기 자신이 직접 서명한 인증서)<br>
-keytool을 사용해서 self-signed인증서를 만들어서 KeyStore에 저장<br>
<img width="844" alt="스크린샷 2020-05-22 오후 6 58 41" src="https://user-images.githubusercontent.com/44339530/82656160-43687700-9c5e-11ea-929f-aec14cb72356.png"><br>

1-1)keystore라는 디렉토리 생성<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 04 07" src="https://user-images.githubusercontent.com/44339530/82656616-03ee5a80-9c5f-11ea-818f-fe99898bc7fb.png"><br>
1-2)KeyStore 생성<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 09 59" src="https://user-images.githubusercontent.com/44339530/82657140-d5bd4a80-9c5f-11ea-815e-9818b152c15d.png"><br>

1-3)확인<br>
<img width="844" alt="스크린샷 2020-05-22 오후 7 12 20" src="https://user-images.githubusercontent.com/44339530/82657340-29c82f00-9c60-11ea-9dd6-fa7355bad3e2.png"><br>

*JKS : Java에서 사용되는 KeyStore format<br>

2.Configuring Tomcat to use SSL<br>

2-1)server.xml의 SSL과 관련된 부분 주석 해제<br>

<img width="844" alt="스크린샷 2020-05-22 오후 7 20 25" src="https://user-images.githubusercontent.com/44339530/82658012-4a44b900-9c61-11ea-90c4-dca6af9eed9c.png"><br>

<img width="844" alt="스크린샷 2020-05-22 오후 7 16 46" src="https://user-images.githubusercontent.com/44339530/82657717-c8549000-9c60-11ea-9453-c4fa59362cbe.png"><br>

<img width="844" alt="스크린샷 2020-05-22 오후 7 19 37" src="https://user-images.githubusercontent.com/44339530/82657961-2e411780-9c61-11ea-8cf7-374466f89df2.png">

<img width="844" alt="스크린샷 2020-05-22 오후 7 21 08" src="https://user-images.githubusercontent.com/44339530/82658084-647e9700-9c61-11ea-9137-308cbab7dca4.png"><br>

<img width="844" alt="스크린샷 2020-05-22 오후 7 50 18" src="https://user-images.githubusercontent.com/44339530/82660509-78c49300-9c65-11ea-97b5-767df7da6e56.png">

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


<img width="844" alt="스크린샷 2020-05-22 오후 7 21 58" src="https://user-images.githubusercontent.com/44339530/82658171-82e49280-9c61-11ea-8a17-05bdfd59aed9.png">

