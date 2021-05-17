# Debugging
-  프로그래밍에 있어 디버깅은 필수적인 요소이며 실제 개발할 때에도 가장 중요한 부분이다. 디버깅을 얼마나 잘하느냐에 따라 프로그래머의 실력이 갈릴 정도로 프로그래머에게 있어 가장 중요한 능력이 디버깅 능력이다.
- [디버깅의 중요성](https://github.com/jeonyoungho/TIL/blob/master/ComputerScience/Debugging.md)

### 디버깅을 하기 전에
- <b>습관적으로 run을 실행하지만, 로컬 개발에선 웬만하면 debug로 실행하자!</b>
- 애플리케이션 실행 중에 break point를 사용하려면 debug여야만 한다.
- 코드 좌측의 여백을 우클릭하면 <b>Show Line Numbers</b>가 나오는데 이를 체크하면 아래의 그림과 같이 Line번호가 부여된다. 에러가 발생했을 때, 몇 번째 Line에서 발생했는지 log에 표시되는데, Line번호가 없으면 에러의 위치를 발견하기가 힘들다.<br>
<img width="884" alt="스크린샷 2021-05-17 오후 7 05 45" src="https://user-images.githubusercontent.com/44339530/118471628-ebfec900-b742-11eb-9faf-09aa303e9f39.png">

### Break Point 설정 방법
- 1)라인 넘버와 코드 사이의 여백을 클릭<br>
<img width="1041" alt="스크린샷 2021-05-17 오후 7 07 11" src="https://user-images.githubusercontent.com/44339530/118471802-194b7700-b743-11eb-978a-b770d2c0504c.png"><br>

- <b>2)표시된 브레이크 포인트(빨간점)를 우클릭하면 조건으로 break를 걸 수 있음</b>
    - 특히나 for, while 등의 반복문에서 특정값이 들어올때만 break를 걸고 싶을 때 유용<br>
<img width="1021" alt="스크린샷 2021-05-17 오후 7 09 04" src="https://user-images.githubusercontent.com/44339530/118472027-5adc2200-b743-11eb-9481-e3d7301be685.png"><br>

### 디버깅 버튼
![1](https://user-images.githubusercontent.com/44339530/118472133-75ae9680-b743-11eb-80cd-a3a4dace39ae.png)<br>

#### resume
- 단축키: option + command + r
- 기능: 다음 break point로 이동

#### step over
- 단축키: F8
- 기능: 현재 break된 파일에서 다음 라인 이동

#### step into
- 단축키: F7
- 기능: 현재 break된 라인에서 실행하고 있는 라인 내부로 이동

#### Force step into
- 단축키: option + shift + F7
- 기능: 다음 실행되는 라인으로 이동하나, step into와 달리 Stepping을 무시하고 진행
    - 예를 들어, command + shift + a -> stepping으로 설정창을 오픈 후<br>
    - <img width="1160" alt="스크린샷 2021-05-17 오후 7 13 21" src="https://user-images.githubusercontent.com/44339530/118472585-f372a200-b743-11eb-9ca1-94a5cdddda37.png"><br>
    - 아래처럼 skip simple getters를 체크하자<br>
    - <img width="1116" alt="2" src="https://user-images.githubusercontent.com/44339530/118472725-21f07d00-b744-11eb-9b24-3856ebd76cda.png"><br>
    - 그러면 step into시 getter메소드라면 skip할 것이고 force step into는 getter메소드 내부로 이동할 것이다.
    - <b>굳이 확인이 불필요한 getter, 생서자 등에 skip 옵션을 설정 후, skip이 필요할 시에는 step into로, 전부 확인이 필요할시에는 force step into로 이동하며 디버깅하면 유용하다.</b>

#### step out
- 단축키: shift + F8
- 기능: 현재 break된 라인에서 호출한 곳으로 이동한다.
    - step into로 파고 들어간 내부 메소드에서 호출했던 외부 메소드로 빠져나올시에 사용한다. 

#### drop frame
- 단축키 : 없음
- 기능 : call stack을 거슬러 올라간다.
    - <b>step out과 별차이 없어 보이는데, 큰 차이점은 step out은 해당 라인이 실행된 후 돌아가지만, drop frame은 해당 라인이 실행되기 전에 돌아간다. 예시로 살펴보자.</b><br>

    - <img width="959" alt="4" src="https://user-images.githubusercontent.com/44339530/118474102-a8f22500-b745-11eb-95f7-da7889802fb9.png"><br>
    - <img width="1090" alt="3" src="https://user-images.githubusercontent.com/44339530/118474119-ad1e4280-b745-11eb-860e-653d065e41a3.png"><br>
    - 1)step out 실행 결과<br>
    - <img width="912" alt="5" src="https://user-images.githubusercontent.com/44339530/118474283-dfc83b00-b745-11eb-842e-16186ea56e99.png"><br>
    - 위의 그림과 같이 getTemp메소드가 전부 실행 후 리턴되어 콘솔창에 "yoyo!"가 출력되었다.

    - 2)drop frame 실행 결과<br>
    - <img width="893" alt="스크린샷 2021-05-17 오후 7 30 02" src="https://user-images.githubusercontent.com/44339530/118474680-48afb300-b746-11eb-9a13-a5824dd76e57.png"><br>
    - 위의 그림과 같이 getTemp메소드가 실행되지 않고 롤백되어 호출했던 곳으로 돌아가기에 콘솔창에는 아무것도 출력되지 않는다.

