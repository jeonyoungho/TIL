
public class RobotFireCommand implements Command {
	private Robot theRobot;
	public void setTheRobot(Robot theRobot) {
		this.theRobot = theRobot;
	}
	public void execute() {
		theRobot.fireGun();
	}

}
