## GC(Garbage Collection)의 개요

### 가비지 컬렉션 과정
- GC에 대해 알아보기 전에 'stop-the-world'라는 용어를 알아 둬야 함
- 'stop-the-world'란 GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것
- 'stop-the-world'가 발생하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈추게 됨
- GC작업을 완료한 이후에야 중단했던 작업을 다시 시작함
- 어떤 GC알고리즘을 사용하더라도 'stop-the-world'는 반드시 발생함
- 대개의 경우 GC 튜닝이란 'stop-the-world'시간을 줄이는 것임
- Java는 C언어와는 다르게 프로그램 코드에서 메모리를 명시적으로 지정하여 해제 하지 않음
- 가끔 명시적으로 해제하려고 해당 객체를 null로 지정하거나 System.gc()메서드를 호출하는 개발자가 있음
- null로 지정하는 것은 큰 문제가 안되지만, System.gc() 메서드를 호출하는 것은 시스템의 성능에 매우 큰 영향을 끼치므로 절대 사용해선 안됨
- Java에서 개발자가 프로그램 코드로 메모리를 명시적으로 해제하지 않기 때문에 가비지 컬렉터가 더이상 필요 없는 객체(쓰레기)를 찾아 지움
- 이 가비지 컬렉터는 두 가지 가설 하에 만들어졌음
    - 1. 대부분의 객체는 금방 접근 불가능 상태(unreachable)가 된다.
    - 2. 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재한다.

- 이러한 가설을 'weak generational hypothesis'라 하는데 이 가설의 장점을 최대한 살리기 위해서 HotSpot VM에서는 크게 Young영역과 Old영역 2개로 물리적 공간을 나누었음
    - 1. Young 영역(Yong Generation 영역): 새롭게 생성한 객체의 대부분이 여기에 위치한다. 대부분의 객체가 금방 접근 불가능 상태가 되기 때문에 매우 많은 객체가 Young 영역에 생성되었다가 사라진다. 이 영역에서 객체가 사라질때 Minor GC가 발생한다고 말한다.
    - 2. Old 영역(Old Generation 영역): 접근 불가능 상태로 되지 않아 Young 영역에서 살아남은 객체가 여기로 복사된다. 대부분 Young 영역보다 크게 할당하며, 크기가 큰 만큼 Young 영역보다 GC는 적게 발생한다. 이 영역에서 객체가 사라질 때 Major GC(혹은 Full GC)가 발생한다고 말한다.

