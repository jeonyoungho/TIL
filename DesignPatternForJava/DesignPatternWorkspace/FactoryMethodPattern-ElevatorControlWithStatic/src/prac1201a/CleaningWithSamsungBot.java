package prac1201a;

public class CleaningWithSamsungBot extends CleaningWithRobotCleaner {

	@Override
	protected RobotCleaner getCleaner() {
		return new SamsungBot();
	}

}
