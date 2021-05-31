# Docker설치
### 인스턴스에 접속 후 아래 커맨드 실행
~~~
[ec2-user@ip-172-31-18-132 ~]$ $ sudo yum -y upgrade
[ec2-user@ip-172-31-18-132 ~]$ $ sudo yum -y install docker
~~~

### Docker 설치 확인하기
~~~
[ec2-user@ip-172-31-18-132 ~]$ docker -v
Docker version 18.09.1, build 4c52b90
~~~

### Docker 시작하기
~~~
[ec2-user@ip-172-31-18-132 ~]$ sudo service docker start
~~~

### 그룹에 사용자 추가하기
- usermod명령어를 사용하여 그룹에 사용자인 ec2-user를 추가
~~~
[ec2-user@ip-172-31-18-132 ~]$ sudo usermod -aG docker ec2-user
~~~

# Docker-compose 설치
### 아래의 커맨드를 실행하여 Docker-compose실행
~~~
[ec2-user@ip-172-31-18-132 ~]$ sudo curl -L https://github.com/docker/compose/releases/download/1.25.0\
-rc2/docker-compose-`uname -s`-`uname -m` -o \
/usr/local/bin/docker-compose
~~~

### 실행권한 추가
- 설치 후에 chmod 명령어를 사용하여 디렉토리에 excute권한을 추가
~~~
[ec2-user@ip-172-31-18-132 ~]$ sudo chmod +x /usr/local/bin/docker-compose
~~~

### Docker-compose 설치 확인하기
~~~
[ec2-user@ip-172-31-18-132 ~]$ docker-compose -v
docker-compose version 1.25.0-rc2, build 661ac20e
~~~

#### 출처
- https://megazonedsg.github.io/1-Make-Docker/#6-docker-machine%EC%9C%BC%EB%A1%9C-aws-%EB%93%9C%EB%9D%BC%EC%9D%B4%EB%B2%84-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
