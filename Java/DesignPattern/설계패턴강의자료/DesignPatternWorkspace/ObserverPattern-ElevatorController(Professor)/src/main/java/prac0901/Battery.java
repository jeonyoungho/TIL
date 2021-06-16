package prac0901;

public class Battery extends Subject {
	private int level=100;

	public void consume(int amount) {
		level -= amount;
		notifyObservers();
	}

	public int getLevel() {
		return level;
	}
}