#### Run to Cursor
- 단축키: option + F9
- 기능: 포커스 되어있는 라인으로 이동
- 예시<br>
<img width="1030" alt="스크린샷 2021-05-17 오후 7 32 16" src="https://user-images.githubusercontent.com/44339530/118474930-97f5e380-b746-11eb-8197-9dccff6ff558.png"><br>

- 위의 그림과 같이 포커스가 23라인에 있는 상태에서 Run to Cursor를 실행하면 해당 라인으로 이동한다.<br>
<img width="978" alt="스크린샷 2021-05-17 오후 7 34 01" src="https://user-images.githubusercontent.com/44339530/118475153-d68b9e00-b746-11eb-9fe7-cdee5347bfb2.png"><br>

- <b>보통 break point로 지정하지 않고, 단발성으로 break를 걸고 싶을 때 사용한다.</b>

#### Evaluate&Watch그리고 Call Stack
- 위에 있는 것들은 break line을 사용하는 법과 이동하는 방법과 관련이 있다.
- 이제는 break된 라인에서 어떤 일이 가능한지 확인할 것이다.

- <b>1)Evaluate&Watch</b>
    - break된 상태에서 코드를 실행할 수 있는 방법은 2가지이다.
    - <b>Evaluate와 Watch</b>인데, 둘 중 한가지만 사용하기 보다는 둘다 인지하고 그때그때 상황에 따라 사용하면 된다.
    - 1)Evaluate
        - 디버깅 버튼들 가장 우측(run to cursor 우측)에 계산기처럼 되어 잇는 버튼이다.
        - 단축키: option + F8
        - 기능: break된 라인에서 사용가능한 모든 코드(메소드, 변수, 클래스, 필드)를 실행할 수 있음, 모든 코드를 사용할 수는 없다.<br>
        - <img width="1110" alt="6" src="https://user-images.githubusercontent.com/44339530/118476274-42223b00-b748-11eb-9245-8f066fe925ab.png"><br>
    
    - 2)Watch
        - Evaluate와 거의 동일한 기능<br>
        - <img width="1009" alt="7" src="https://user-images.githubusercontent.com/44339530/118476732-ddb3ab80-b748-11eb-9928-100ef10d93c0.png"><br>
        - Watches메뉴에 +를 눌러 실시간으로 확인하고 싶은 인스턴스나 멤버 변수 값을 추가한 후 디버깅하면서 상태값의 변화를 확인한다.<br>
        - 해당 라인에서 가능한 모든 값, 메소드를 사용할 수 있으며 단순한 변수값부터 시작해서 Autowired된 코드까지 전부 사용가능하다.

    - 언제 Evaludate를 사용하고 언제 Watch를 사용해야 하는가?
        - 2개의 기능은 동일하다.
        - 단, Evaluate의 경우 코드를 계속 수동 실행해야하지만, Watch의 경우 삭제하지 않는 한, break line이 실행될때마다 자동 실행횐다.
        - 값을 변경하는 코드를 watch에 등록했다면 의도치 않게 값이 변경되서 다른 로직을 수행하게 되는 경우도 있으니 주의해야 한다.
        - 하지만 watch는 여러 디버깅 코드의 결과를 동시에 확인 가능하고, 반복적으로 디버깅 코드를 사용할 필요 없는 장점이 있다.
        ~~~
        evaluate를 선호하는 분들의 경우 단축키 때문에 많이들 사용한다.
        watch는 기본 단축키가 없으므로 마우스를 사용하거나, 단축키를 추가해야하는데 evaluate는 option+F8으로 열고 바로 코드 작성 후 실행하면 되서 사용하기 편하다.
        - 출처: https://jojoldu.tistory.com/149
        ~~~

- <b>2)Call Stack</b>
    - 디버깅 화면의 좌측 하단에는 해당 break linedㅔ 오기까지의 call stack이 출력된다. 이를 통해 이전에 어떤 값들이 넘어 온것인지, 이전에 다른 연산을 했으면 어떻게 값이 바뀔지를 모두 확인할 수 있다.<br>
    - <img width="1239" alt="8" src="https://user-images.githubusercontent.com/44339530/118477962-47808500-b74a-11eb-81d5-5b7a1e641f4d.png"><br>
    - 이동 후에는, break line에서 했던 것처럼 variables와 watches를 이용해 확인하고자 하는 값과 코드를 확인하면 된다.
    - 특히 spring과 같은 프레임워크에서 어떻게 코드가 실행되고 값이 변경되는지 확인할 때 굉장히 유용하게 사용된다. 

### 디버깅 관련 단축키 정리
- Resume (다음 브레이크 포인트로 이동) : Command + Option + R
    - 디버그 화면에서 왼쪽 창은 현재 브레이크 포인트까지 오기까지 거친 메소드 목록, 즉 콜스택
- Step Over (다음 줄로 이동) : F8
- Step Into (안으로 이동) : F7
- Stop Out (밖으로 이동) : Shift + F8
- 조건절 브레이크 포인트 (for문 같은 경우) : 브레이트 포인트에 우클릭하고 코드 작성
- Evaluate Expresstion (브레이크된 상태에서 코드 사용, 단발성) : Option + F8
- Watch (브레이크 이후의 코드 변경 확인하기) : 안경 모양 버튼

#### 출처
- https://jojoldu.tistory.com/149
- https://higugu.tistory.com/entry/IntelliJ-%EB%94%94%EB%B2%84%EA%B9%85-%EB%8B%A8%EC%B6%95%ED%82%A4-%EB%A7%A5-%EA%B8%B0%EC%A4%80?category=833857