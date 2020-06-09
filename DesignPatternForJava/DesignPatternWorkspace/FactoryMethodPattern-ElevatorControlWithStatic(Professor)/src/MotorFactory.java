
public class MotorFactory {
	public static Motor getMotor(MotorVendorID motorVendorID) {
		Motor motor = null;
				
		if(motorVendorID == MotorVendorID.HYUNDAI)
			motor = new HyundaiMotor();
		else if(motorVendorID == MotorVendorID.LG)
			motor = new LGMotor();
		
		return motor;
	}
}
