# 쪼밀리와 오구의 Process vs Thread
- https://www.youtube.com/watch?v=DmZnOg5Ced8&list=RDCMUC-mOekGSesms0agFntnQang&index=4

### Program vs Process vs Thread
- Program: 어떤 작업을 위해 운영체제 위에서 실행할 수 있는 파일
    - 예를 들어 웹 브라우저, 워드 프로세서, 카카오톡 등
- Process: 운영 체제 위에서 실행중인 프로그램
- Processor: 프로세스가 동작될 수 있도록 하는 하드웨어(=cpu)

### 프로세스 상태
<img width="538" alt="스크린샷 2021-05-01 오후 5 06 29" src="https://user-images.githubusercontent.com/44339530/116775751-9a4a1380-aa9f-11eb-80d1-7a270766d99a.png"><br>

- 프로세스가 실행되면 Ready상태가 되고 스케쥴링에 의해 작업이 할당되면 Run상태가 되어 작업이 진행된다.
- Run상태에서 할당 받은 시간이 끝나게 되면 다시 Ready상태가 된다.
- Run상태에서 작업을 하다 I/O작업이 생길시 Blocked상태로 된 후 I/O작업이 끝나면 다시 Ready상태가 된다.
- 프로세스의 작업이 끝나면 종료 상태가 된다.

### PCB(Process Control Block)
![1](https://user-images.githubusercontent.com/44339530/116776220-44c33600-aaa2-11eb-82de-273ab65ed2e0.png)<br>

- 운영체제가 스케쥴링을 하면서 프로세스를 스위칭하기 위해서 프로세스에 대한 정보를 알아야함
    - 어떤 프로세스가 어디까지 작업을 수행했는지에 대해 정보를 가지고 있어야함
- 이러한 프로세스를 제어하기 위한 정보 모음을 PCB라 한다.
    - 1)프로세스 식별자(Process ID)
    - 2)프로세스 상태
    - 3)다음에 실행할 명령어의 주소
    - 4)이전에 작업하던 작업 내용(레지스터)
    - 5)CPU 스케줄링 정보(우선 순위, 최종 실행시각, CPU점유시간 등)
    - 6)프로세스의 주소 공간 등

### Context Switching(문맥 교환)
<img width="536" alt="스크린샷 2021-05-01 오후 5 41 07" src="https://user-images.githubusercontent.com/44339530/116776637-69b8a880-aaa4-11eb-9569-081f3fa771b7.png"><br>

- <b>하나의 프로세스가 CPU를 사용 중인 상태에서 다른 프로세스가 CPU를 사용하도록 하기 위해, 이전의 프로세스의 상태(문맥)를 보관하고 새로운 프로세스의 상태를 적재하는 작업(위키백과)</b>
- Task1, Task2 두 가지 프로세스가 진행 중이라고 가정해보자.
    - Task1에서 어디까지 진행됐는지 Task2를 어디서 부터 시작해야 될지에 대해 알아내야함
    - Task1과 Task2과 교체되는 시점마다 PCB를 교체하는 과정이 일어남. 이를 Context Swithching라 한다.

### 프로세스 구조
![2](https://user-images.githubusercontent.com/44339530/116777177-0fb9e200-aaa8-11eb-90fd-15008e40f19f.png)<br>

- Stack: 호출된 함수, 지역변수 등 임시 데이터
- Heap: 동적으로 생긴 데이터. 예) new Object(), malloc()
- Data: 전역변수. 예) static 변수, global 변수
- Code: 프로그램의 코드
    
### 멀티 프로세스의 문제점
<img width="871" alt="1" src="https://user-images.githubusercontent.com/44339530/116839330-f54d4900-ac0c-11eb-96a1-e94392a13e0d.png"><br>

#### 단점1) 각 프로세스가 각각의 메모리 영역을 따로 가지고 있기에 비효율이 발생함
- 작업 중인 프로세스를 교체할 때 독자적인 메모리 영역을 core로 교체해야하기에 context swithching에 있어 많은 부담이 발생<br>
 
 <img width="448" alt="스크린샷 2021-05-03 오후 12 43 32" src="https://user-images.githubusercontent.com/44339530/116839375-2d548c00-ac0d-11eb-960e-ad62ce3a6a93.png"><br>
<img width="453" alt="스크린샷 2021-05-03 오후 12 46 16" src="https://user-images.githubusercontent.com/44339530/116839491-91775000-ac0d-11eb-9ff9-e6d924a7429c.png"><br>

#### 단점2) 다른 프로세스의 정보를 이용하기 위해서 통신이 필요함
- 각각의 메모리의 영역을 따로 가지고 있기 때문<br>

