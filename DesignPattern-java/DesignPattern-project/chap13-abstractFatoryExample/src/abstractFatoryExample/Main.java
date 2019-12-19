package abstractFatoryExample;

public class Main {

	public static void main(String[] args) {
	
		RobotStore myStore = new RobotStore();
		
		Brand brand = Brand.valueOf(args[0]);
		AbstractRobotFactory factory = null;
		factory = AbstractRobotFactoryFactory.getRobotFactory(brand); //이렇게 할시 매번 팩토리객체도 만들어주는 문제점발생
		
		myStore.assemble(factory);
		
	}

}
