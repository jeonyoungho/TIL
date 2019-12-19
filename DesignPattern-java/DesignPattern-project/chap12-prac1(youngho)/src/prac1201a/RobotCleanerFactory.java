package prac1201a;

public class RobotCleanerFactory { //객체생성담당하는 클래스
	public static RobotCleaner hRobot = new HansungBot(); //미리 객체를 만들어놓고 메소드가 호출될때마다 반환만 해주는방법
	public static RobotCleaner sRobot = new SamsungBot();
		
	public static RobotCleaner getRobotCleanger(Brand brand) { //객체 생성하는 메소드
		RobotCleaner robot = null;
		
		switch(brand) {
		case HANSUNG : robot = hRobot; break;
		case SAMSUNG : robot = sRobot; break;
		}
		
		
		return robot;
	}
}
