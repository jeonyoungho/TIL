public class Battery extends Subject{
	private int level = 100;
	
	public void consume(int amout) {
		level -= amout;
		notifyObservers();
	}
	
	public int getLevel() {
		return level;
	}
	
}
