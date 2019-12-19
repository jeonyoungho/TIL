package abstractFatoryExample;

public abstract class AbstractRobotFactory {
	public abstract Head getHead();
	public abstract Body getBody();
	public abstract Hand getHand();
	public abstract Leg getLeg();
	
	public Robot createRobot() {
		return new Robot();
	}
}
