package prac1201a;

public abstract class CleaningWithRobotCleaner {
	
	
	public void clean(Brand brand) {
		RobotCleaner robot = RobotCleanerFactory.getRobotCleanger(brand.HANSUNG); //getRobotCleaner�� ��ü�� �����ϴ� å������ , ��ü���� �帧�� �״�� �������� ��ü�� �����ϴ� �κи� �޸��ϰڴ� , ���ø� �޼ҵ�! , GOF Factory Method����
		robot.sweeping(); //�۾��帧1
		robot.washing();  //�۾��帧2
		robot.cleaning(); //�۾��帧3
	}

	protected RobotCleaner getCleaner() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
