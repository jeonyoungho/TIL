
public class Client {

	public static void main(String[] args) {
		//Motor lgMotor = MotorFactory.getMotor(MotorVendorID.LG);

		ElevatorController controller1 = new ElevatorControllerWithHyundai(1);

		controller1.gotoFloor(5);
		controller1.gotoFloor(3);

	//	Motor hyundaiMotor = MotorFactory.getMotor(MotorVendorID.HYUNDAI);
		ElevatorController controller2 = new ElevatorControllerWithLg(2);

		controller2.gotoFloor(4);
		controller2.gotoFloor(6);
	}

}
