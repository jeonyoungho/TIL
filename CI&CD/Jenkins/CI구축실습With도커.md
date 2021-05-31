# CI구축실습With도커

### Ec2인스턴스에 Docker 설치
- [Docker설치](https://github.com/jeonyoungho/TIL/blob/master/AWS/EC2/Docker%EC%84%A4%EC%B9%98.md)

### Docker를 활용하여 JDK11을 활용한 Jenkins설치
- 현재 jenkins의 Global Tool Configuration -> install automatically에서 Oracle JDK를 설치하는 것을 9 버전 까지만 지원하고 오라클의 라이선스 정책 변경 때문에 10 이상 버전은 더 이상 지원하지 않는다.
    - https://issues.jenkins-ci.org/browse/JENKINS-54305
- docker hub의 jenkins 공식 이미지 위치는 아래와 같다. Docker 이미지엔 범용적으로 쓰이는 버전에 대해서만 제공하고 있다.
    - https://hub.docker.com/_/jenkins

- 수동으로 설치하는 방법도 있긴 한데 그냥 jenkins 이미지 자체를 jdk 11로 실행한 경우 별도 설정 없이 편하게 java 11을 사용할 수 있다.
    - 아래의 주소에는 jdk11, slim, lts-slim, alpine 등 좀 더 다양한 버전의 jenkins를 사용할 수 있다.
    - https://hub.docker.com/r/jenkins/jenkins

- 아래의 명령어를 통해 Jenkins컨테이너 실행
    ~~~
    docker run -d -p 9090:8080 -v /jenkins:/var/jenkins_home --name jenkins jenkins/jenkins:jdk11
    ~~~
    - <b>jenkins의 포트를 인스턴스의 9090포트를 통해 바인딩시켜주었기에 security group의 9090포트를 열어줘야 한다.</b>
    - <b>인스턴스의 /jenkins 디렉토리에 컨테이너의 /var/jenkins_home디렉토리를 마운트시켜주었다.

- 인스턴스의 public아이피:9090 으로 접속하면 아래의 그림과 같이 비밀번호 입력화면이 나온다.<br>
![1](https://user-images.githubusercontent.com/44339530/120137917-510cf100-c210-11eb-9b55-58ec8827032b.png)<br>

- 젠킨스 컨테이너에 직접 접속하여 비밀번호를 찾아서 입력한다.
~~~
docker exec -it jenkins /bin/bash
cat /var/jenkins_home/secrets/initialAdminPassword
~~~

- 출력된 패스워드를 입력하면 젠킨스 설치페이지로 이동하게 된다. 여기서 <b>install suggested plugins</b>를 선택하면 많이 사용되는 플러그인들이 포함되어 자동설치되니 <b>install suggested plugins</b>를 설치한다. 다만 오류가 발생하기에 Retry로 계속 설치해줘야 하기에 시간이 다소 소요된다.<br>
![2](https://user-images.githubusercontent.com/44339530/120138203-fe800480-c210-11eb-99aa-82c0dc9eca36.png)<br>

- 설치가 끝나면 관리자 계정을 생성한다.<br>
<img width="624" alt="스크린샷 2021-05-31 오후 1 18 58" src="https://user-images.githubusercontent.com/44339530/120139120-c2e63a00-c212-11eb-9466-5270706d49d5.png"><br>

- 생성한 관리자 계정으로 접속하면 다음과 같은 초기 페이지가 보인다.<br>
<img width="891" alt="스크린샷 2021-05-31 오후 1 18 46" src="https://user-images.githubusercontent.com/44339530/120139107-bc57c280-c212-11eb-8cbb-7e32148727fe.png"><br>

### 젠킨스 설정
- 1)item등록을 버튼을 누르면 다음과 같은 페이지가 보이는데 item이름을 입력하고 Freestyle project클릭<br>
<img width="1438" alt="스크린샷 2021-05-31 오후 5 28 56" src="https://user-images.githubusercontent.com/44339530/120164769-dfe03480-c235-11eb-9043-54db72d1ea76.png">

- 2)소스 코드 관리 탭에서 git라디오 버튼을 클릭 후 Reposityory URL을 클릭하고 Credentials부분에 있는 Add버튼을 클릭<br>
<img width="780" alt="스크린샷 2021-05-31 오후 5 32 10" src="https://user-images.githubusercontent.com/44339530/120165264-58df8c00-c236-11eb-94d6-721b86a08054.png"><br>

- 3)Kind에는 'Username with password'설정 후 해당 github 프로젝트의 github 계정과 비밀번호를 입력
<img width="773" alt="스크린샷 2021-05-31 오후 5 32 54" src="https://user-images.githubusercontent.com/44339530/120165283-5d0ba980-c236-11eb-8316-4a3af101d9c8.png"><br>

