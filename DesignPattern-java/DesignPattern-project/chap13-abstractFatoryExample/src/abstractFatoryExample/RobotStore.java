package abstractFatoryExample;

public class RobotStore {
	public Robot assemble(AbstractRobotFactory f) { //조립하는 메소드 , 일관성있게 생성시키고 싶을때 AbstractRobotFactory 객체를 인자로 받아서 사용
		
		Robot robot = f.createRobot();
		Head head = f.getHead();
		Body body = f.getBody();
		Hand hand = f.getHand();
		Leg leg = f.getLeg();

		
		//.....
		//...
		robot.setHead(head);
		robot.setBody(body);
		robot.setHand(hand);
		robot.setLeg(leg);
		
		//....
		//..
		return robot;
		
	}
}
