# Kafka개요

### MessageQueue
- 데이터를 전송하는 애플리케이션과 데이터를 수신 받는 애플리케이션의 개수가 늘어날 수 록 데이터 전송라인이 많아 지게 됨<br>
- 데이터 전송라인이 많아지면 배포와 장애에 대응하기 어려움<br>
- 데이터를 전송할 때의 프로토콜의 파편화가 심각해짐<br>
- 추후 데이터의 포맷내부에 변경이 있을 때 유지보수하기 매우 어려워짐<br>
- 카프카는 이런 복잡함을 해결하기 위해 링크드인에서 내부적으로 개발하였고 현재는 오픈 소스로 제공<br>
- Source Application과 TargetAppliction의 커플링을 약하게 해줌
- Source Application은 카프카에 데이터를 전송하면 되고 Target Application은 데이터를 가져오기만 하면 됨<br>
- 방대한 양의 클릭로그, 결제로그와 같은 데이터들을 안전하게 처리
- json, tsv, avo등 여러 데이터 포맷을 지원함<br>
- Kafka Producer는 데이터를 집어넣는 역할, 즉 Source Application이 되며 Kafka Consumer는 데이터를 빼서 쓰는 역할을 함<br>
- Producer, Consumer는 라이브러리로 구현되어 다양한 언어로 지원<br>
- 낮은 지연과 높은 처리량으로 대량의 데이터를 효과적으로 처리<br>
<img width="878" alt="스크린샷 2020-11-30 오후 10 15 43" src="https://user-images.githubusercontent.com/44339530/100614510-97fd5500-3359-11eb-9d71-379cbdc2414b.png"><br>

### Topic
- <img width="878" alt="스크린샷 2020-11-30 오후 10 24 26" src="https://user-images.githubusercontent.com/44339530/100615336-d0516300-335a-11eb-9166-86674444b164.png"><br>
- 카프카에서는 토픽을 여러 개 생성 가능<br>
- 목적에 따라 무슨 데이터를 담는지 명확히 명시하여 유지보수가 편리함<br>
- 하나의 파티션은 내부의 0번 인덱스부터 순서대로 쌓이게 됨<br>
- 컨슈머가 붙으면 데이터를 낮은 인덱스의 데이터부터 가져가게 됨<br>
- 컨슈머가 파티션 내부에서 데이터를 가져가더라도 데이터는 삭제되지 않음<br>
- 새로운 컨슈머가 붙었을때 0번 부터 새로 가져가게 됨 (단, 컨슈머 그룹이 달라야 하고 auto.offset.reset=earliest로 설정되있어야함)<br>
- 데이터의 유실도 방지하며 동일 데이터를 여러 번 사용 가능
- 클릭로그를 분석하고 시각화하기 위해 엘라스틱 서치에 저장하기도 하고 백업하기 위해 Hadoop에 저장하기도 함<br>
- <img width="878" alt="스크린샷 2020-11-30 오후 10 26 25" src="https://user-images.githubusercontent.com/44339530/100615561-173f5880-335b-11eb-94e8-220341a608f0.png"><br>
- 다음 그림과 같이 키가 null이고, 기본 파티셔너를 사용할 경우 데이터는 라운드로빈 방식으로 할당이되고 키가 있고 기본파티셔너를 사용할 경우 키의 해시 값을 구하여 특정 파티션에 할당되어짐<br>
- 파티션을 늘리면 컨슈머의 개수를 늘려서 데이터 처리를 분산시킬 수 있음<br>
- 파티션을 늘리는 건 가능하지만 줄일 수는 없음<br>
- 파티션의 데이터는 옵션에 따라 삭제되는 시기가 다름<br>
  - log.retention.ms: 최대 record보존 기간<br>
  - log.retention.byte: 최대 record보존 크기(byte)<br>

### Producer
- 데이터를 카프카에 보내는 역할<br>
- 대량의 데이터를 실시간으로 카프카에 적재할 수 있음<br>
- 프로듀서의 역할<br>
  - Topic에 해당하는 메시지를 생성<Br>
  - 특정 Topic으로 데이터를 publish<br>
  - 처리 실패/재시도 (전송성공 여부를 알 수 있고 재시도 할 수 있음)<br>
- Gradle이나 Maven으로 라이브러리를 가져 올 수 있으며 Client버전과 Broker버전을 일치시켜주는게 좋음<br>
~~~
// gradle
compile group: 'org.apache.kafka', name: 'kafka-clients', version: '2.3.0'

