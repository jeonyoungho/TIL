
public class MotorFactory {
	public static Motor getMotor(MotorVendorID motorVendorID) {
		Motor motor = null;
				
		if(motorVendorID == motorVendorID.HYUNDAI)
			motor = new HyundaiMotor();
		else if(motorVendorID == motorVendorID.LG)
			motor = new LGMotor();
		
		return motor;
	}
}
