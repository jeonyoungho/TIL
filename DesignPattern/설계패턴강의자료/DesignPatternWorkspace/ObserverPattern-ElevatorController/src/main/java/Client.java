
public class Client {

	public static void main(String[] args) {
		
		ElevatorPanel p = new ElevatorPanel();
		ElevatorController controller = new ElevatorController(p);
		ElevatorDisplay elevatorDisplay = new ElevatorDisplay(controller,p);
		VoiceNotice voiceNotice = new VoiceNotice(controller,p);
		FloorDisplay floorDisplay = new FloorDisplay(controller,p);
		ControlRoomDisplay controlRoomDisplay = new ControlRoomDisplay(controller,p);
		
		controller.addObserver(elevatorDisplay);
		controller.addObserver(voiceNotice);
		controller.addObserver(floorDisplay);
		controller.addObserver(controlRoomDisplay);
		
		controller.gotoFloor(5);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controller.gotoFloor(10);
		
		
		
	}

}
