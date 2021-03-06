package ElevatorMotor;

public class Main {

	public static void main(String[] args) {
		/*
		 * Door lgDoor = DoorFactory.createDoor(VendorID.LG); Motor lgMotor =
		 * MotorFactory.createMotor(VendorID.LG);
		 * 
		 * lgMotor.setDoor(lgDoor);
		 * 
		 * lgDoor.open(); lgMotor.move(Direction.UP);
		 */
		
		
		String vendorName = args[0];
		VendorID vendorID;
		
		
		if(vendorName.equalsIgnoreCase("LG"))
			vendorID = VendorID.LG;
		else
			vendorID = VendorID.HYUNDAI;
		
		ElevatorFactory factory = ElevatorFactoryFactory.getFactory(vendorID);
		
		Door door = factory.createDoor();
		Motor motor = factory.createMotor();
		
		motor.setDoor(door);
		door.open();
		motor.move(Direction.UP);
	}

}
