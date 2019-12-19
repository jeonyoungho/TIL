
public class ControlRoomDisplay implements Observer {

	private ElevatorController controller;
	private ElevatorPanel elevatorPanel;
	
	public ControlRoomDisplay(ElevatorController controller,ElevatorPanel elevatorPanel) {
		this.controller = controller;
		this.elevatorPanel = elevatorPanel;
	}

	public void update() {
		int curFloor =controller.getCurfloor();
		
		((ElevatorPanel) elevatorPanel).setCrText(Integer.toString(curFloor));

	}

}
