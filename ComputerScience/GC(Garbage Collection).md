## GC(Garbage Collection)

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
- Java에서 개발자가 프로글매 코드로 메모리를 명시적으로 해제하지 않기 때문에 가비지 컬렉터가 더이상 필요 없는 객체(쓰레기)를 찾아 지움
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

#### 참고 자료
- https://d2.naver.com/helloworld/37111