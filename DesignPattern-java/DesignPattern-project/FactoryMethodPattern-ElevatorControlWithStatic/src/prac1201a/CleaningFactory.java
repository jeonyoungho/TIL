package prac1201a;

public class CleaningFactory {
	public static CleaningWithRobotCleaner getCleaning() {
		
//		return new CleaningWithRobotCleaner(); 
		return new CleaningWithSamsungBot();
	}
}
