package prac1201a;

public class Main {

	public static void main(String[] args) {
		/*
		 * CleaningWithRobotCleaner myRoom = new CleaningWithRobotCleaner();
		 * 
		 * myRoom.clean(Brand.valueOf(args[0])); //argv[0]�� �ش��ϴ� Ŭ����Ÿ������ ��ȯ
		 */
		
		CleaningWithRobotCleaner hansung = new CleaningWithSamsungBot();
		hansung.clean(Brand.valueOf(args[0]));
		
	}

}