<img width="467" alt="스크린샷 2021-05-03 오후 12 48 22" src="https://user-images.githubusercontent.com/44339530/116839576-da2f0900-ac0d-11eb-94c1-e5543eece657.png"><br>

### 스레드 
<img width="222" alt="스크린샷 2021-05-03 오후 12 49 46" src="https://user-images.githubusercontent.com/44339530/116839627-0c406b00-ac0e-11eb-9890-d49730b8f16d.png"><br>

- 위의 그림과 같이 멀티스레드로 구성하면 Code Data Heap영역을 공유하게 되어 Context Switching시 비교적 부담이 덜함

### 멀티 프로세스 vs 멀티 스레드
#### 멀티 프로세스
<img width="477" alt="스크린샷 2021-05-03 오후 12 53 23" src="https://user-images.githubusercontent.com/44339530/116839783-8cff6700-ac0e-11eb-9acd-cd5ca220036b.png"><br>
<img width="455" alt="스크린샷 2021-05-03 오후 12 55 03" src="https://user-images.githubusercontent.com/44339530/116839853-c89a3100-ac0e-11eb-9b34-bf4988938bdd.png"><br>

- 프로세스A가 CPU에 할당되면 Core의 레지스터는 PCB에 대한 정보를 가지게 되고 캐시 및 램 에는 code data heap영역이 할당되게 됨
- 현 상태에서 프로세스B가 CPU에 할당된다면 기존의 프로세스A의 데이터들이 내려가고 프로세스B의 데이터들이 다시 Core의 레지스터 및 램에 올라가기 때문에 많은 데이터들의 이동이 발생하게 됨

#### 멀티 스레드
<img width="464" alt="스크린샷 2021-05-03 오후 12 57 34" src="https://user-images.githubusercontent.com/44339530/116839953-229af680-ac0f-11eb-8665-6955b97bce7d.png"><br>
<img width="468" alt="스크린샷 2021-05-03 오후 12 57 58" src="https://user-images.githubusercontent.com/44339530/116839969-30e91280-ac0f-11eb-8ab0-979b54d7d247.png"><br>

- 프로세스A가 실행되는 도중에 스레드가 교체 될 경우 <b>CPU 및 램에 새롭게 데이터를 적재시킬 필요없이 CPU에 있는 레지스터블록들만 변경되면 됨</b>
    - <b>즉, 다음 스레드가 실행할 메소드가 어느 부분부터 시작해야 될지에 대한 정보를 가지고 있는 레지스터만 변경되면 되기에 context switching비용이 줄어들게 됨</b><br>

<img width="437" alt="스크린샷 2021-05-03 오후 1 00 30" src="https://user-images.githubusercontent.com/44339530/116840072-8c1b0500-ac0f-11eb-92c7-4a9bb7bb9ff1.png"><br>

### 멀티코어라면?
#### 멀티코어일때의 멀티스레드 방식
<img width="459" alt="스크린샷 2021-05-03 오후 1 03 48" src="https://user-images.githubusercontent.com/44339530/116840219-0186d580-ac10-11eb-8542-a3573c3d7190.png"><br>

- <b>core 두개가 L2캐쉬를 통해 code, data, heap영역을 공유하며 각각의 작업을 진행하게 됨</b>

#### 멀티코어일때의 싱글스레드 방식
<img width="450" alt="스크린샷 2021-05-03 오후 1 04 21" src="https://user-images.githubusercontent.com/44339530/116840245-16636900-ac10-11eb-928d-d30a8129f9f6.png">

- core는 하나만 돌게되어 비효율적이게 됨

### 멀티 스레드 주의점
- 디버깅이 까다로움
- 한 프로세스안의 스레드에 문제가 생기면 같은 프로세스안의 다른 스레드에도 같이 문제가 생길 우려가 있음
- 같은 데이터(Code Data Heap영역)를 공유하기에, 데이터 동기화에 신경써야함

### 결론
- 스레드: 프로세스 내에서 실행되는 작업 흐름의 단위
- 프로세스
    - 프로세서에 의해 동작하고 있는 프로그램
    - 프로세스가 동작 한다는 것은 프로세스의 특정 스레드가 실행 중인데 실행중인 스레드는 프로세스가 가진 데이터를 참조함
    - 스레드 단위 작업을 지원하기 위한 자원 할당의 단위
- <b>자원은 Process단위로 받고 CPU의 작업/스케쥴링은 Thread 단위로 진행됨</b>

#### 출처
- https://heurinbada.tistory.com/27
- https://www.guru99.com/process-management-pcb.html
- https://www.neulsang.life/multi%20process%20vs%20multi%20thread/