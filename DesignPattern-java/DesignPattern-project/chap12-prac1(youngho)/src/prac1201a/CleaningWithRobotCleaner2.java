package prac1201a;

public abstract class CleaningWithRobotCleaner2 {
	
	protected abstract RobotCleaner getCleaner();
	
	public void clean(Brand brand) {
		RobotCleaner robot = getCleaner(); //getRobotCleaner로 객체를 생성하는 책임전가 , 전체적인 흐름은 그대로 가져가되 객체를 생성하는 부분만 달리하겠다 , 템플릿 메소드! , GOF Factory Method패턴
		robot.sweeping(); //작업흐름1
		robot.washing();  //작업흐름2
		robot.cleaning(); //작업흐름3
	}
	
}
