# Kafka-AWS에카프카클러스터실습(설치 및 실행)
- aws의 ec2 서버 3대를 발급받아서 카프카를 설치 후 console producer와 console consumer로 연동해보는 실습<br>
- 아파치 카프카를 실행하기 위해선 2가지의 애플리케이션의 요구
    - zookeeper: 카프카 관련 정보를 저장하는 역할<br>
    - kafka<br>
### 설치
1. ec2 t2.micro 인스턴스 3대 발급 받기<br>
2. 발급받은 인스턴스 3대 모두 wget명령어로 zookeeper다운 받고 압축 풀기<br>
~~~
wget https://downloads.apache.org/zookeeper/zookeeper-3.6.2/apache-zookeeper-3.6.2-bin.tar.gz
tar xvf apache-zookeeper-3.6.2-bin.tar.gz
~~~
<img width="573" alt="스크린샷 2021-01-02 오후 4 29 12" src="https://user-images.githubusercontent.com/44339530/103452899-a7d0d600-4d17-11eb-984e-be28bf976d1e.png"><br>
<img width="530" alt="스크린샷 2021-01-02 오후 4 31 18" src="https://user-images.githubusercontent.com/44339530/103452921-f2525280-4d17-11eb-844a-aed3b287850a.png"><br>

3. zookeeper 앙상블을 구축하기 위해서 각 서버마다 주키퍼를 설정<br>
~~~
vi conf/zoo.cfg

tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
initLimit=20
syncLimit=5
server.1=test-broker01:2888:3888
server.2=test-broker03:2888:3888
server.3=test-broker02:2888:3888
~~~
<img width="332" alt="스크린샷 2021-01-02 오후 4 38 28" src="https://user-images.githubusercontent.com/44339530/103453008-f29f1d80-4d18-11eb-8274-a02e5bbb1f92.png"><br>
<img width="283" alt="스크린샷 2021-01-02 오후 4 40 42" src="https://user-images.githubusercontent.com/44339530/103453061-427de480-4d19-11eb-9a1d-d2ed610b60e4.png"><br>

4. 각 서버별로 ip가 아닌 hostname으로 통신하기 위해 /etc/hosts 파일 수정<br>
~~~
sudo vi /etc/hosts

127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
::1         localhost6 localhost6.localdomain6

0.0.0.0 test-broker03
54.180.24.106 test-broker01
13.125.221.139 test-broker02
~~~
<img width="570" alt="스크린샷 2021-01-02 오후 4 52 44" src="https://user-images.githubusercontent.com/44339530/103453216-f16ef000-4d1a-11eb-832b-aef3d940b8c5.png"><br>

5. 주키퍼를 서로 연동하기 위해서는 방화벽 설정이 필요 (aws의 security group의 2181 2888 3888 세 개의 포트를 open, 카프카 통신을 위해 9092포트도 open)<br>
![스크린샷 2021-01-02 오후 4 57 22](https://user-images.githubusercontent.com/44339530/103453275-97225f00-4d1b-11eb-9f28-d8e26b913597.png)<br>

6. open jdk 설치 후 JAVA_HOME설정<br>
~~~
sudo yum install java-1.8.0-openjdk-devel.x86_64

vi ~/.bash_profile
JAVA_HOME=/usr/lib/jvm/java-1.8.0
PATH=$PATH:$HOME/.local/bin:$HOME/bin:$JAVA_HOME/bin
~~~

6. /var/lib/zookeeper 밑에 다음과 같이 myid지정<br>
~~~
sudo vi /var/lib/zookeeper/myid
1~255사이의 id지정
~~~

7. zookeeper 시작<br>
~~~
./bin/zkServer.sh start
~~~

8. Kafka 설치<br>
~~~
wget https://archive.apache.org/dist/kafka/2.1.0/kafka_2.11-2.1.0.tgz
tar xvf kafka_2.11-2.1.0.tgz
~~~

9. server.properties의 설정<br>
- 1) broker.id를 서버 별로 각각 다른 숫자로 설정<br>
- <img width="561" alt="스크린샷 2021-01-02 오후 5 52 25" src="https://user-images.githubusercontent.com/44339530/103454044-47479600-4d23-11eb-961f-95e0d6ef8f7d.png"><br>
- 2) listener와 advertise listener도 설정<br>
- 3) 실행했던 zookeeper의 hostname과 port도 설정<br>
- <img width="551" alt="스크린샷 2021-01-02 오후 5 57 28" src="https://user-images.githubusercontent.com/44339530/103454110-fd12e480-4d23-11eb-971b-d2276b02a7cf.png"><br>

10. Kafka실행<br>
~~~
./kafka-server-start.sh ../config/server.properties
~~~

11. Topic생성<br>
~~~
./bin/kafka-topics --create --zookeeper test-broker01:2181,test-broker02:2181,test-broker03:2181/test --replication-factor 1 --partitions 1 --topic test
~~~

12. producer 실행<br>
~~~
./kafka-console-producer --broker-list 54.180.24.106:9092,13.125.221.139:9092,3.34.44.11:9092 --topic test
~~~
13. consumer 실행<br>
~~~
./kafka-console-consumer --bootstrap-server test-broker01:9092 --topic test --from-beginning
~~~

### 출처 및 참고
https://bluexmas.tistory.com/731
https://blog.voidmainvoid.net/325