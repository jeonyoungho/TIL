package robotCleaner1;

public class CleaningWithRobotWithoutFactoryMethod {
	public void clean(Brand brand) {
		RobotCleaner robot = RobotCleanerFactory.getRobotCleaner(brand);
		robot.sweeping();
		robot.washing();
		robot.cleaning();
	}
}
