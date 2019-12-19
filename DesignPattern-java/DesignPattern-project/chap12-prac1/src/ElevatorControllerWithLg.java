
public class ElevatorControllerWithLg extends ElevatorController {
	private Motor lMotor;
	public ElevatorControllerWithLg(int id) {
		super(id);
	}

	@Override
	protected Motor getMotor() {
		if(lMotor == null)
			lMotor = new LGMotor();
	
		return lMotor;
	}

}
