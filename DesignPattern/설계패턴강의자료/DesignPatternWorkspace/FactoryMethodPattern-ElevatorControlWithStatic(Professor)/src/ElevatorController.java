
public abstract class ElevatorController {
	private int id;
	private int curFloor = 1;
	private Motor motor;
	public ElevatorController(int id) {
		this.id = id;
	}
	
	public void gotoFloor(int destination) {
		if(destination == curFloor)
			return;
		
		Direction direction;
		
		if(destination > curFloor)
			direction = Direction.UP;
		else
			direction = Direction.DOWN;
		
		motor = getMotor();
		
		motor.move(direction);
		
		System.out.println("Elevator [" + id + "] Floor: " + curFloor);
		curFloor = destination;
		System.out.println("==> " + curFloor + " with " + motor.getClass().getName());
		
		motor.stop();
	}
	
	protected abstract Motor getMotor();
	
	
}
