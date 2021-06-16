# 추상팩토리메서드패턴(Abstract Factory Method Pattern)
- 관련성이 있는 여러 종류의 객체를 일관된 방식으로 생성하는 경우에 유용

## 엘리베이터 부품 업체의 변경하기 Example
- LG와 현대 업체의 모터와 문을 지원하는 클래스<br>
![image](https://user-images.githubusercontent.com/44339530/111414764-e9242f80-8723-11eb-8bb1-113487282652.png)<br>

- 템플릿 메서드의 적용<br>
<img width="657" alt="스크린샷 2021-03-17 오후 1 24 12" src="https://user-images.githubusercontent.com/44339530/111414839-1244c000-8724-11eb-9f4c-a3968c85d27c.png"><br>

~~~
public abstract class Door {
	private DoorStatus doorStatus ;
	public Door() {
		doorStatus = DoorStatus.CLOSED ;
	}
	public DoorStatus getDoorStatus() {
		return doorStatus ;
	}
	public void close() {  // 템플릿 메서드
		if ( doorStatus == DoorStatus.CLOSED ) return ;
		doClose() ;
		doorStatus = DoorStatus.CLOSED ;
	}
	protected abstract void doClose() ;
	public void open() {  // 템플릿 메서드
		if ( doorStatus == DoorStatus.OPENED ) return ;
		doOpen() ;
		doorStatus = DoorStatus.OPENED ;
	}
	protected abstract void doOpen() ;
}

public class LGDoor extends Door {
	protected void doClose() {
		System.out.println("close LG Door") ;		
	}
	protected void doOpen() {
		System.out.println("open LG Door") ;
	}
}

public class HyundaiDoor extends Door {
	protected void doClose() {
		System.out.println("close Hyundai Door") ;		
	}
	protected void doOpen() {
		System.out.println("open Hyundai Door") ;
	}
}
~~~
- 팩토리 메서드 패턴의 적용: MotorFactory, DoorFactory<br>
![image](https://user-images.githubusercontent.com/44339530/111414935-428c5e80-8724-11eb-8454-efc603257565.png)<br>
~~~
public enum VendorID { LG, HYUNDAI }
public class MotorFactory {
	public static Motor createMotor(VendorID vendorID) {
		Motor motor = null ;
		switch ( vendorID ) {
		case LG : motor = new LGMotor() ; break ;
		case HYUNDAI : motor = new HyundaiMotor() ; break ;
		}	
		return motor ;
	}
}
~~~

~~~
public class DoorFactory {
	public static Door createDoor(VendorID vendorID) {
		Door door = null ;
		switch ( vendorID ) {
		case LG : door = new LGDoor() ; break ;
		case HYUNDAI : door = new HyundaiDoor() ; break ;
		}	
		return door ;
	}
}
~~~

- 클라이언트 코드
~~~
public class Client {
	public static void main(String[] args) {
		Door lgDoor = DoorFactory.createDoor(VendorID.LG) ;
		Motor lgMotor = MotorFactory.createMotor(VendorID.LG) ;
		lgMotor.setDoor(lgDoor) ;
		
		lgDoor.open() ;
		lgMotor.move(Direction.UP) ;
	}
}
~~~
- 실행 결과<br>
![image](https://user-images.githubusercontent.com/44339530/111415056-84b5a000-8724-11eb-8247-52af54ee9a30.png)<br>

#### 문제점
- 현재 프로그램은 LG의 부품(LGMotor와 LGDoor)를 사용하고 있다. 만약 다른 제조업체의 부품을 사용해야 한다면? 예를 들어 LG 부품 대신에 현대의 부품(HyundaiMotor와 HyundaiDoor)를 사용해야 한다면?
    - 팩토리 메서드 패턴을 이용한 현대 부품 사용
    ~~~
    public class Client {
        public static void main(String[] args) {			
            Door hyundaiDoor = DoorFactory.createDoor(VendorID.HYUNDAI) ;
            Motor hyundaiMotor = MotorFactory.createMotor(VendorID.HYUNDAI) ;
            hyundaiMotor.setDoor(hyundaiDoor) ;
            
            hyundaiDoor.open() ;
            hyundaiMotor.move(Direction.UP) ;
        }
    } 
    ~~~
    - 부품 별로 팩토리를 구현해야 함
    ~~~
    public class Client {
	public static void main(String[] args) {			
		Door hyundaiDoor = DoorFactory.createDoor(VendorID.HYUNDAI) ;
		Motor hyundaiMotor = MotorFactory.createMotor(VendorID.HYUNDAI) ;
		hyundaiMotor.setDoor(hyundaiDoor) ;
		ArrivalSensor hyundaiArrivalSensor = 
			ArrivalSensorFactory.createArrivalSensor (VendorID.HYUNDAI) ;
		WeightSensor hyundaiWeightSensor = 
			WeightSensorFactory.createWeightSensor (VendorID.HYUNDAI) ;
		ElevatorLamp hyundaiElevatorLamp = 
			ElevatorLampFactory.createElevatorLamp (VendorID.HYUNDAI) ;
		FloorLamp hyundaiFloorLamp = FloorLampFactory.createFloorLamp (VendorID.HYUNDAI) ;
		DirectionLamp hyundaiDirectionLamp = 
			DirectionLampFactory.createDirectionLamp (VendorID.HYUNDAI) ;
		Speaker hyundaiSpeaker = SpeakerFactory.createSpeaker (VendorID.HYUNDAI) ;
		ElevatorButton hyundaiElevatorButton = 
			ElevatorButtonFactory.createElevatorButton (VendorID.HYUNDAI) ;
		FloorButton hyundaiFloorButton = 
			FloorButtonFactory.createElevatorFloorButton (VendorID.HYUNDAI) ;
		hyundaiDoor.open() ;
		hyundaiMotor.move(Direction.UP) ;
	}
    }
    ~~~
    - 코드량이 너무 방대해지고 하나씩 일일이 부품을 추가해줘야하는 문제점 발생

- 게다가 새로운 제조업체의 부품을 사용해야 한다면? 예를 들어 삼성에서 엘리베이터 부품을 생산하기 시작해서 SamsungMotor와 SamsungDoor 클래스를 사용해야 한다면?
    - 각 팩토리에서 새로운 제조 업체 부품을 생성하도록 수정이 필요함<br>
    - <img width="726" alt="스크린샷 2021-03-17 오후 1 32 46" src="https://user-images.githubusercontent.com/44339530/111415438-44a2ed00-8725-11eb-8e72-65bad4ab5b82.png"><br>

#### 해결책
- 부품이 아니라 제조업체 별로 팩토리를 정의함<br>
![image](https://user-images.githubusercontent.com/44339530/111415514-6bf9ba00-8725-11eb-81e2-2d8b4b52a545.png)<br>

- 제조업체의 팩토리 클래스의 공통 추상 팩토리 클래스를 정의<br>
<img width="711" alt="스크린샷 2021-03-17 오후 1 34 36" src="https://user-images.githubusercontent.com/44339530/111415568-85026b00-8725-11eb-9be1-82451ec2e246.png"><br>

- 소스 코드
~~~
public abstract class ElevatorFactory {
	public abstract Motor createMotor() ;
	public abstract Door createDoor() ;
}

public class LGElevatorFactory extends ElevatorFactory {
	public Motor createMotor() {
		return new LGMotor() ;
	}
	public Door createDoor() {
		return new LGDoor() ;
	}
}

public class HyundaiElevatorFactory extends ElevatorFactory {
	public Motor createMotor() {
		return new HyundaiMotor() ;
	}
	public Door createDoor() {
		return new HyundaiDoor() ;
	}
}

public class Client {
	public static void main(String[] args) {
		ElevatorFactory factory = null ;
		String vendorName = args[0] ;
		if ( vendorName.equalsIgnoreCase("LG") )
			factory = new LGElevatorFactory() ;
		else
			factory = new HyundaiElevatorFactory() ;
		Door door = factory.createDoor() ;
		Motor motor = factory.createMotor() ;
		motor.setDoor(door) ;
		
		door.open() ;
		motor.move(Direction.UP) ;
	}
}
~~~

- 실행 결과<br>
<img width="524" alt="스크린샷 2021-03-17 오후 1 36 36" src="https://user-images.githubusercontent.com/44339530/111415705-cc88f700-8725-11eb-88ae-003ac132fcd5.png"><br>

- 새로운 제조 업체가 새로 추가되면? (삼성 부품의 지원)<br>
![image](https://user-images.githubusercontent.com/44339530/111415714-d1e64180-8725-11eb-9d4c-52faeadc4390.png)<br>

- 소스 코드
~~~
public class SamsungElevatorFactory extends ElevatorFactory {
	public Motor createMotor() {
		return new SamsungMotor() ;
	}
	public Door createDoor() {
		return new SamsungDoor() ;
	}
}

public class SamsungDoor extends Door {
	protected void doClose() {
		System.out.println("close Samsung Door") ;		
	}
	protected void doOpen() {
		System.out.println("open Samsung Door") ;
	}
}

public class SamsungMotor extends Motor {	
	protected void moveMotor(Direction direction) {
		System.out.println("move Samsung Motor") ;
	}
}

public class Client {
	public static void main(String[] args) {	
		ElevatorFactory factory = null ;
		String vendorName = args[0] ;
		if ( vendorName.equalsIgnoreCase("LG") )
			factory = new LGElevatorFactory() ;
		else if ( vendorName.equalsIgnoreCase("Samsung") )
			factory = new SamsungElevatorFactory() ;
		else
			factory = new HyundaiElevatorFactory() ;
		Door door = factory.createDoor() ;
		Motor motor = factory.createMotor() ;
		motor.setDoor(door) ;
		
		door.open() ;
		motor.move(Direction.UP) ;
	}
}
~~~

- 실행 결과<br>
<img width="367" alt="스크린샷 2021-03-17 오후 1 38 30" src="https://user-images.githubusercontent.com/44339530/111415838-11149280-8726-11eb-8d03-d8ff5413c716.png"><br>

- 패턴의 추가적인 적용<br>
    - 제조업체 별로 Factory 클래스를 생성하는 부분을 팩토리 메소드 패턴을 적용하여 설계
    - 제조업체 별 팩토리는 1개만 필요하다면 싱글턴 패턴을 적용
    ~~~
    if ( vendorName.equalsIgnoreCase("LG") )
        factory = new LGElevatorFactory() ;
    else if ( vendorName.equalsIgnoreCase("Samsung") )
        factory = new SamsungElevatorFactory() ;
    else
        factory = new HyundaiElevatorFactory() ;
    ~~~
    - <img width="773" alt="스크린샷 2021-03-17 오후 1 40 16" src="https://user-images.githubusercontent.com/44339530/111415971-5042e380-8726-11eb-8f8a-d0e7c7c2e29b.png"><br>
    - 소스 코드
    ~~~
    public class ElevatorFactoryFactory {	
        public static ElevatorFactory getFactory(VendorID vendorID) {  // 팩토리 메서드
            ElevatorFactory factory = null ;	
            switch ( vendorID ) {
            case LG: factory = LGElevatorFactory.getInstance() ; break ;
            case HYUNDAI : factory = HyundaiElevatorFactory.getInstance() ; break ;
            case SAMSUNG : factory = SamsungElevatorFactory.getInstance() ; break ;
            }
            return factory ;
        }
    }
    ~~~
    ~~~
    public class LGElevatorFactory extends ElevatorFactory {  // 싱글턴을 적용한 LG 팩토리
	private static ElevatorFactory factory ;
	private LGElevatorFactory() {}
	public static ElevatorFactory getInstance() {
		if ( factory == null ) factory = new LGElevatorFactory() ;
		return factory ;
	}
	public Motor createMotor() { return new LGMotor() ; }
	public Door createDoor() { return new LGDoor() ; }
    }
    ~~~
    ~~~
    public class Client {
	public static void main(String[] args) {
		String vendorName = args[0] ;
		VendorID vendorID ;
		if ( vendorName.equalsIgnoreCase("LG")) vendorID = VendorID.LG ;
		else if ( vendorName.equalsIgnoreCase("Samsung"))
			vendorID = VendorID.SAMSUNG ;
		else vendorID = VendorID.HYUNDAI ;
		
		ElevatorFactory factory = ElevatorFactoryFactory.getFactory(vendorID) ; // 제조사별 팩토리 생성하는 부분
		
		Door door = factory.createDoor() ;
		Motor motor = factory.createMotor() ;
		motor.setDoor(door) ;
		
		door.open() ;
		motor.move(Direction.UP) ;
	}
    }
    ~~~
    
## 추상 팩토리 패턴의 일반적인 형태
![image](https://user-images.githubusercontent.com/44339530/111416329-0d354000-8727-11eb-9406-606314767471.png)

## 추상 팩토리 패턴의 순차 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111416340-132b2100-8727-11eb-85a7-03a09dca4e4e.png)

## 추상 팩토리 패턴을 위에 적용한 클래스 다이어그램
![image](https://user-images.githubusercontent.com/44339530/111416342-16261180-8727-11eb-99dc-9ff064a14064.png)

## 출처
- Java객체지향 디자인패턴(한빛미디어)






