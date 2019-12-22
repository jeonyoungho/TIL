package robotCleaner1;

public class RobotCleanerFactory {
	private static RobotCleaner hRobot =  new HansungRobotCleaner();
	private static RobotCleaner sRobot =  new SamsungRobotCleaner();
	public static RobotCleaner getRobotCleaner(Brand brand) {
		RobotCleaner robot = null;
		switch (brand) {
		case HANSUNG: robot = hRobot; break;
		case SAMSUNG: robot = sRobot; break;
		}
		
		return robot;
	}
}
