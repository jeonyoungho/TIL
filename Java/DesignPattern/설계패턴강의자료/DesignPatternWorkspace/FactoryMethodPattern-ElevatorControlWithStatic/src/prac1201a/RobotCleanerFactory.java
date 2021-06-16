package prac1201a;

public class RobotCleanerFactory { //��ü��������ϴ� Ŭ����
	public static RobotCleaner hRobot = new HansungBot(); //�̸� ��ü�� �������� �޼ҵ尡 ȣ��ɶ����� ��ȯ�� ���ִ¹��
	public static RobotCleaner sRobot = new SamsungBot();
		
	public static RobotCleaner getRobotCleanger(Brand brand) { //��ü �����ϴ� �޼ҵ�
		RobotCleaner robot = null;
		
		switch(brand) {
		case HANSUNG : robot = hRobot; break;
		case SAMSUNG : robot = sRobot; break;
		}
		
		
		return robot;
	}
}
