public class HyundaiElevatorFactory extends ElevatorFactory {
	private static ElevatorFactory factory =null;
	
	public static ElevatorFactory getInstance() {
		if(factory == null)
			factory = new HyundaiElevatorFactory();
		return factory;
	}
	@Override
	public Motor createMotor() {
		return new HyundaiMotor();
	}

	@Override
	public Door createDoor() {
		// TODO Auto-generated method stub
		return new HyundaiDoor();
	}

}