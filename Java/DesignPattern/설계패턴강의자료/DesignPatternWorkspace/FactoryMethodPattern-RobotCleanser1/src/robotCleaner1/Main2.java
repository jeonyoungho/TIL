package robotCleaner1;

public class Main2 {

	public static void main(String[] args) {
		CleaningWithRobot cr = CleaningWithRobotFactory.getCleaningWithRobot();
		cr.clean(Brand.valueOf(args[0]));

	}

}
