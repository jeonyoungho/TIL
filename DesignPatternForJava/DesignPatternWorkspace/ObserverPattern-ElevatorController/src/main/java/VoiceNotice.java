
public class VoiceNotice implements Observer {

	private ElevatorController controller;
	private ElevatorPanel elevatorPanel;
	public VoiceNotice(ElevatorController controller,ElevatorPanel elevatorPanel) {
		this.controller = controller;
		this.elevatorPanel = elevatorPanel;
	}

	public void update() {
		int curFloor =controller.getCurfloor();
		
		((ElevatorPanel) elevatorPanel).setVnText("This is "+Integer.toString(curFloor) + " floor");
	
	}


}
