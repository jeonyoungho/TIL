package prac1201a;

public class cleaningWithHansungBot extends CleaningWithRobotCleaner {

	@Override
	protected RobotCleaner getCleaner() {
		return new HansungBot();
	}

}