//maven
<dependency>
  <groupId>org.apache.kafka</groupId>
  <artifactId>kafka-clients</artifactId>
  <version>2.3.0</version>
</dependency>
~~~
- 예제 코드<br>
  - <img width="878" alt="스크린샷 2020-11-30 오후 10 34 58" src="https://user-images.githubusercontent.com/44339530/100616322-4904ef00-335c-11eb-85bd-bec544f6fca0.png"><br>
- 브로커의 주소목록은 2개 이상의 ip와 port를 지정하도록 권장 (한 개 브로커가 비정상일 경우 다른 브로커를 사용할 수 있기 때문에)<br>
- StringSerializer는 key혹은 value를 직렬화하기 위해 사용(byte array, string, integer 시리얼라이즈 사용 가능)<br>
- 키는 메시지를 보내면, 토픽의 파티션이 지정될 때 쓰임<br>
- ProduceRecord를 생성할 때 인자로 토픽하고 key, value를 지정<br>
~~~
producer.send(new ProducerData<String, String>("TopicName", "KeyName", "Value"));
~~~
- 카프카는 키를 특정한 해쉬값으로 변환시켜 파티션과 1대1 매칭을 시키게 됨<br>
- 키와 파티션의 매칭이 깨지게되면 일관성이 보장되지 않음<br>

###  Broker
- 카프가 설치되어 있는 서버 단위<br>
- 보통 세 개 이상의 브로커를 구성하여 사용하는 것을 권장<br>
- <img width="878" alt="스크린샷 2020-11-30 오후 10 46 20" src="https://user-images.githubusercontent.com/44339530/100617449-deed4980-335d-11eb-8f2a-530cc65a0fd6.png"><br>
  - <b>replication</b>은 파티션의 복제를 뜻함<br>
  - 클러스터에서 서버에 장애가 생겼을 때 가용성을 보장<br>
  - <b>카프카 아키텍쳐의 핵심</b><br>
    - 만약 repliaction이 1이라면 파티션이 1개만 존재<br>
    - 만약 repliaction이 2이라면 원본 파티션1개, 복제본 파티션1개 존재<br>
    - 만약 repliaction이 3이라면 원본 파티션1개, 복제본 파티션2개 존재<br>
    - 여기서 원본 파티션은 Leader Partion이라 부르고 복제본 파티션은 Follower Partion이라 부름<br>
    - <img width="878" alt="스크린샷 2020-11-30 오후 10 50 56" src="https://user-images.githubusercontent.com/44339530/100617943-836f8b80-335e-11eb-8c35-afd6e199000f.png">
    - 다만 브로커 개수에 따라 replication의 개수는 제한이 됨<br>
    - replication의 개수는 broker의 개수보다 많아질 수 없음<br>
      - broker의 개수가 3이면 replication의 개수는 4가 될 수 없음<br>
    - leader partion과 follower partion을 합쳐서 <b>In Sync Replica(ISR)</b>라고도 부름<br>
    - 만약 브로커가 3개인 카프카에서 repliaction이 1이고 partion이 1인 topic이 존재한다고 가정했을 때 브로커가 중단된다면 더 이상 해당 파티션을 복구가 될 수 없음<br>
    - 만약 replication이 2라면 브로커 1개가 죽더라도 follower partion이 leader partion의 역할을 승계하게 됨<br>
    - 프로듀서가 토픽에 데이터를 전달할 때 Leader Partion이 데이터를 전달 받는 주체가 됨<br>
    - 프로듀서에는 ack옵션을 통해 고가용성을 유지할 수 있는데 partion의 replication과 관련이 있음<br>
    - ack는 0, 1, all 옵션 3개 중 1개를 골라서 설정 가능<br>
      - 0일 경우 프로듀서는 leader partion에 데이터를 전송하고 응답값을 받지 않기에 정상적으로 전송됐는지 또는 나머지 파티션에 정상적으로 복제 됐는지 알 수 없고 보장할 수 없음(속도는 빠르지만 데이터 유실 가능성 존재)<br>
      - 1일 경우 leader partion에 데이터를 전송하고 제대로 전송됐는지 응답값을 받지만 replication이 정상적으로 복제 됐는지는 알 수 없음(만약 leader partion이 데이터를 받자마자 장애가 나면 나머지 partion에 데이터가 미처 전송되지 못한 상태이므로 데이터 유실 가능성이 존재)<br>
      - all일 경우 1옵션에 추가로 follower partion에 복제가 잘 이뤄줬는지까지 응답값을 받게 됨(데이터 유실 없지만 속도가 현저히 느린 단점 존재)<br>
    - 하지만 무작정 replication개수를 늘리면 브로커의 리소스 사용량도 늘어나게 됨<br>
    - 따라서 카프카에 들어오는 데이터량과 저장 시간을 고려해서 replication을 정하는게 좋음<br>
    - 3개 이상의 브로커를 사용할 때 replication은 3으로 설정하는 것을 권장<br>

