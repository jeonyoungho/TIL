
public class ESCDecorator extends CarOptionDecorator {
	private int escPrice;
	
	
	public ESCDecorator(CarComponent decoratedCar, int airBagPrice) {
		super(decoratedCar);
		this.escPrice = airBagPrice;
	}
	public int getPrice() {
		int price = super.getPrice();
		return price + getAirBagPrice();
	}
	public String getCarinfo() {
		String carinfo = super.getCarinfo(); 
		return carinfo + " With ESC ";
	}
	
	private int getAirBagPrice() {
		return escPrice;
	}
}
