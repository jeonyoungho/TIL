package abstractFatoryExample;

public class HansungRobotFactory extends AbstractRobotFactory {
	private static HansungRobotFactory factory;
	
	private HansungRobotFactory() {}
	
	public static HansungRobotFactory getInstance() {
		if(factory == null) factory = new HansungRobotFactory();
		return factory;
	}
	
	@Override
	public Head getHead() {
		return new HansungHead();
	}

	@Override
	public Body getBody() {
		return new HansungBody();
	}

	@Override
	public Hand getHand() {
		return new HansungHand();
	}

	@Override
	public Leg getLeg() {
		return new HansungLeg();
	}

}