### Consumer
- <b>다른 메시징 시스템과 다르게 메시지를 가져가더라도 데이터가 사라지지 않음</b><br>
- 이와 같은 특징은 카프카, 그리고 카프카 컨슈머를 데이터 파이프라인으로 운영하는데 핵심적인 역할을 함<br>
- Consumer의 역할
  - Topic의 partion으로 부터 데이터를 가져옴(polling)<br>
  - partion offset(파티션에 위치해있는 데이터의 번호) 위치 기록(commit)<br>
  - Consumer group을 통해 병렬처리<br>
- Gradle이나 Maven으로 라이브러리를 가져 올 수 있으며 Client버전과 Broker버전을 일치시켜주는게 좋음<br>
~~~
// gradle
compile group: 'org.apache.kafka', name: 'kafka-clients', version: '2.3.0'

//maven
<dependency>
  <groupId>org.apache.kafka</groupId>
  <artifactId>kafka-clients</artifactId>
  <version>2.3.0</version>
</dependency>
~~~
- 예제 코드<br>
- <img width="878" alt="스크린샷 2020-11-30 오후 11 15 38" src="https://user-images.githubusercontent.com/44339530/100620621-f6c6cc80-3361-11eb-9e43-0b703901ee32.png"><br>
- 브로커의 주소목록은 2개 이상의 ip와 port를 지정하도록 권장 (한 개 브로커가 비정상일 경우 다른 브로커를 사용할 수 있기 때문에)<br>
- Consumer group id를 지정해야함<br>
- StringDeSerializer는 key혹은 value를 직렬화 설정해야함(byte array, string, integer 시리얼라이즈 사용 가능)<br>
- 특정 토픽의 전체 파티션이 아닌 특정 파티션에서 데이터를 가져오고자 한다면 다음과 같이 설정<br>
- <img width="878" alt="스크린샷 2020-11-30 오후 11 18 17" src="https://user-images.githubusercontent.com/44339530/100620918-5624dc80-3362-11eb-9d42-6cb79bd8c675.png"><br>
- polling한 데이터를 하둡 또는 엘라스틱서치와 같은 저장소에 저장하기도 함<br>
- Consumer는 partion에 접근해서 __consumer_offsets토픽에 offset에 대한 정보들을 기록하며 데이터를 가져감<br>
- 만약 Consumer가 의도치 않게 중단되도 어디까지 읽었는지에 대한 offset정보는 __consumer_offsets에 이미 저장되있기에 컨슈머를 재실행해도 중지되었던 시점부터 제대로 데이터를 읽어들일 수 있음(고가용성)<br>
- Partition과 Consumer의 개수<br>
  - 하나의 Consumer는 하나의 Partion을 subscribe<br>
  - Partition은 하나의 Consumer만 접근이 가능<br>
  - 반대로 Consumer는 여러 개의 Partition을 소비할 수 있음<br>
  - 대량의 메시지가 Kafka에 쓰여진다고 가정<br>
  
  - <b>(1) Partition 1개 / Consumer 인스턴스 1</b>
    - 메시지가 대량으로 막 생산되고 있는데, 처리할 수 있는 Consumer가 1개 밖에 없으니 그래서 Consumer를 늘리기로 함.

  - <b>(2) Partition 1개 / Consumer 인스턴스 4개</b>
    - Consumer를 4개로 늘렸지만, Consumer Group에서 Partition은 하나의 Consumer 밖에 접근을 못하는 구조
    - 즉, Consumer를 늘리나 마나인 상황이라 그래서 이번에는 Partition을 늘림

  - <b>(3) Partition 4개 / Consumer 인스턴스 4개</b>
    - Consumer는 하나의 Partition에 접근할 수 있으므로, Partition과 Consumer는 1:1 구성
    - 이상적인 상황

  - <b>(4) Partition 4개 / Consumer 인스턴스 3개</b>
    - 잘 운영이 되다가, 갑자기 Consumer 하나가 죽어버려도 문제는 없음
    - Consumer Group에서 offset이 공유되고 있으므로 Consumer가 하나 죽더라도 다른 Consumer가 해당 Partition에 접근하면 됨

  - <b>(5) Partition 3개 / Consumer 인스턴스 3개</b>
    - 메시지가 잘 처리되고 있고, 상황을 보니 Partition을 3개로 줄여도 될 것 같지만 Partition은 한 번 늘리면 줄일수가 없음

  - 위 상황의 결론은 Partition의 개수 >= Consumer 인스턴스의 갯수를 유지하는 것이 좋음( Consumer > Partition은 불가능 )
  - 하나의 Partition에 하나의 Consumer가 담당하는 것이 좋지만 딱 맞출수는 없으므로, Consumer 수가 모자라도 상관은 없음
  - 주의할 점은 한 번 Partition을 늘리면 다시 줄일 수 없기 때문에, 처리량을 잘 고려하여 Partition과 Consumer의 개수를 선택해야 할 것<br>

