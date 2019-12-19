package prac1201a;

public class Main {

	public static void main(String[] args) {
		/*
		 * CleaningWithRobotCleaner myRoom = new CleaningWithRobotCleaner();
		 * 
		 * myRoom.clean(Brand.valueOf(args[0])); //argv[0]을 해당하는 클래스타입으로 변환
		 */
		
		CleaningWithRobotCleaner hansung = new CleaningWithSamsungBot();
		hansung.clean(Brand.valueOf(args[0]));
		
	}

}
