## JVM구조

### JVM이란?
- java 가상머신으로 java 바이트 코드를 실행할 수 있는 주체<br>
<img width="609" alt="스크린샷 2021-03-18 오후 7 39 40" src="https://user-images.githubusercontent.com/44339530/111613280-af395300-8821-11eb-8239-5f76e3367f76.png"><br>

### JVM의 구성 요소
- 1. <b>Class Loader</b>
    - 예를 들어 자바에서 코딩시 a.java파일이 생성된다고 하면 .java소스를 자바컴파일러(javac)가 컴파일하여 a.class와 같은 클래스 파일(바이트코드)로 변환해준다. Class Loader는 이러한 class파일들을 모아 JVM이 운영체제로 부터 할당받은 메모리 영역인 Runtime Data Area로 적재하는 역할을 한다.
- 2. <b>Execution Engine</b>
    - Execution Engine은 Class Loader에 의해 메모리에 적재된 클래스(바이트코드)들을 기계어로 변경해 명령어 단위(operation code)로 실행하는 것을 말한다. 명령어단위 실행에는 2가지 방식이 있다.
    ~~~
    1) 인터프리터 방식 : 명령어를 하나씩 수행(기본적인 방식. 전체 수행은 느리나 명령어 하나씩의 동작은 빠름)

    2) JIT(Just In Time compiler)방식 : 전체 바이트 코드를 네이티브 코드로 변환하고 그 이후에는 인터프리팅하지 않고 네이티브 코드 상태로 실행(전체적인 동작은 빠르나 컴파일하는데 상당 시간 소요)
    ~~~

- 3. <b>Garbage Collector</b>
    - Garbage Collector(GC)는 Heap 메모리 영역에 생성된 객체들 중에 참조되지 않은 객체들을 제거하는 역할을 한다. GC의 동작시간은 일정하게 정해져 있지 않기 때무에 언제 객체를 정리할지는 알 수 없다. 즉 바로 참조가 없어지자마자 작동하는것이 아니라는 것이다.
    - 또한 GC를 수행하는 동안 GC Thread를 제외한 다른 모든 Thread는 일시정지상태가 된다. 
    - 특히 Full GC가 일어나서 수초간 모든 Thread가 정지한다면 심각한 장애로 이어질 수 있다.
- 4. <b>Runtime Data Area</b>
    - JVM의 메모리 영역으로 자바 애플리케이션 실행시 사용되는 데이터를 적재하는 영역으로 크게 5가지 영역으로 구분된다.

### Runtime Data Area의 5가지 구성 요소
<img width="624" alt="스크린샷 2021-03-18 오후 7 46 48" src="https://user-images.githubusercontent.com/44339530/111614217-ae54f100-8822-11eb-85ed-3f258b70a038.png"><br>

#### Method Area
- 필드 정보(클래스 멤버 변수명, 데이터 타입, 접근 제어자 정보)
- 메소드 정보(메소드명, 리턴 타입, 접근 제어자 정보)
- Type정보(Interface인지 Class인지)
- Constant Pool(상수 풀: 문자 상수, 타입, 필드, 객체 참조 정보)
- static 변수
- final 클래스 변수

#### Heap Area
- new 키워드로 생성된 객체와 배열이 생성되는 영역
- Method Area에 로드된 class만 생성 가능하며 GC가 참조되지 않은 메모리를 확인하고 제거하는 영역

#### Stack Area
- 지역변수, 파라미터, 리턴 값, 연산에 사용되는 임시값 등을 저장
- int a = 10 을 예로 들면 정수값이 할당될 수 있는 메모리 공간을 a라고 잡아두고 그 메모리 영역에 10을 넣음
- 클래스 A a = new A()의 경우 A a는 스택영역에 저장되고 new로 생성된 A클래스의 인스턴스는 Heap영역에 생성
- 또한 스택영역에 생성된 a는 힙영역의 주소값을 가지고 있다. 즉 a가 heap영역에 생성된 객체를 가리키며 참조하고 있음
- 메소드 호출시마다 개별적으로 스택이 생성

#### PC Register
- Thread가 생성될 때마다 생성되는 영역
- Program Counter 즉, 현재 쓰레드가 실행되는 부분의 주소와 명령을 저장하고 있는 영역
- 이것을 이용해 쓰레드를 돌아가면서 수행

#### Native Method Stack
- 자바외 언어로 작성된 네이티브 코드를 위한 메모리 영역
- 보통 C/C++등의 코드를 수행하기 위한 스택(JNI)

### 참고 사항
- 스레드 생성시 Method Heap영역을 모든 스레드가 공유하고, stack, PC Register, Native Stack은 각각의 스레드마다 생성되고 공유되지 않음

#### 출처
- https://velog.io/@hono2030/JVM%EC%9D%98-%EA%B5%AC%EC%A1%B0