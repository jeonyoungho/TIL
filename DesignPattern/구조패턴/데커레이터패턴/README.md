# 데커레이터 패턴(Decorator Pattern)
- 기본 기능에 추가할 수 있는 기능의 종류가 많은 경우에 사용하는 패턴이다.
- 데커레이터 패턴은 기본 기능에 추가될 수 있는 많은 수의 부가 기능에 대해서 다양한 조합을 동적으로 구현할 수 있는 패턴이다.
- 동적으로 클래스의 기능을 확장시키고자 할 때 사용하는 패턴 (추가되는 기능을 갖는 클래스만 만들어주면 됨)

## 도로 표시 방법 조합 프로그램 example
- 도로 표시
    - RoadDisplay 클래스: 기본 도로 표시 기능을 제공하는 클래스
    - RoadDisplayWithLane 클래스: 기본 도로 표시에 추가적으로 차선을 표시하는 클래스<br>
    - ![image](https://user-images.githubusercontent.com/44339530/110622862-6230fd80-81df-11eb-9308-54d0e951222e.png)<br>
- 소스 코드
~~~
public class RoadDisplay {  // 기본 도로 표시 클래스	
	public void draw() {
		System.out.println("도로 기본 표시");
	}
}

public class RoadDisplayWithLane extends RoadDisplay {  // 기본 도로 표시 + 차선 표시 클래스
	public void draw() {
		super.draw(); // 상위 클래스 즉 RoadDisplay의 draw 메서드를 호출해서 기본 도로를 표시
		drawLane();
	}
	private void drawLane() {
		System.out.println("차선 표시");
	}
}

public class Client {
	public static void main(String[] args) {		
		RoadDisplay road = new RoadDisplay();
		road.draw();  // 기본 도로만 표시
		
		RoadDisplay roadWithLane = new RoadDisplayWithLane();
		roadWithLane.draw(); // 기본 도로 + 차선 표시
	}
}
~~~

#### 문제점
- 1) 또다른 추가적인 도로 표시 기능을 구현하고 싶다면 어떻게 해야 하는가? 예를 들어 기본 도로 표시에 교통량을 표시하고 싶다면?<br>
    - 기본 도로 표시에 추가적으로 교통량을 표시하는 경우<br>
    - ![image](https://user-images.githubusercontent.com/44339530/110623100-b63be200-81df-11eb-91a5-ad844ceabd87.png)<br>
    ~~~
    public class RoadDisplayWithTraffic extends RoadDisplay {
        public void draw() {
            super.draw();
            drawTraffic();
        }
        private void drawTraffic() {
            System.out.println("교통량 표시");
        }
    }
    ~~~

- 2) 여러가지 추가 기능의 조합하여 제공하고 싶다면 어떻게 해야 하는가? 예를 들어 기본 도료 표시에 차선 표시 기능과 교통량 표시 기능을 함께 제공하고 싶다면?
    - 여러 가지 추가 기능을 조합해야 하는 경우 다음과 같아진다.<br>
    - ![image](https://user-images.githubusercontent.com/44339530/110623389-1c286980-81e0-11eb-82b6-b96acd7bb715.png)<br>
    - ![image](https://user-images.githubusercontent.com/44339530/110623421-26e2fe80-81e0-11eb-8d36-4c2ea81e0a23.png)<br>
    - RoadDisplay클래스를 상속 받은 클래스가 엄청 많아지게 되는 문제가 발생한다.

#### 해결책
- 추가 기능 별로 개별적인 클래스를 설계하고 이를 조합<br>
![image](https://user-images.githubusercontent.com/44339530/110623575-5db91480-81e0-11eb-86ea-8f044992e6ae.png)<br>
- 동적으로 클래스의 기능을 확장 가능하게 됨
- 기본 기능이 여러개 (RoadDisplayA, RoadDisplayB) 있을 수 있으므로 Display를 추상클래스로 만들어 하위클래스로 둔다.
- 하나만 있을시 Decorator가 직접 RoadDisplay와 연관 관계를 맺어도 문제는 없다!
- 소스 코드
~~~
public abstract class Display {
	 public abstract void draw();
}

public class RoadDisplay extends Display {	 // 기본 도로 표시 클래스
	public void draw() {
		System.out.println("도로 기본 표시");
	}
}

// 다양한 추가 기능에 대한 공통 클래스
public class DisplayDecorator extends Display {
	private Display decoratedDisplay;
	public DisplayDecorator(Display decoratedDisplay) {
		this.decoratedDisplay = decoratedDisplay;
	}
	public void draw() {
		decoratedDisplay.draw();
	}
}

public class LaneDecorator extends DisplayDecorator {  // 차선표시를 축하는 클래스
	public LaneDecorator(Display decoratedDisplay) { // 기존 표시 클래스의 설정
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw(); // 설정된 기존 표시 기능을 수행
		drawLane(); // 추가적으로 차선을 표시
	}
	private void drawLane() { System.out.println("\t차선 표시"); }	
}

public class TrafficDecorator extends DisplayDecorator { // 교통량 표시를 추가하는 클래스
	public TrafficDecorator(Display decoratedDisplay) { // 기존 표시 클래스의 설정
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw();  // 설정된 기존 표시 기능을 수행
		drawTraffic(); // 추가적으로 교통량을 표시
	}
	private void drawTraffic() { System.out.println("\t교통량 표시");	 }	
}

public class Client {
	public static void main(String[] args) {		
		Display road = new RoadDisplay();
		road.draw();  // 기본 도로 표시
		
		Display roadWithLane = new LaneDecorator(new RoadDisplay());
        // Client 클래스는 동일한 Display 클래스만을 통해서 일관성 있는 방식으로 도로 정보를 표시
		roadWithLane.draw();  // 기본 도로 표시 + 차선 표시
		
		Display roadWithTraffic = new TrafficDecorator(new RoadDisplay());
		roadWithTraffic.draw();  // 기본 도로 표시 + 교통량 표시
}
~~~

- 실행 결과<br>
![image](https://user-images.githubusercontent.com/44339530/110623903-d7e99900-81e0-11eb-9971-de0e6ee97367.png)<br>
- roadWithLane 객체의 draw메서드 동작<br>
![image](https://user-images.githubusercontent.com/44339530/110624026-01a2c000-81e1-11eb-8301-3626c6f1e85d.png)<br>
- 만약 기본 도로 표시 기능 + 차선 표시 + 교통량 표시를 한 번에 조합해야 한다면?
~~~
public class Client {
	public static void main(String[] args) {		
	Display roadWithLaneAndTraffic =
		new TrafficDecorator(
			new LaneDecorator(
				new RoadDisplay()));
		roadWithLaneAndTraffic.draw();
	}
}
~~~

- 교차로 기능을 추가한다면?<br>
![image](https://user-images.githubusercontent.com/44339530/110624336-652ced80-81e1-11eb-86c8-4b505175d2de.png)<br>
- 소스 코드
~~~
public class CrossingDecorator extends DisplayDecorator {
	public CrossingDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw();
		drawCrossing();
	}
	private void drawCrossing() {
		System.out.println("\t횡단보도 표시");		
	}	
}

public class Client {
	public static void main(String[] args) {		
	Display roadWithLaneAndTraffic =
		new LaneDecorator(
			new TrafficDecorator(
				new CrossingDecorator(
					new RoadDisplay())));
		roadWithCrossingAndTrafficAndLane.draw();
	}
}
~~~
- 실행 결과<br>
![image](https://user-images.githubusercontent.com/44339530/110624411-842b7f80-81e1-11eb-8688-9a8ca793dc4f.png)<br>

## 데커레이터 패턴의 일반적인 형태
![image](https://user-images.githubusercontent.com/44339530/110624809-09169900-81e2-11eb-95a9-628256d472ff.png)

## 데커레이터 패턴의 순차 다이어그램
![image](https://user-images.githubusercontent.com/44339530/110624820-0caa2000-81e2-11eb-966d-20a028c66a79.png)

## 데커레이터 패턴을 위에 적용한 클래스 다이어그램
![image](https://user-images.githubusercontent.com/44339530/110624833-116ed400-81e2-11eb-8b08-0703cce35135.png)

## 출처
- Java객체지향 디자인패턴(한빛미디어)