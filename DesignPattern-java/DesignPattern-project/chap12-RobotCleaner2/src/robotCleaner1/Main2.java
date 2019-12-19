package robotCleaner1;

public class Main2 {

	public static void main(String[] args) {
		CleaningWithRobot crf = CleaningWithRobotFactory.getCleaningWithRobot();
		crf.clean(Brand.valueOf(args[0]));

	}

}
