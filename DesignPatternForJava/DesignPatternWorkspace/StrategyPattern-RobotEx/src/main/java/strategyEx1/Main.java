package strategyEx1;

public class Main {
	public static void main(String[] args) {
		Robot atom1 = new Atom("æ∆≈Ë1");
		atom1.setMovingStrategy(new FlyingStrategy());
		atom1.setAttackStrategy(new PunchStrategy());
		atom1.move();
		atom1.attack();
		
		Robot taekwon1 = new TaekwonV("≈¬±«∫Í¿Ã1");
		taekwon1.setMovingStrategy(new WalkingStrategy());
		taekwon1.setAttackStrategy(new MissileStrategy());
		taekwon1.move();
		taekwon1.attack();
		
		taekwon1.setMovingStrategy(new FlyingStrategy());
		taekwon1.setAttackStrategy(new PunchStrategy());
		taekwon1.move();
		taekwon1.attack();
		
		Robot sungard1 = new Sungard("Ω„∞°µÂ1");
		sungard1.setMovingStrategy(new JumpingStrategy());
		sungard1.setAttackStrategy(new PunchStrategy());
		sungard1.move();
		sungard1.attack();
	}
}
