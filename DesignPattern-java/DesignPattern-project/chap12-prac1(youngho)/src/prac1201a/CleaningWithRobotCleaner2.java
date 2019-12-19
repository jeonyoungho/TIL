package prac1201a;

public abstract class CleaningWithRobotCleaner2 {
	
	protected abstract RobotCleaner getCleaner();
	
	public void clean(Brand brand) {
		RobotCleaner robot = getCleaner(); //getRobotCleaner�� ��ü�� �����ϴ� å������ , ��ü���� �帧�� �״�� �������� ��ü�� �����ϴ� �κи� �޸��ϰڴ� , ���ø� �޼ҵ�! , GOF Factory Method����
		robot.sweeping(); //�۾��帧1
		robot.washing();  //�۾��帧2
		robot.cleaning(); //�۾��帧3
	}
	
}
