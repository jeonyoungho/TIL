package ElevatorMotor;

public class LGElevatorFactory extends ElevatorFactory {
	private static ElevatorFactory factory =null;
	
	public static ElevatorFactory getInstance() {
		if(factory == null)
			factory = new LGElevatorFactory();
		return factory;
	}
	
	@Override
	public Motor createMotor() {
		return new LGMotor();
	}

	@Override
	public Door createDoor() {
		// TODO Auto-generated method stub
		return new LGDoor();
	}

}
