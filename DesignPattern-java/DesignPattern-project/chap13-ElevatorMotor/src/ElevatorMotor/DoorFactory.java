package ElevatorMotor;
public class DoorFactory {
	public static Door createDoor(VendorID motorVendorID) {
		Door door = null;
				
		if(motorVendorID == motorVendorID.HYUNDAI)
			door = new HyundaiDoor();
		else if(motorVendorID == motorVendorID.LG)
			door = new LGDoor();
		
		return door;
	}
}
