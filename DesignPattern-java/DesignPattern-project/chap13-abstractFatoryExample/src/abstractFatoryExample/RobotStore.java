package abstractFatoryExample;

public class RobotStore {
	public Robot assemble(AbstractRobotFactory f) { //�����ϴ� �޼ҵ� , �ϰ����ְ� ������Ű�� ������ AbstractRobotFactory ��ü�� ���ڷ� �޾Ƽ� ���
		
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