### Consumer Group
- <b>왜 Consumer Group이 필요한가?</b>
  - 만약 Consumer Group이 없고 두 개의 Consumer가 하나의 Partion에 동시에 접근한다면 어떤 Consumer가 몇 번의 offset을 소비해야하는지 알 수가 없게 됨<br>
  - 그리고 Consumer Group이 어떤 Consumer가 해당 partion의 몇 번 offset을 소비해야하는지에 대한 정보들을 다 가지고 있기 때문에 Consumer하나가 다운 되어도 다른 Consumer가 해당 파티션의 메시지를 소비할 수 있도록함(고가용성 확보)<br>
- Consumer는 하나의 Consumer Group에 속하며 Consumer Group은 하나의 Topic을 Subscribe<br>
- 각기 다른 Consumer Group에 속한 Consumer들은 서로 영향을 끼치지 않음<br>
- <b>Consumer Group의 활용</b><br>
  - 데이터 실시간 시각화 및 분석을 위해 엘라스틱 서치에 데이터를 저장하는 Consumer Group이 있다고 가정<br>
  - <img width="878" alt="스크린샷 2020-11-30 오후 11 32 17" src="https://user-images.githubusercontent.com/44339530/100622522-4a3a1a00-3364-11eb-98f0-2c437c3bf4ad.png"><br>
  - 여기에 추가로 데이터 백업 용도로 하둡에 데이터를 저장하는 Consumer Group이 새로 들어옴<br>
  - <img width="878" alt="스크린샷 2020-11-30 오후 11 33 46" src="https://user-images.githubusercontent.com/44339530/100622691-7f466c80-3364-11eb-899c-e4d6d7ccffa9.png"><br>
  - 만약 엘라스틱 서치에 저장하는 Consumer Group이 각 파티션에 특정 offset을 읽고 있어도 하둡에 저장하는 Consumer Group이 데이터를 읽는데 영향을 미치지 않음<br>
  - 왜냐하면 __consumer_offsets토픽에는 컨슈머 그룹별로 토픽별로 offset을 나눠 저장하기 때문<br>
  - 이러한 카프카의 특징을 토대로 하나의 Topic으로 들어온 데이터는 다양한 역할을 하는 여러 Consumer들이 각자 원하는 데이터로 처리가 될 수 있음<br>

### Consumer Design (Kafka의 이점)
- Kafka와 마찬가지로 대표적인 메시징 시스템인 RabbitMQ, ActiveMQ 역시 분산 큐 시스템(Distributed Queue System)이니까 성능이 다 빠를것 같은데.. 왜 유독 Kafka가 빠르다고 할까?

- 성능이 좋으려면 Consumer가 최대의 효율을 내야 함
- 분산처리가 된다한들, 소비자가 메시지를 처리못하면 전체적인 성능이 느려지게됨
- 즉, 메시지를 소비하는 방식에 대한 차이가 성능의 차이를 나타내게 됨
- Kafka는 Single Consumer가 아닌, Multi Consumer를 염두에 두고 설계되었기 때문에 Consumer를 잘 살펴볼 필요가 있음

### RabbitMQ와 Kafka의 Consumer Design의 비교
- 1)RabbitMQ
  - Message Broker가 Consumer에게 메시지를 push하는 방식
  - Broker는 Consumer의 처리여부에 관계없이 push를 하므로, 메시지 소비 속도보다 생산 속도가 빠를 경우 Consumer에 부하를 주게됨
  - RabbitMQ는 DRAM을 사용하므로 buffer를 사용하지만, DRAM을 다 사용하면 disk에 저장, 따라서 batch 같이 큰 작업에서는 disk로 메시지를 읽어올 경우 지연이 발생

