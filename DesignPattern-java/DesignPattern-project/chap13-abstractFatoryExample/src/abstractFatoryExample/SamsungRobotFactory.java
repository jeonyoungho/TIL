package abstractFatoryExample;

public class SamsungRobotFactory extends AbstractRobotFactory {
	private static SamsungRobotFactory factory;
	
	private SamsungRobotFactory() {}
	
	public static SamsungRobotFactory getInstance() {
		if(factory == null) factory = new SamsungRobotFactory();
		return factory;
	}
	@Override
	public Head getHead() {
		return new SamsungHead();
	}

	@Override
	public Body getBody() {
		return new SamsungBody();
	}

	@Override
	public Hand getHand() {
		return new SamsungHand();
	}

	@Override
	public Leg getLeg() {
		return new SamsungLeg();
	}

}
