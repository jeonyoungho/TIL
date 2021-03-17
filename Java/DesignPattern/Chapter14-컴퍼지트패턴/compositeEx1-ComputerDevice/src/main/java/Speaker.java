public class Speaker extends ComputerDevice{
	private int price;
	private int power;
	
	public Speaker(int price, int power) {
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
