import javax.swing.JPanel;

public class FloorDisplay implements Observer {
	private ElevatorController controller;
	private JPanel elevatorPanel;
	public FloorDisplay(ElevatorController controller,JPanel elevatorPanel) {
		this.controller = controller;
		this.elevatorPanel = elevatorPanel;
	}

	public void update() {
		int curFloor =controller.getCurfloor();
		
		((ElevatorPanel) elevatorPanel).setFdText(Integer.toString(curFloor));
	}

}
