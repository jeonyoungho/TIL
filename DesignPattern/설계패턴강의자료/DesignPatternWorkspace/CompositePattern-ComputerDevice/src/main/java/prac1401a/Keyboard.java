package prac1401a;

public class Keyboard extends ComputerDevice{
	private int price;
	private int power;
	
	public Keyboard(int price, int power) {
		super();
		this.price = price;
		this.power = power;
	}
	
	public int getPrice() {
		return price;
	}

	public int getPower() {
		return power;
	}

	
}
