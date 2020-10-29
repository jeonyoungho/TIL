package ElevatorMotor;
public class DoorFactory {
	public static Door createDoor(VendorID motorVendorID) {
		Door door = null;
				
		if(motorVendorID == VendorID.HYUNDAI)
			door = new HyundaiDoor();
		else if(motorVendorID == VendorID.LG)
			door = new LGDoor();
		
		return door;
	}
}
