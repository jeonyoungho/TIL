package abstractFatoryExample;

public class AbstractRobotFactoryFactory {

	
	public static AbstractRobotFactory getRobotFactory(Brand brand) {
		AbstractRobotFactory factory = null;
		switch (brand) {
		case HANSUNG:
			factory = HansungRobotFactory.getInstance();
			break;
		case SAMSUNG:
			factory = SamsungRobotFactory.getInstance();
			break;
		}

		return factory;
	}
}