- 4)branch의 경우 main브런치에 push시에 젠킨스 빌드가 관리 되도록 main을 입력하였습니다. 만약 develop브랜치에 push시에 빌드되도록 하신다면 */develop를 입력하면 됨<br>
<img width="788" alt="스크린샷 2021-05-31 오후 5 33 10" src="https://user-images.githubusercontent.com/44339530/120165295-609f3080-c236-11eb-8524-d617ba99fb03.png"><br>

- 5)build trigger는 github hook trigger를 선택<br>
<img width="912" alt="스크린샷 2021-05-31 오후 5 36 56" src="https://user-images.githubusercontent.com/44339530/120165740-d4413d80-c236-11eb-9dc4-69067fd02c54.png"><br>


- 6)마지막으로 Github에서 push가 올 경우 실행할 빌드를 등록한다. 아래 Build를 클릭해서 execute shell을 선택 후 빌드스크립트 작성
    - 이미 프로젝트에 gradle 실행파일인 gradlew가 포함되어있기 때문에 별도로 gradle을 설치하지 않고 gradlew를 사용하도록 작성
    - clean 수행 후, 이전에 작성한 task인 print를 수행하도록 설정하면 빌드 수행시 print가 수행되었는지 확인할 수 있음
<img width="928" alt="스크린샷 2021-05-31 오후 5 40 16" src="https://user-images.githubusercontent.com/44339530/120166193-4d409500-c237-11eb-984d-6ffa83dcb7b7.png"><br>
<img width="922" alt="스크린샷 2021-05-31 오후 5 39 29" src="https://user-images.githubusercontent.com/44339530/120166178-4a45a480-c237-11eb-8191-823d9e03a4f7.png"><br>


### Github 설정
- 1)해당 Repository에서 Settings클릭<br>
<img width="917" alt="스크린샷 2021-05-31 오후 5 44 08" src="https://user-images.githubusercontent.com/44339530/120166799-f38c9a80-c237-11eb-8703-525573369ff5.png"><br>

- 2)Webhooks클릭<br>
<img width="805" alt="스크린샷 2021-05-31 오후 5 44 33" src="https://user-images.githubusercontent.com/44339530/120166812-f7202180-c237-11eb-98d1-882932d9295e.png"><br>

- 3)Payload url부분에 ngrok로 터널링된 jenkins페이지의 url에 '/github-webhook/'을 붙여서 입력<br>
<img width="657" alt="스크린샷 2021-05-31 오후 5 46 36" src="https://user-images.githubusercontent.com/44339530/120166995-2d5da100-c238-11eb-9a1b-e33d3b701fa1.png"><br>

### 테스트
- 1)등록한 Item을 클릭 후 Build Now를 클릭하면 바로 빌드를 수행하게 됨<br>
<img width="942" alt="스크린샷 2021-05-31 오후 5 48 40" src="https://user-images.githubusercontent.com/44339530/120167327-92b19200-c238-11eb-89a4-39ca6e14e9d2.png"><br>

- 2)Build History에서 가장 최근에 빌드된 내역을 클릭하시고 Console Output 을 클릭하면 빌드가 정상적으로 수행되고 등록한 빌드스크립트가 정상적으로 실행된 것을 확인할 수 있음<br>
<img width="1087" alt="스크린샷 2021-05-31 오후 5 50 46" src="https://user-images.githubusercontent.com/44339530/120167562-da381e00-c238-11eb-9401-439f7eb9d032.png"><br>
<img width="1053" alt="스크린샷 2021-05-31 오후 5 51 12" src="https://user-images.githubusercontent.com/44339530/120167577-de643b80-c238-11eb-9119-bec3ae2baa37.png"><br>

- 3)프로젝트 수정 후, git push를 수행하게 되면 github hook을 통해 자동으로 빌드가 수행되는지 확인하면 아래와 같이 github로 인해서 빌드가 시작되었다는 메시지를 확인할 수 있음. 빌드 후에 있을 모든 일들은 위에서 작성했던 빌드스크립트를 통해 진행하면 됨.<br>
<img width="1232" alt="스크린샷 2021-05-31 오후 5 55 44" src="https://user-images.githubusercontent.com/44339530/120168100-782be880-c239-11eb-86c7-038d24b2dfaf.png">

#### 출처
- https://jojoldu.tistory.com/139
- https://luvstudy.tistory.com/66
- https://nesoy.github.io/articles/2017-02/Jenkins