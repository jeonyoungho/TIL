package prac1401a;

public class Monitor extends ComputerDevice{
	private int price;
	private int power;
	
	public Monitor(int price, int power) {
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
