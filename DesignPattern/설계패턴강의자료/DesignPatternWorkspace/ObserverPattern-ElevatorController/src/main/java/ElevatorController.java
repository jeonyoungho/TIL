
public class ElevatorController extends Subject{
	private ElevatorPanel elevatorPanel;
	private int curFloor =1;
	
	public ElevatorController(ElevatorPanel elevatorPanel) {
		this.elevatorPanel = elevatorPanel;
	}
	
	public void gotoFloor(int destination) {
		curFloor = destination;
		
		((ElevatorPanel) elevatorPanel).close();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		
		notifyObservers();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		
		((ElevatorPanel) elevatorPanel).open();
	}
	
	public int getCurfloor() {
		return curFloor;
	}
}
