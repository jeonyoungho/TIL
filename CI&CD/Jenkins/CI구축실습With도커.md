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

- 설치가 끝나면 관리자 계정을 생성 후 접속한다.

#### 출처
- https://jojoldu.tistory.com/139
- https://luvstudy.tistory.com/66