- 2)Kafka
  - Consumer가 Broker로부터 메시지를 pull하는 방식
  - Consumer가 처리할 수 있을 때 메시지를 가져오므로 자원을 효율적으로 사용
  - Kafka는 애초에 메시지를 disk에 저장하고, 이미 처리한 과거의 offset으로 자유롭게 움직일 수 있으므로 batch 작업에서 자원의 낭비라던지 지연이 발생하지 않음
  - 메시지를 쌓아두었다가 처리하는 batch Consumer 구현도 가능

- 항상 trade off가 있듯이, pull 방식에도 단점은 있음
- 데이터가 없음에도 정기적인 polling으로 인해 자원을 낭비하는 문제인데, 이러한 단점을 보완하기 위해 실제 데이터가 도착할 때까지 long poll 대기를 할 수 있는 parameter를 지원<br>
(kafka공식문서 참고 https://kafka.apache.org/documentation/#design_pull)<br>

### Replication
  - Topic을 생성할 때, --replication-factor 옵션을 부여하면 복제본(replication)을 생성
  - Replication이란 Zookeeper가 leader가 되는 Partition을 정하고, Partition을 각 broker마다 복제를 하는 것, 이때  leader를 복제하는 partition을 follower라 칭함
    - leader: 메시지를 생산하고 소비하는 작업은 모두 leader broker에서 이뤄짐
    - follower: 나머지 follower들은 leader를 복제만 수행
  - 이는 고가용성을 위한것이며, 혹시 leader가 죽었을 경우 follower 중 하나가 leader가 되어야 하기 때문에, follower는 leader와 싱크를 맞추고 있는 것 ( In-Sync Replica, ISR )
  - 예를 들어, 3대의 Broker Server에 대하여, topic-1에 4개의 Partition이 존재할 때, --replication-factor=2 로 설정한다고 가정 ( Zookeeper가 Partition을 골고루 분배 )
  - Partition1에 메시지를 쓰는 상황일 때, leader partition이 존재하는 Broker2에서 메시지가 생산
  - 그리고 follower인 Broker3에 존재하는 Partition은 leader를 복제 ( ISR )
  - 만약 leader가 되는 Broker가 다운이 되면, follower가 leader로 선출됩니다.
  - 예시는 leader가 1개지만 여러 개의 follower가 있다면, Zookeeper가 leader를 알아서 선출

### 메시지 보존기간
- 클러스터는 쓰여진 메시지를 보존기간동안 유지
- 보존 기간 정책은 log.retention.hours 설정을 통해 가능하며, 기본값은 7일
- 예를 들어, 보존 정책이 2일이면, 2일 뒤에는 공간 확보를 위해 해당 메시지를 폐기
- 데이터 크기에 상관없이 카프카의 성능은 일정하기 때문에 장기간 저장해도 문제는 없으므로, 보존기간을 짧게 잡을 필요는 없음
- 즉, Consumer가 메세지를 소비한다고 해서 메시지가 없어지는게 아니라 보존기간이 지나야 사라짐, 그래서 Consumer가 과거의 offset에 대한 접근을 할 수 있는것
- Partition이 1개이냐, 2개 이상이냐에 따른 메시지 순서 보장에 대한 자료<br>
https://www.popit.kr/kafka-%EC%9A%B4%EC%98%81%EC%9E%90%EA%B0%80-%EB%A7%90%ED%95%98%EB%8A%94-%EC%B2%98%EC%9D%8C-%EC%A0%91%ED%95%98%EB%8A%94-kafka/<br>

### kafka command
- (1) zookeeper시작<br>
bin\windows\zookeeper-server-start.bat config/zookeeper.properties<br>

- (2) kafka server 시작<br>
bin\windows\kafka-server-start.bat config/server.properties<br>

- (3) consumer시작<br>
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic topic1<br>
  - <b>Option</b>
  - --from-beginning: 쌓여있던 모든 메시지를 가져옴<br>

- (4) producer 시작<br>
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic topic1<br>

### kafka log4j configuration for java
https://alwaysemmyhope.com/ko/java/460988-how-to-enable-kafka-logging-with-log4j-java-log4j-apache-kafka-slf4j.html<br>

### 출처 및 참고
- https://victorydntmd.tistory.com/344?category=798367<br>
- https://team-platform.tistory.com/13<br>
- http://junil-hwang.com/blog/kafka-java/<br>
- https://oboki.net/workspace/bigdata/kafka/kafka-producer-consumer-sample/<br>
- https://www.youtube.com/watch?v=waw0XXNX-uQ&list=PL3Re5Ri5rZmkY46j6WcJXQYRlDRZSUQ1j<br>