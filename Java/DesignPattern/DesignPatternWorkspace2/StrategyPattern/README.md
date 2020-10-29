<!-- <h1>전략 패턴(strategy pattern)</h1>

- 예를 들어, 기차( Train )와 버스( Bus ) 클래스가 있고, 이 두 클래스는 Movable 인터페이스를 구현했다고 가정한다. 그리고 Train과 Bus 객체를 사용하는 Client도 있다고 가정한다.<br>
![image](https://user-images.githubusercontent.com/44339530/97515108-a4496780-19d3-11eb-870f-69544dc9b420.png)<br>
<br>

- 이 구조를 표현 하면 다음과 같다. <br>
![image](https://user-images.githubusercontent.com/44339530/97515147-bd521880-19d3-11eb-8078-ed15999a21af.png)<br>

- 기차는 선로를 따라 이동하고, 버스는 도로를 따라 이동한다.
- <b>그러다 시간이 흘러 선로를 따라 움직이는 버스가 개발되었다고 가정해봅시다.</b>
- 그러면 Bus클래스의 move메서드를 다음과 같이 바꿔주면 된다.<br>
![image](https://user-images.githubusercontent.com/44339530/97515251-04400e00-19d4-11eb-8e41-06aa7036e2d8.png)<br>

- 그런데 이렇게 수정하는 방식은 SOLID의 원칙 중 OCP(Open-Closed Principle)에 위배된다. (OCP에 의하면 기존의 move메서드를 수정하지 않으면서 행위가 수정되어야한다)<br>
- 또한 지금과 같은 방식의 변경은 시스템이 확장 되었을때 유지보수 관점에서도 복잡하다.<br>
- 예를 들어, 새로 개발된 선로를 따라 움직이는 버스와 같이, 선로를 따라 움직이는 택시, 고속 버스가 생긴다면 move메서드를 일일이 수정해야 하며, 같은 메서드를 여러 클래스에 똑같이 정의하고 있으므로 메서드의 중복이 발생한다.<br>

- 즉, 지금과 같은 수정 방식의 문제점은 다음과 같다.<br>
1. OCP위배
2. 시스템이 커져서 확장이 될 경우 메서드의 중복문제  -->

출처 - https://victorydntmd.tistory.com/292?category=719467



