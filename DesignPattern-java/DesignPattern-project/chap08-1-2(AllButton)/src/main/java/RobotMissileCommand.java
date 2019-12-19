
public class RobotMissileCommand implements Command {
	Robot theRobot;
	
	public void setTheRobot(Robot theRobot) {
		this.theRobot = theRobot;
	}

	public void execute() {
		theRobot.missile();
	}

}
