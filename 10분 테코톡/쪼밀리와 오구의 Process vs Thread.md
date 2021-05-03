# 쪼밀리와 오구의 Process vs Thread
- https://www.youtube.com/watch?v=DmZnOg5Ced8&list=RDCMUC-mOekGSesms0agFntnQang&index=4

### Program vs Process vs Thread
- Program: 어떤 작업을 위해 운영체제 위에서 실행할 수 있는 파일
    - 예를 들어 웹 브라우저, 워드 프로세서, 카카카오톡 등
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
    - 어떤 프로세스를 어디까지 작업을 수행했는지에 대해 정보를 가지고 있어야함
- 이러한 프로세스를 제어하기 위한 정보 모음을 PCB라 한다.
    - 1) 프로세스 식별자(Process ID)
    - 2) 프로세스 상태
    - 3) 다음에 실행할 명령어의 주소
    - 4) 이전에 작업하던 작업 내용(레지스터)
    - 5) CPU 스케줄링 정보(우선 순위, 최종 실행시각, CPU점유시간 등)
    - 6) 프로세스의 주소 공간 등

### Context Switching(문맥 교환)
<img width="536" alt="스크린샷 2021-05-01 오후 5 41 07" src="https://user-images.githubusercontent.com/44339530/116776637-69b8a880-aaa4-11eb-9569-081f3fa771b7.png"><br>

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
- ㅁㅁ

#### 출처
- https://heurinbada.tistory.com/27
- https://www.guru99.com/process-management-pcb.html