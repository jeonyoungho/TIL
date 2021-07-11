# 컴퍼지트패턴(Composite Pattern)
- 부분(part)-전체(whole)의 관계를 가지는 객체들을 정의할 때 유용
- 클라이언트는 전체와 부분을 구분하지 않고 동일한 인터페이스를 사용할 수가 있다.

## 컴퓨터에 추가 장치 지원하기 Example
<img width="755" alt="스크린샷 2021-03-17 오후 2 02 19" src="https://user-images.githubusercontent.com/44339530/111417519-64d4ab00-8729-11eb-9262-053aa9f375ce.png"><br>

- 소스 코드
~~~
public class Keyboard {
	private int price;
	private int power;
	public Keyboard(int power, int price) {
		this.power = power; 
		this.price = price;
	}
	public int getPrice() { return price; }
	public int getPower() { return power; }
}
~~~

~~~
public class Body {
	private int price;
	private int power;
	public Body(int power, int price) {
		this.power = power; 
		this.price = price;
	}
	public int getPrice() { return price; }
	public int getPower() { return power; }
}
~~~

~~~
public class Monitor {
	private int price;
	private int power;
	public Monitor(int power, int price) {
		this.power = power; 
		this.price = price;
	}
	public int getPrice() { return price; }
	public int getPower() { return power; }
}
~~~

~~~
public class Computer {
	private Body body;
	private Keyboard keyboard;
	private Monitor monitor;
	
	public void addBody(Body body) { this.body = body; }
	public void addKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	public void addMonitor(Monitor monitor) { this.monitor = monitor; }
	public int getPrice() {
		int bodyPrice = body.getPrice();
		int keyboardPrice = keyboard.getPrice();
		int monitorPrice = monitor.getPrice();
		return bodyPrice + keyboardPrice + monitorPrice;
	}
	public int getPower() {
		int bodyPower = body.getPower();
		int keyboardPower = keyboard.getPower();
		int monitorPower = monitor.getPower();
		return bodyPower + keyboardPower + monitorPower;
	}
}
~~~

~~~
public class Client {
	public static void main(String[] args) {
		// 컴퓨터의 부품으로서 Body, Keyboard, Monitor 객체를 생성함
		Body body = new Body(100, 70);
		Keyboard keyboard = new Keyboard(5, 2);
		Monitor monitor = new Monitor(20, 30);
		
		// Computer 객체를 생성하고 부품 객체들을 설정함
		Computer computer = new Computer();
		computer.addBody(body);
		computer.addKeyboard(keyboard);
		computer.addMonitor(monitor);
		
		// 컴퓨터의 가격과 전력소비량을 구함
		int computerPrice = computer.getPrice();
		int computerPower = computer.getPower();
		System.out.println("Computer Power: " + computerPower + " W");
		System.out.println("Computer Price: " + computerPrice + " 만원");	
	}
}
~~~

