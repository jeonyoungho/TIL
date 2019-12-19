package strategyEx1;

public abstract class Robot {
	private String name;
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;

	public void move() {
		movingStrategy.move();
	};
	
	public void attack() {
		attackStrategy.attack();
	}
	
	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public MovingStrategy getMovingStrategy() {
		return movingStrategy;
	}
	
	public AttackStrategy getAttackStrategy() {
		return attackStrategy;
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy = movingStrategy;
	}

}