- 영역별 데이터 흐름<br>
    - ![223](https://user-images.githubusercontent.com/44339530/111610272-82377100-881e-11eb-9919-4685c517141d.png)<br>
    - 위 그림의 Permanent Generation 영역(이하 Perm 영역)은 Method Area라고도 함
    - 객체나 억류(intern)된 문자열 정보를 저장하는 곳이며, Old영역에서 살아남은 객체가 영원히 남아 있는 곳은 절대 아님
    - 이 영역에서 GC가 발생할 수도 있는데, 여기서 GC가 발생해도 Major GC의 횟수에 포함됨

- 만약 Old영역에 있는 객체가 Young영역의 개체를 참조하는 경우가 있을 때에는 어떻게 처리될까?
    - 이러한 경우를 처리하기 위해선 Old영역에는 512바이트의 덩어리(chunk)로 되어 있는 카드 테이블(card table)이 존재함
    - 카드 테이블에는 Old 영역에 있는 객체가 Young 영역의 객체를 참조할 때마다 정보가 표시됨
    - Young 영역의 GC를 실행할 때에는 Old 영역에 있는 모든 객체의 참조를 확인하지 않고, 이 카드 테이블만 뒤져서 GC 대상인지 식별함
    - <img width="421" alt="스크린샷 2021-03-18 오후 7 21 20" src="https://user-images.githubusercontent.com/44339530/111610845-1f92a500-881f-11eb-800b-d6ba83f9bc26.png"><br>
    - 카드 테이블은 write barrier를 사용하여 관리함. write barrier는 Minor GC를 빠르게 할 수 있도록 하는 장치<br>
    - write barrirer때문에 약간의 오버헤드는 발생하지만 전반적인 GC 시간은 줄어들게 됨

### Young 영역의 구성
- 객체가 제일 먼저 생성되는 영역
- Eden영역과 2개의 Survivor영역으로 총 3개의 영역으로 나뉨
- 각 영역의 처리 절차를 순서에 따라서 기술하면 다음과 같음
    - 1. 새로 생성한 대부분의 객체는 Eden 영역에 위치한다.
    - 2. Eden 영역에서 GC가 한 번 발생한 후 살아남은 객체는 Survivor 영역 중 하나로 이동된다.
    - 3. Eden 영역에서 GC가 발생하면 이미 살아남은 객체가 존재하는 Survivor 영역으로 객체가 계속 쌓인다.
    - 4. 하나의 Survivor 영역이 가득 차게 되면 그 중에서 살아남은 객체를 다른 Survivor 영역으로 이동한다. 그리고 가득 찬 Survivor 영역은 아무 데이터도 없는 상태로 된다.
    - 5. 이 과정을 반복하다가 계속해서 살아남아 있는 객체는 Old 영역으로 이동하게 된다.

- 위의 절차를 확인해 보면 알겠지만 Survivor 영역 중 하나는 반드시 비어 있는 상태로 남아 있어야 함
- 만약 두 Survivor 영역에 모두 데이터가 존재하거나, 두 영역 모두 사용량이 0이라면 시스템은 비정상적인 상태
- Minor GC를 통해서 Old 영역까지 데이터가 쌓인 것을 간단히 나타내면 다음과 같음<br>
<img width="283" alt="스크린샷 2021-03-18 오후 8 19 40" src="https://user-images.githubusercontent.com/44339530/111618069-4654d980-8827-11eb-8938-484e416a14b5.png"><br>

- <b>Eden 영역에 최초로 객체가 만들어지고, Survivor 영역을 통해서 Old 영역으로 오래 살아남은 객체가 이동한다는게 핵심</b>

### Old 영역에 대한 GC
- Old 영역은 기본적으로 데이터가 가득 차면 GC를 실행
- GC 방식에 따라서 처리 절차가 달라지므로, 어떤 GC 방식이 있는지 살펴보면 이해가 쉬움
- GC 방식은 JDK 7을 기준으로 5가지 방식이 존재
    - 1. Serial GC
    - 2. Parallel GC 
    - 3. Parallel Old GC(Parallel Compacting GC)
    - 4. Concurrent Mark & Sweep GC(이하 CMS)
    - 5. G1(Garbage First) GC

1. <b>Serial GC (-XX:+UseSerialGC)</b>
- 운영 서버에서 절대 사용하면 안 되는 방식(애플리케이션의 성능이 많이 떨어짐)
- 데스크톱의 CPU 코어가 하나만 있을 때 사용하기 위해서 만든 방식
- Old 영역의 GC는 mark-sweep-compact이라는 알고리즘을 사용
- 이 알고리즘은 Old영역에 살아 있는 객체를 식별(Mark)하여 그 다음에는 힙(Heap)의 앞 부분부터 확인하여 살아 있는 것만 남김(Sweep)
- 막 단계에서는 각 객체들이 연속되게 쌓이도록 힙의 가장 앞 부분부터 채워서 객체가 존재하는 부분과 객체가 없는 부분으로 나눔(Compaction)
- Serial GC는 적은 메모리와 CPU 코어 개수가 적을 때 적합한 방식

2. <b>Parallel GC (-XX:+UseParallelGC)</b>
- Serial GC와 기본적인 알고리즘은 같음
- 그러나 Serial GC는 GC를 처리하는 스레드가 하나인 것에 비해, Parallel GC는 GC를 처리하는 쓰레드가 여러 개
- 그렇기 때문에 Serial GC보다 빠른게 객체를 처리할 수 있음
- 메모리가 충분하고 코어의 개수가 많을 때 적합한 방식
- Throughput GC라고도 부름
- Serial GC와 Parallel GC의 스레드 비교<br>
<img width="551" alt="스크린샷 2021-03-18 오후 8 32 44" src="https://user-images.githubusercontent.com/44339530/111619576-19a1c180-8829-11eb-8307-b8372634fe24.png"><br>

3. <b>Parallel Old GC(-XX:+UseParallelOldGC)</b>
- JDK 5 update 6부터 제공한 GC 방식
- Parallel GC와 비교하여 Old 영역의 GC 알고리즘만 다름
- Mark-Summary-Compaction 단계를 거침
- Summary 단계는 앞서 GC를 수행한 영역에 대해서 별도로 살아 있는 객체를 식별한다는 점에서 Mark-Sweep-Compaction 알고리즘의 Sweep 단계와 다르며, 약간 더 복잡한 단계를 거침


4. <b>CMS GC (-XX:+UseConcMarkSweepGC)</b>
- Serial GC와 CMS GC의 절차를 비교한 그림(지금까지의 GC 방식보다 더 복잡)<br>
<img width="577" alt="스크린샷 2021-03-18 오후 8 35 14" src="https://user-images.githubusercontent.com/44339530/111619914-71d8c380-8829-11eb-8568-f0c11a9bb447.png"><br>

- 초기 Initial Mark 단계에서는 클래스 로더에서 가장 가까운 객체 중 살아 있는 객체만 찾는 것으로 끝냄. 따라서, 멈추는 시간은 매우 짧음. 그리고 Concurrent Mark 단계에서는 방금 살아있다고 확인한 객체에서 참조하고 있는 객체들을 따라가면서 확인함. 이 단계의 특징은 다른 스레드가 실행 중인 상태에서 동시에 진행된다는 것
- 그 다음 Remark 단계에서는 Concurrent Mark 단계에서 새로 추가되거나 참조가 끊긴 객체를 확인. 마지막으로 Concurrent Sweep 단계에서는 쓰레기를 정리하는 작업을 실행. 이 작업도 다른 스레드가 실행되고 있는 상황에서 진행
- 이러한 단계로 진행되는 GC 방식이기 때문에 stop-the-world 시간이 매우 짧음
- 모든 애플리케이션의 응답 속도가 매우 중요할 때 CMS GC를 사용하며, Low Latency GC라고도 부름
-  CMS GC는 stop-the-world 시간이 짧다는 장점에 반해 다음과 같은 단점이 존재
    - 다른 GC 방식보다 메모리와 CPU를 더 많이 사용한다.
    - Compaction 단계가 기본적으로 제공되지 않는다.
- 따라서, CMS GC를 사용할 때에는 신중히 검토한 후에 사용해야 함. 그리고 조각난 메모리가 많아 Compaction 작업을 실행하면 다른 GC 방식의 stop-the-world 시간보다 stop-the-world 시간이 더 길기 때문에 Compaction 작업이 얼마나 자주, 오랫동안 수행되는지 확인해야 함



5. <b>G1(Garbage First) GC</b><br>
<img width="505" alt="스크린샷 2021-03-18 오후 8 40 30" src="https://user-images.githubusercontent.com/44339530/111620524-2ecb2000-882a-11eb-985a-dc23fe08af80.png"><br>

- 이전까지의 Young영역과 Old영역에 대해서는 잊는 것이 좋음
- 장기적으로 말도 많고 탈도 많은 CMS GC를 대체하기 위해서 만들어짐
- 가비지 수집 절차
    - 1. G1 GC는 바둑판의 각 영역에 객체를 할당하고 GC를 실행
    - 2. 해당 영역이 꽉 차면 다른 영역에서 객체를 할당하고 GC를 실행
    - 3. 즉, 지금까지 설명한 Young의 세가지 영역에서 데이터가 Old 영역으로 이동하는 단계가 사라진 GC 방식
- 가장 큰 장점은 '성능'
    - 지금까지 설명한 어떤 GC방식 보다 빠름
    - JDK 6에서는 G1 GC를 early access라고 부르며 그냥 시험삼아 사용할 수만 있도록 함
    - JDK 7에서 정식으로 G1 GC를 포함하여 제공

#### 출처
- https://d2.naver.com/helloworld/37111
- https://yaboong.github.io/java/2018/06/09/java-garbage-collection/
- https://velog.io/@litien/%EA%B0%80%EB%B9%84%EC%A7%80-%EC%BB%AC%EB%A0%89%ED%84%B0GC
- https://kamang-it.tistory.com/entry/Java%EC%97%90%EC%84%9C%EC%9D%98-%EA%B0%80%EB%B9%84%EC%A7%80%EC%BB%AC%EB%A0%89%ED%84%B0Garbage-CollectorGC%EB%8F%8C%EC%95%84%EA%B0%80%EB%8A%94-%EC%9B%90%EB%A6%AC-%ED%8C%8C%ED%95%B4%EC%B9%98%EA%B8%B0