- 실행 결과<br>
![image](https://user-images.githubusercontent.com/44339530/111417646-a5342900-8729-11eb-82d2-992f550057dc.png)<br>

#### 문제점
- 만약 부품으로서 Speaker를 추가해야 한다면? 또는 Mouse를 추가한다면?
    - ![image](https://user-images.githubusercontent.com/44339530/111417690-ba10bc80-8729-11eb-93f9-b1dc785347fc.png)<br>
    - <img width="402" alt="스크린샷 2021-03-17 오후 2 05 57" src="https://user-images.githubusercontent.com/44339530/111417792-e75d6a80-8729-11eb-9101-fd22d48fa5f7.png"><br>
    - <img width="719" alt="스크린샷 2021-03-17 오후 2 05 48" src="https://user-images.githubusercontent.com/44339530/111417773-e1678980-8729-11eb-81f6-0ba396dd4737.png"><br>

#### 해결책
- 구체적인 부품들을 일반화한 클래스를 정의하고 이를 가리키도록 설계<br>
![image](https://user-images.githubusercontent.com/44339530/111417855-fe9c5800-8729-11eb-8678-ee5fc7346aaa.png)<br>

- 소스 코드
~~~
public abstract class ComputerDevice {
	public abstract int getPrice();
	public abstract int getPower();
}
~~~

~~~
public class Keyboard extends ComputerDevice {
	private int price;
	private int power;
 
	public Keyboard(int power, int price) {
		this.power = power;
		this.price = price;
	}
	public int getPrice() { return price; }
	public int getPower() { return power; }
}
~~~

~~~
public class Computer extends ComputerDevice {
	// 복수 개의 ComputerDevice를 가리킴
	private List<ComputerDevice> components = new ArrayList<ComputerDevice>();
	// ComputerDevice를 Computer에 추가
	public void addComponent(ComputerDevice component) {
		components.add(component);
	}
	// ComputerDevice를 Computer에서 제거
	public void removeComponent(ComputerDevice component) {
		components.remove(component);
	}
	public int getPrice() {
		int price = 0;	
		for ( ComputerDevice component: components )
			price += component.getPrice();
		return price;
	}
	public int getPower() {
		int power = 0;	
		for ( ComputerDevice component: components )
			power += component.getPower();
		return power;
	}
}
~~~

~~~
public class Client {
	public static void main(String[] args) {
		// 컴퓨터의 부품으로서 Body, Keyboard, Monitor 객체를 생성함
		Body body = new Body(100, 70);
		Keyboard keyboard = new Keyboard(5, 2);
		Monitor monitor = new Monitor(20, 30);
		
		// Computer 객체를 생성하고 부품 객체들을 설정함
		Computer computer = new Computer();
		computer.addComponent(body);
		computer.addComponent(keyboard);
		computer.addComponent(monitor);
		
		int computerPrice = computer.getPrice();
		int computerPower = computer.getPower();
		System.out.println("Computer Power: " + computerPower + " W");
		System.out.println("Computer Price: " + computerPrice + " 만원");	
	}
}
~~~

- 만약 스피커가 새로 추가된다면?<br>
![image](https://user-images.githubusercontent.com/44339530/111418080-5d61d180-872a-11eb-872f-dea791850929.png)<br>

~~~
public class Client {
	public static void main(String[] args) {
		// 컴퓨터의 부품으로서 Body, Keyboard, Monitor, Speaker 객체를 생성함
		Body body = new Body(100, 70);
		Keyboard keyboard = new Keyboard(5, 2);
		Monitor monitor = new Monitor(20, 30);
		Speaker speaker = new Speaker(10, 10);
 
		// Computer 객체를 생성하고 부품 객체들을 설정함
		Computer computer = new Computer();
		computer.addComponent(body);
		computer.addComponent(keyboard);
		computer.addComponent(monitor);
		computer.addComponent(speaker);
		
		int computerPrice = computer.getPrice();
		int computerPower = computer.getPower();
		System.out.println("Computer Power: " + computerPower + " W");
		System.out.println("Computer Price: " + computerPrice + " 만원");	
	}
}
~~~

- 실행 결과<br>
![image](https://user-images.githubusercontent.com/44339530/111418125-74082880-872a-11eb-9e5e-e71a083ba8b5.png)
<br>

## 컴퍼지트 패턴의 일반적인 형태
<img width="801" alt="스크린샷 2021-03-17 오후 2 11 39" src="https://user-images.githubusercontent.com/44339530/111418258-b29de300-872a-11eb-8b98-1db81ed3d6b6.png">

## 컴퍼지트 패턴의 순차 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111418273-bcbfe180-872a-11eb-8857-b44de81f4cc2.png)

## 컴퍼지트 패턴을 위에 적용한 클래스 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111418281-c0536880-872a-11eb-932f-678474b19ec8.png)

## 출처
- Java객체지향 디자인패턴(한빛미디어)
