package robotCleaner1;

public class CleaningWithRobotFactory {
	private static CleaningWithRobot cr;
	public static CleaningWithRobot getCleaningWithRobot() {
		if(cr == null)
			cr = new CleaningWithRobot();
		
		return cr;
	}

}
