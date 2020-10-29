
public class ElevatorControllerWithHyundai extends ElevatorController {
	private Motor hMotor;
	public ElevatorControllerWithHyundai(int id) {
		super(id);
	}

	@Override
	protected Motor getMotor() {
		if(hMotor == null)
			hMotor = new HyundaiMotor();
	
		return hMotor;
	}

}
