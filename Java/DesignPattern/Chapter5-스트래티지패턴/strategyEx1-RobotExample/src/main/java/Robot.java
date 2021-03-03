
public abstract class Robot {
	private String name;
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;

	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy = movingStrategy;
	}
	
	public MovingStrategy getMovingStrategy() {
		return movingStrategy;
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}
	
	public AttackStrategy getAttackStrategy() {
		return attackStrategy;
	}
	
	public void move() {
		System.out.print("["+name+"]");
		movingStrategy.move();
	};
	
	public void attack() {
		System.out.print("["+name+"]");
		attackStrategy.attack();
	}
}
