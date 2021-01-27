# Kafka-실습1

### Kafka 설치
1. homebrew를 통한 kafka 설치<br>
~~~
brew install kafka
~~~

### Kafka 실행
2. Kafka를 실행하기 전 zookeeper를 먼저 시작<br>
~~~
brew services start zookeeper
~~~
<img width="524" alt="스크린샷 2021-01-02 오후 3 29 10" src="https://user-images.githubusercontent.com/44339530/103452119-44db4100-4d0f-11eb-9c54-54d78ac462eb.png"><br>

3. Kafka 시작<br>
~~~
brew services start kafka
~~~
<img width="493" alt="스크린샷 2021-01-02 오후 3 30 33" src="https://user-images.githubusercontent.com/44339530/103452140-76eca300-4d0f-11eb-814c-ea96e930a3f9.png"><br>

4. Kafka에서 기본적으로 제공하는 테스트를 위한 다양한 script의 위치 확인<br>
~~~
brew info kafka
~~~
<img width="571" alt="스크린샷 2021-01-02 오후 3 32 37" src="https://user-images.githubusercontent.com/44339530/103452168-c03cf280-4d0f-11eb-896d-43a855e478a7.png"><br>

<img width="544" alt="스크린샷 2021-01-02 오후 3 33 10" src="https://user-images.githubusercontent.com/44339530/103452176-d34fc280-4d0f-11eb-9e45-e234b5029c88.png"><br>


5. Topic생성 (파티션1개 replication은 1이고 Topic이름은 test)
~~~
./kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 -partitions 1 --topic test
~~~
<img width="1042" alt="스크린샷 2021-01-02 오후 3 35 15" src="https://user-images.githubusercontent.com/44339530/103452204-1dd13f00-4d10-11eb-9120-de2b511f4610.png"><br>

6. 로컬에 띄워진 카프카에 생성한 Topic으로 String데이터를 전송<br>
~~~
./kafka-console-producer --broker-list localhost:9092 --topic test
~~~
<img width="828" alt="스크린샷 2021-01-02 오후 3 37 17" src="https://user-images.githubusercontent.com/44339530/103452228-67218e80-4d10-11eb-899e-02b66f9e7eee.png"><br>

7. Topic으로 부터 데이터를 맨 처음 offset부터 읽어오기<br>
~~~
./kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
~~~
<img width="960" alt="스크린샷 2021-01-02 오후 3 39 26" src="https://user-images.githubusercontent.com/44339530/103452265-b36cce80-4d10-11eb-8503-1a5d64e3001d.png"><br>

### 출처
- https://www.youtube.com/watch?v=HbbI6G24LZs&list=PL3Re5Ri5rZmkY46j6WcJXQYRlDRZSUQ1j&index=7