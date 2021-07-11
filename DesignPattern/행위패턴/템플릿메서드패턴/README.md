# 템플릿메서드패턴(Template Method Pattern)
- <b>전체적으로 동일하면서 부분적으로 상이한 문장을 가지는 메소드의 코드 중복을 최소화할 때 유용</b>
- 템플릿 메소드 패턴은 전체적인 알고리즘을 구현하면서 상이한 부분은 하위 클래스에서구현할 수 있도록 해 주는 디자인 패턴으로서 전체적인 알고리즘의 코드를 재사용하는 데 유용하다.
- 전체적인 틀은 상위클래스에게 상속받고 변경되는 부분만 하위클래스에서 오버라이딩 하는 패턴
- 변화되는 부분만 추상 메소드로 만들어준다

## 엘리베이터 제어 프로그램 Example
- 엘리베이터 제어 시스템에서 모터를 구동시키는 기능
    - HyundaiMotor 클래스: 모터를 제어하여 엘리베이터를 이동시키는 클래스
    - Door 클래스: 문을 열거나 닫는 기능을 제공하는 클래스<br>
![image](https://user-images.githubusercontent.com/44339530/111251025-c75a7800-8651-11eb-8a12-61a138ac0ba0.png)<br>
![image](https://user-images.githubusercontent.com/44339530/111251199-1c968980-8652-11eb-9ae3-b899cfc5252a.png)<br>
- 소스 코드
~~~
public enum DoorStatus { CLOSED, OPENED }
public enum MotorStatus { MOVING, STOPPED}

public class Door {
	private DoorStatus doorStatus;
	public Door() {
		doorStatus = DoorStatus.CLOSED;
	}
	public DoorStatus getDoorStatus() {
		return doorStatus;
	}
	public void close() {
		doorStatus = DoorStatus.CLOSED;
	}
	public void open() {
		doorStatus = DoorStatus.OPENED;
	}
}

public class HyundaiMotor {
	private Door door;
	private MotorStatus motorStatus;	
	public HyundaiMotor(Door door) {
		this.door = door;
		motorStatus = MotorStatus.STOPPED;  // 초기에는 멈춘 상태
	}	
	private void moveHyundaiMotor(Direction direction) {
		// Hyundai Motor를 구동시킨다.
	}
	public MotorStatus getMotorStatus() { return motorStatus; }
	private void setMotorStatus(MotorStatus motorStatus) { this.motorStatus = motorStatus; }
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if (  motorStatus == MotorStatus.MOVING ) return;  // 이미 이동 중이면 아무 작업을 하지 않음
		
		DoorStatus doorStatus = door.getDoorStatus();
		if ( doorStatus == DoorStatus.OPENED ) door.close();  // 만약 문이 열려 있으면 먼저 문을 닫음
		
		moveHyundaiMotor(direction);  // 모터를 주어진 방향으로 이동
		setMotorStatus(MotorStatus.MOVING);  // 모터 상태를 이동 중으로 변경함
	}
}

public class Client {
	public static void main(String[] args) {
		Door door = new Door();
		HyundaiMotor hyundaiMotor = new HyundaiMotor(door);
		hyundaiMotor.move(Direction.UP);
	}
}
~~~

#### 문제점
- HyundaiMotor 클래스는 현대모터를 구동시킨다. 만약 다른 회사의 모터를 제어해야 한다면? 예를 들어 LG모터를 구동시키기 위해서는 어떻게 해야 할까?
    - <b>=> 내부소스코드 수정 (OCP위반)</b>
~~~
public class LGMotor {
	private Door door;
	private MotorStatus motorStatus;
	public LGMotor(Door door) {
		this.door = door; motorStatus = MotorStatus.STOPPED;
	}	
	private void moveLGMotor(Direction direction) {
		// LG Motor를 구동시킴
	}
	public MotorStatus getMotorStatus() { return motorStatus; }
	private void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if (  motorStatus == MotorStatus.MOVING ) return;
		DoorStatus doorStatus = door.getDoorStatus();
		if ( doorStatus == DoorStatus.OPENED ) door.close();	
		moveLGMotor(direction);  // move 메서드는 이 문장을 제외하면 HyundaiMotor와 동일함
		setMotorStatus(MotorStatus.MOVING);
	}
}
~~~

