# 던의 JVM의 Garbage Collector

 ### JVM(Java Virtual Machine)
 - 운영체제의 메모리 영역에 접근하여 메모리를 관리하는 프로그램
 - JVM의 역할
    - 메모리 관리
    - GC수행

### Garbage Collector
- 동적으로 할당된 메모리 영역 중 하용하지 않은 영역을 탐지하여 해제하는 기능

### Stack & Heap
- Stack: 정적으로 할당한 메모리 영역
    - 원시 타입의 데이터가 값과 함께 할당
    - Heap영역에 생성된 Object 타입 데이터의 참조 값
- Heap: 동적으로 할당한 메모리 영역
    - 모든 Object 타입의 데이터가 할당
    - Heap영역의 Object를 가리키는 참조 변수가 Stack에 할당

### GC의 대상
- 1)왼쪽의 코드를 실행하면 Stack과 Heap의 상태는 아래의 그림과 같다.<br>
<img width="518" alt="스크린샷 2021-05-04 오후 6 01 13" src="https://user-images.githubusercontent.com/44339530/116981615-b8fc1480-ad02-11eb-981f-aac9f12948da.png"><br>

- 2)메인 메소드가 끝나게 되면 stack이 전부 pop되면서 데이터가 전부 사라지고 Heap영역에는 "던"이라는 String 객체만 남게된다.<br> 
<img width="513" alt="스크린샷 2021-05-04 오후 6 04 18" src="https://user-images.githubusercontent.com/44339530/116981934-26a84080-ad03-11eb-982e-0a60bb7be4f7.png"><br>

- "던"이라는 String 객체를 Unreachable Object라 하고 참조되지 않은 영역이 된어 GC의 대상이 된다.<br>
<img width="513" alt="스크린샷 2021-05-04 오후 6 05 23" src="https://user-images.githubusercontent.com/44339530/116982051-4d667700-ad03-11eb-9aaa-7ebaf4124cbb.png"><br>

### GC의 과정
- 1)모든 애플리케이션의 흐름을 중단시킨다.(Stop-The-World: 아래에서 설명)
    
- 2)GC가 스택의 모든 변수를 스캔하면서 각각 어떤 힙의 객체를 참조하고 있는지 찾아서 마킹(marking)한다.

- 3)Unreachable Object가 참조하고 있는 객체도 찾아서 마킹(marking)한다.

- 4)마킹되지 않은 객체를 Heap에서 제거한다. 그 후 다시 애플리케이션이 수행한다.

- 즉, 참조되지 않은 객체를 찾아서 마킹하고 그외의 객체를 전부 삭제하는 과정으로 진행된다.(Mark & Sweep)

<img width="404" alt="스크린샷 2021-05-04 오후 6 09 59" src="https://user-images.githubusercontent.com/44339530/116982604-f1e8b900-ad03-11eb-96bf-36869879864f.png"><br>

- 위의 사진의 "던"이라는 객체는 참조되지 않은 객체이기에 제거 된다.
  
### GC가 발생하는 시기
- Heap은 New 영역과 Old 영역으로 나뉜다.<br>
<img width="461" alt="스크린샷 2021-05-04 오후 6 13 40" src="https://user-images.githubusercontent.com/44339530/116982973-76d3d280-ad04-11eb-8a0f-89fb1d1a955a.png"><br>

- New영역은 1개의 Eden영역과 2개의 Survival영역으로 구분된다.
    - Heap
        - New Generation
            - Eden
            - Survival0
            - Survival1
        - Old Generation<br>
<img width="457" alt="스크린샷 2021-05-04 오후 6 15 54" src="https://user-images.githubusercontent.com/44339530/116983206-c61a0300-ad04-11eb-83a0-c2d149ca2baa.png"><br>

#### 1. 새로운 객체는 Eden영역에 할당된다. 만약 Eden영역이 꽉 차면 Minor GC가 발생한다.
<img width="451" alt="스크린샷 2021-05-04 오후 6 17 28" src="https://user-images.githubusercontent.com/44339530/116983397-fe214600-ad04-11eb-93eb-f3839fb12e6a.png"><br>
<img width="452" alt="스크린샷 2021-05-04 오후 6 19 16" src="https://user-images.githubusercontent.com/44339530/116983570-3e80c400-ad05-11eb-9e81-dd8cbc9a903e.png"><br>

- Eden영역의 Unreachable객체는 메모리에서 해제되고 Reachable 객체는 Survival0으로 옮겨진다.

#### 2. 위의 과정이 반복되어 Survival0 영역이 꽉차면 Survival0영역에 Minor GC가 발생하여 살아남은 객체들은 Survival1로 이동하고 Age값이 증가한다.
<img width="451" alt="스크린샷 2021-05-04 오후 6 23 21" src="https://user-images.githubusercontent.com/44339530/116984075-d088cc80-ad05-11eb-8ece-e5aadfbbccf8.png">

