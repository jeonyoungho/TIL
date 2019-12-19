package prac1201a;

public class Main1 {

	public static void main(String[] args) {
		CleaningWithRobotCleaner cr = CleaningFactory.getCleaning();
		cr.clean(Brand.valueOf(args[0]));
	}

}