![image](https://user-images.githubusercontent.com/44339530/111251557-d4c43200-8652-11eb-91ac-331665b86dbc.png)<br>
~~~
public abstract class Motor { // HyundaiMotor와 LGMotor에 공통적인 기능을 구현하는 클래스
	protected Door door;
	private MotorStatus motorStatus;
	
	public Motor(Door door) {
		this.door = door;
		motorStatus = MotorStatus.STOPPED;
	}	
	public MotorStatus getMotorStatus() {
		return motorStatus;
	}
	protected void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}
}

public class HyundaiMotor extends Motor { // Motor를 상속받아서 HyundaiMotor를 구현함
	public HyundaiMotor(Door door) {
		super(door);
	}	
	private void moveHyundaiMotor(Direction direction) {
		// Hyundai Motor를 구동시킨다.
	}
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if (  motorStatus == MotorStatus.MOVING ) return;
		
		DoorStatus doorStatus = door.getDoorStatus();
		if ( doorStatus == DoorStatus.OPENED )
			door.close();
		
		moveHyundaiMotor(direction);  // move 메서드는 이 구문을 제외하면 LGMotor와 동일함
		
		setMotorStatus(MotorStatus.MOVING);
	}
}

public class LGMotor extends Motor {
	public LGMotor(Door door) {
		super(door);
	}	
	private void moveLGMotor(Direction direction) {
		// LG Motor를 구동시킨다.
	}
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if (  motorStatus == MotorStatus.MOVING ) return;
		
		DoorStatus doorStatus = door.getDoorStatus();
		if ( doorStatus == DoorStatus.OPENED )
			door.close();
		
		moveLGMotor(direction); // move 메서드는 이 구문을 제외하면 HyundaiMotor와 동일함
		
		setMotorStatus(MotorStatus.MOVING);
	}
}
~~~
<img width="863" alt="스크린샷 2021-03-16 오후 12 55 30" src="https://user-images.githubusercontent.com/44339530/111253656-e576a700-8656-11eb-8f51-ad3ce42bfc72.png"><br>

- <b>HyundaiMotor클래스와 LGMotor클래스의 move()메서드에서 코드 중복 발생!!!</b>

#### 해결책
- move 메서드에서 공통적인 부분을 상위 클래스 Motor로 이동<br>
![image](https://user-images.githubusercontent.com/44339530/111253915-56b65a00-8657-11eb-8cfe-b0fc2d471a43.png)<br>
<img width="827" alt="스크린샷 2021-03-16 오후 12 59 43" src="https://user-images.githubusercontent.com/44339530/111253998-7c436380-8657-11eb-94a9-40fc8a759116.png"><br>
<img width="820" alt="스크린샷 2021-03-16 오후 1 00 54" src="https://user-images.githubusercontent.com/44339530/111254092-a6952100-8657-11eb-9feb-83b763372b57.png"><br>

#### 템플릿 메서드의 개념
<img width="789" alt="스크린샷 2021-03-16 오후 1 04 23" src="https://user-images.githubusercontent.com/44339530/111254328-2327ff80-8658-11eb-932e-798c497e7764.png"><br>

## 템플릿 메서드 패턴의 일반적인 형태
![image](https://user-images.githubusercontent.com/44339530/111254703-f32d2c00-8658-11eb-8cd1-f06ea5a18140.png)

## 템플릿 메서드 패턴의 순차 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111254782-1657db80-8659-11eb-9b3f-02f8ba10e83f.png)

## 템플릿 메서드 패턴을 위에 적용한 클래스 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111254791-1d7ee980-8659-11eb-85e5-fff8a3c52aed.png)

## 출처
- Java객체지향 디자인패턴(한빛미디어)