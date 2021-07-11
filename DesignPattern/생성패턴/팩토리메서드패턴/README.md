# 팩토리메서드패턴(Factory Method Pattern)
- 객체의 생성 코드를 별도의 클래스/메소드로 분리함으로써 객체 생성의 변화를 대비하는 데 유용
- 팩토리 메소드 패턴은 객체의 생성 코드를 별도의 클래스/메소드로 분리함으로써 객체 생성 방식의 변화를 대비하는 데 유용하다.<br>
![image](https://user-images.githubusercontent.com/44339530/111283097-16b89c80-8682-11eb-9f7d-f658fc0792cc.png)<br>

## 여러 방식의 엘리베이터 스케줄링 방법 지원하기 Example
- ElevatorManager 클래스: 여러 엘리베이터 중에서 스케줄링에 따라서 하나의 엘리베이터를 선택하고 이동시킴
    - ElevatorController 클래스: 하나의 엘리베이터 이동을 제어하는 클래스
    - ThroughputScheduler 클래스: 처리량을 기준으로 스케줄링하는 클래스<br>
    - ![image](https://user-images.githubusercontent.com/44339530/111279575-2a620400-867e-11eb-9fa0-b12ead5b63b8.png)<br>

- 소스 코드
~~~
public class ElevatorManager {

	private List<ElevatorController> controllers;

	private ThroughputScheduler scheduler;

	public ElevatorManager(int controllerCount) {
		controllers = new ArrayList<ElevatorController>(controllerCount);

		for ( int i = 0; i < controllerCount; i ++ ) {
			ElevatorController controller = new ElevatorController(i+1);
			controllers.add(controller);
		}

		scheduler = new ThroughputScheduler();
	}	

	void requestElevator(int destination, Direction direction) {
		// ThroughputScheduler를 이용해서 엘리베이터를 선택함	
		int selectedElevator = scheduler.selectElevator(this, destination, direction);		
		controllers.get(selectedElevator).gotoFloor(destination);  // 선택된 엘리베이터를 이동 시킴
	}
}

public class ThroughputScheduler {
	public int selectElevator(ElevatorManager manager, int destination, Direction direction) {
		return 0; // 임의로 선택한다.
	}
}

public class ElevatorController {
	private int id;
	private int curFloor;

	public ElevatorController(int id) {
		this.id = id;
		curFloor = 1;
	}

	public void gotoFloor(int destination) {
		System.out.print("Elevator [" + id + "] Floor: " + curFloor); // 현재 층 갱신, 즉 주어진 목적지 층으로 엘리베이터가 이동함
		curFloor = destination;
		System.out.println(" ==> " + curFloor);
	}
}
~~~

#### 문제점
- 현재 ElevatorManager는 ThroughputScheduler를 이용하고 있다. 즉 엘리베이터의 처리량을 최대화시키는 전략을 사용하고 있다. 만약 다른 스케쥴링 전략을 사용해야 한다면? 예를 들어 사용자의 대기시간을 최소화시키는 엘리베이터를 선택하는 전략을 사용해야 한다면?
- 게다가 스케쥴링 전략이 프로그램 실행 중에 변경을 해야 한다면 즉 동적 스케쥴링을 지원해야 한다면? 예를 들어 오전에는 대기시간 최소화 전략을 사용하고 오후에는 처리량 최대화 전략을 사용해야 한다면?

### 스트래티지 패턴을 활용한 스케줄링 전략 설계
![image](https://user-images.githubusercontent.com/44339530/111281916-c987fb00-8680-11eb-89a2-90b8a6a7d54d.png)<br>
<img width="764" alt="스크린샷 2021-03-16 오후 5 57 13" src="https://user-images.githubusercontent.com/44339530/111282156-0b18a600-8681-11eb-8ece-07e8e9416e0f.png"><br>

#### 해결책
- 스케줄링 전략에 맞는 객체를 생성하는 코드를 별도로 정의<br>
![image](https://user-images.githubusercontent.com/44339530/111282285-2f748280-8681-11eb-80b4-9cc069aa7042.png)<br>
~~~
public enum SchedulingStrategyID { RESPONSE_TIME, THROUGHPUT, DYNAMIC }
 
public class SchedulerFactory {
	public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID) {
		ElevatorScheduler scheduler = null;

		switch (strategyID) {
			case RESPONSE_TIME : scheduler = new ResponseTimeScheduler();
				 break;
			case THROUGHPUT : scheduler = new ThroughputScheduler(); break;
			case DYNAMIC : {  // 오전에는 대기 시간 최소화 전략, 오후에는 처리량 최대화 전략
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if ( hour < 12 ) // 오전
					scheduler = new ResponseTimeScheduler();
				else // 오후
					scheduler = new ThroughputScheduler();
				break;
			}
		}

		return scheduler;
	}
}

public class ElevatorManager {
	private List<ElevatorController> controllers;
	private SchedulingStrategyID strategyID;

	public ElevatorManager(int controllerCount, SchedulingStrategyID strategyID) {
		controllers = new ArrayList<ElevatorController>(controllerCount);

		for ( int i = 0; i < controllerCount; i ++ ) {
			ElevatorController controller = new ElevatorController(i+1);
			controllers.add(controller);
		}

		this.strategyID = strategyID;  // 스케줄링 전략을 설정함
	}
	
	public void setStrategyID(SchedulingStrategyID strategyID) { 
		this.strategyID = strategyID; 
	}
	
	void requestElevator(int destination, Direction direction) {
		// 주어진 전략 ID에 해당되는 ElevatorScheduler를 사용함	
		ElevatorScheduler scheduler = SchedulerFactory.getScheduler(strategyID);
		System.out.println(scheduler);
		int selectedElevator = scheduler.selectElevator(this, destination, direction);
		controllers.get(selectedElevator).gotoFloor(destination);
	}
}

public class Client {
	public static void main(String[] args) {
		ElevatorManager emWithResponseTimerScheduler =
			new ElevatorManager(2, SchedulingStrategyID.RESPONSE_TIME);
		emWithResponseTimerScheduler.requestElevator(10, Direction.UP);
		
		ElevatorManager emWithThroughputScheduler =
			new ElevatorManager(2, SchedulingStrategyID.THROUGHPUT);
		emWithThroughputScheduler.requestElevator(10, Direction.UP);
	
		ElevatorManager emWithDynamicScheduler =
			new ElevatorManager(2, SchedulingStrategyID.DYNAMIC);
		emWithDynamicScheduler.requestElevator(10, Direction.UP);
	}
}
~~~

- 실행 결과<br>
<img width="495" alt="스크린샷 2021-03-16 오후 6 00 57" src="https://user-images.githubusercontent.com/44339530/111282640-909c5600-8681-11eb-971b-fda85218d691.png"><br>

### 싱글턴패턴을 적용한 스케줄링 전략 클래스 설계
![image](https://user-images.githubusercontent.com/44339530/111282800-b9245000-8681-11eb-9c65-cd8506365eb6.png)<br>

~~~
public class SchedulerFactory {

	public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID) {
		ElevatorScheduler scheduler = null;

		switch ( strategyID ) {
			case RESPONSE_TIME :
				scheduler = ResponseTimeScheduler.getInstance(); break;
			case THROUGHPUT :
				scheduler = ThroughputScheduler.getInstance(); break;
			case DYNAMIC : {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if ( hour < 12 ) // 오전
					scheduler = ResponseTimeScheduler.getInstance();
				else // 오후
					scheduler = ThroughputScheduler.getInstance();
				break;
			}
		}

		return scheduler;
	}
}

public class ThroughputScheduler implements ElevatorScheduler {
	private static ElevatorScheduler scheduler;

	private ThroughputScheduler() {}

	public static ElevatorScheduler getInstance() {
		if ( scheduler == null ) scheduler = new ThroughputScheduler();
		return scheduler;
	}

	public int selectElevator(ElevatorManager manager, int destination, Direction direction) {		
		return 0;	
	}
}

public class ResponseTimeScheduler implements ElevatorScheduler {
	private static ElevatorScheduler scheduler;
	
	private ResponseTimeScheduler() {}

	public static ElevatorScheduler getInstance() {
		if ( scheduler == null ) scheduler = new ResponseTimeScheduler();
		return scheduler;
	}

	public int selectElevator(ElevatorManager manager,
		int destination, Direction direction) {	
		return 1;
	}
}

public class Client {
	public static void main(String[] args) {
		ElevatorManager emWithResponseTimerScheduler =
			new ElevatorManager(2, SchedulingStrategyID.RESPONSE_TIME);
		emWithResponseTimerScheduler.requestElevator(10, Direction.UP);
		
		ElevatorManager emWithThroughputScheduler =
			new ElevatorManager(2, SchedulingStrategyID.THROUGHPUT);
		emWithThroughputScheduler.requestElevator(10, Direction.UP);
	
		ElevatorManager emWithDynamicScheduler =
			new ElevatorManager(2, SchedulingStrategyID.DYNAMIC);
		emWithDynamicScheduler.requestElevator(10, Direction.UP);
	}
}
~~~
- 실행 결과<br>
<img width="503" alt="스크린샷 2021-03-16 오후 6 03 38" src="https://user-images.githubusercontent.com/44339530/111282992-f12b9300-8681-11eb-9c11-8df9e2f4d6ae.png"><br>

#### 상속을 이용한 팩토리 메서드 패턴의 적용
![image](https://user-images.githubusercontent.com/44339530/111283257-4667a480-8682-11eb-8ff0-ac8d1325a32d.png)<br>
- 소스 코드
~~~
public abstract class ElevatorManager {
	private List<ElevatorController> controllers;

	public ElevatorManager(int controllerCount) {
		controllers = new ArrayList<ElevatorController>(controllerCount);
		for ( int i = 0; i < controllerCount; i ++ ) {
			ElevatorController controller = new ElevatorController(i+1);
			controllers.add(controller);
		}
	}

	protected abstract ElevatorScheduler getScheduler();  // primitive 메서드

	void requestElevator(int destination, Direction direction) { // 템플릿 메서드
		// 하위클래스에서 오버라이드한 getScheduler 메서드를 호출
		ElevatorScheduler scheduler = getScheduler(); 
		int selectedElevator = scheduler.selectElevator(this,
			destination, direction);		
		controllers.get(selectedElevator).gotoFloor(destination);
	}
}

public class ElevatorManagerWithThroughputScheduling extends ElevatorManager {	
	public ElevatorManagerWithThroughputScheduling(int controllerCount) {
		super(controllerCount);
	}

	protected ElevatorScheduler getScheduler() {  // 처리량 최대화 전략을 사용함
		ElevatorScheduler scheduler = ThroughputScheduler.getInstance();
		return scheduler;
	}
}

public class ElevatorManagerWithResponseTimeScheduling extends ElevatorManager {
	public ElevatorManagerWithResponseTimeScheduling(int controllerCount) {
		super(controllerCount);
	}
	
	protected ElevatorScheduler getScheduler() {  // 대기 시간 최소화 전략을 사용함
		ElevatorScheduler scheduler = ResponseTimeScheduler.getInstance();
		return scheduler;
	}
}

public class ElevatorManagerWithDynamicScheduling extends ElevatorManager {	
	public ElevatorManagerWithDynamicScheduling(int controllerCount) {
		super(controllerCount);
	}
	
	protected ElevatorScheduler getScheduler() {  // 동적 스케줄링을 사용함
		ElevatorScheduler scheduler = null;
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if ( hour < 12 ) // 오전
			scheduler = ResponseTimeScheduler.getInstance();
		else // 오후
			scheduler = ThroughputScheduler.getInstance();
		return scheduler;
	}
}
~~~

## 팩토리 메서드 패턴의 일반적인 형태
<img width="781" alt="스크린샷 2021-03-16 오후 6 08 04" src="https://user-images.githubusercontent.com/44339530/111283507-8e86c700-8682-11eb-96c4-261323f46a09.png">

## 팩토리 메서드 패턴을 위에 적용한 클래스 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111283723-c4c44680-8682-11eb-8ba3-21841ae8fd66.png)

## 출처
- Java객체지향 디자인패턴(한빛미디어)