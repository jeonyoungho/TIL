public class MotorFactory {
	public static Motor createMotor(VendorID motorVendorID) {
		Motor motor = null;
				
		if(motorVendorID == VendorID.HYUNDAI)
			motor = new HyundaiMotor();
		else if(motorVendorID == VendorID.LG)
			motor = new LGMotor();
		
		return motor;
	}
}