- 그 다음 Eden영역에 GC가 발생했을 때는 두 개의 Survival영역 중 이미 객체가 차있는 영역(Survival1)에 쌓이게 된다.
- 만약 Survival1영역이 꽉 reachable object로 꽉차면 Minor GC가 발생하고 Survival0영역으로 Age값이 증가하여 옮겨진다.
- 위의 과정들을 반복하며 결론적으로 두 개의 Survival영역 중 하나는 무조건 비어있게 된다.<br>
<img width="451" alt="스크린샷 2021-05-04 오후 6 28 18" src="https://user-images.githubusercontent.com/44339530/116984708-818f6700-ad06-11eb-8680-48db6ca613ee.png">

#### 3. Survival의 객체 중 특정 Age값 이상이 되면 Old 영역으로 옮겨진다.(Promotion)
<img width="459" alt="스크린샷 2021-05-04 오후 6 32 03" src="https://user-images.githubusercontent.com/44339530/116985126-08444400-ad07-11eb-8d79-8c7268663b0f.png">

#### 4. Promotion과정이 계속 반복되어 Old영역이 꽉차게 되면 Major GC가 발생한다.

#### 위의 과정들이 반복되면서 GC가 메모리를 관리한다.

## GC의 종류
### 1. Serial GC
- GC를 처리하는 스레드1가 1개
- CPU 코어가 1개만 있을 때 사용하는 방식
- mark-sweep-compact 알고리즘을 사용
    - 위의 mark-sweep과정을 수행 후 살아남은 객체들을 한 곳에 몰아넣는 과정을 거쳐 메모리 파편화를 방지
<img width="409" alt="스크린샷 2021-05-04 오후 6 36 11" src="https://user-images.githubusercontent.com/44339530/116985548-9b7d7980-ad07-11eb-9fc7-a05dcbaebeae.png">

### 2. Parallel GC
- Serial GC와 동일한 알고리즘 사용하는데 GC를 처리하는 스레드가 여러 개
- Serial GC보다 빠르게 객체를 처리할 수 있다.
- Parallel GC는 메모리가 충분하고 코어의 개수가 많을 때 사용하면 좋다.

### 3. Concurrent Mark Sweep GC(CMS GC)
#### Stop-The-World란?
- GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것
- stop-the-world가 발생하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈춘다.
- GC작업을 완료한 이후에 중단한 작업을 다시 시작한다.<br>

<img width="332" alt="스크린샷 2021-05-04 오후 6 41 37" src="https://user-images.githubusercontent.com/44339530/116986114-5e65b700-ad08-11eb-99ea-9ff493b61118.png"><br>

- Serial GC와 Parallel GC의 비교했을때 파란색이 실제 작업 스레드고 노란색이 GC를 수행하는 스레드인데 확연히 Parallel GC의 stop-the-world시간이 적은것을 알 수 있다.

<img width="428" alt="스크린샷 2021-05-04 오후 6 44 25" src="https://user-images.githubusercontent.com/44339530/116986446-c1efe480-ad08-11eb-83a1-51890bc16e7d.png"><br>

- CMS GC의 경우 stop-the-world를 더 줄일 수 있으므로 속도가 중요한 애플리케이션에 주요 적용된다.
    - 1)Initial Mark: Stack의 모든 변수를 스캔하면서 살아있는 객체들을 마킹한다.
    - 2)Concurrent Mark: 다른 애플리케이션 스레드와 동시에 수행되면서 Inital Mark단계에서 참조된 객체가 참조하는 개체들을 객체 그래프를 순회하면서 계속해서 마킹한다.
    - 3)Remark: Concurrent Mark단계에서 작업 스레드와 동시에 수행되므로 새롭게 생성된 객체들이 있을 것이다. 그러기에 stop-the-world를 수행 후 다시 한 번 마킹하여 재검토한다.
    - 4)Concurrent Sweep: 다른 애플리케이션 스레드와 동시에 수행되며 마킹되지 않은 객체들을 제거한다.

#### CMS GC의 장점
- stop-the-world 시간이 짧기에 애플리케이션의 응답 시간이 빨라야 할 경우에 사용한다.

#### CMS GC의 단점
- 다른 GC보다 메모리와 CPU를 더 많이 사용한다.
- Compaction단계가 제공되지 않는다.
- 위와 같은 단점으로 인해 신중하게 적용해야 함

### 4. G1 GC
<img width="148" alt="스크린샷 2021-05-04 오후 6 55 14" src="https://user-images.githubusercontent.com/44339530/116987637-44c56f00-ad0a-11eb-826f-2ab574302fbb.png"><br>

- 위의 GC들과는 다르게 New 영역과 Old영역으로 나누지 않고 바둑판과 같이 각 영역을 Region으로 나눈다.
- 바둑판의 각 영역에 객체를 할당 후 GC를 수행하는 방식
- 특정 영역이 꽉 차면 다른 빈 영역에 객체를 할당 후 GC를 수행
- GC가 일어날 때 전체 영역을 탐색하는게 아니라 메모리가 꽉 찬 영역만 탐색하여 응답 시간을 빠르게 하는 방식이다.

#### G1 GC의 장점
- G1 GC는 STW시간이 짧다.
- Compaction을 사용한다.