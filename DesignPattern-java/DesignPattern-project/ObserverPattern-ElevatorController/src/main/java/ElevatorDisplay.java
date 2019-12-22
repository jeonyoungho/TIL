
public class ElevatorDisplay implements Observer {
	private ElevatorController controller;
	private ElevatorPanel elevatorPanel;

	public ElevatorDisplay(ElevatorController controller,ElevatorPanel elevatorPanel) {
		this.controller = controller;
		this.elevatorPanel = elevatorPanel;
	}

	public void update() {
		int curFloor =controller.getCurfloor();
		
		((ElevatorPanel) elevatorPanel).setEdText(Integer.toString(curFloor));
	}


}
