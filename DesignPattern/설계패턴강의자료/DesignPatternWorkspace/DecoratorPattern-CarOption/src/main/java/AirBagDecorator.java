
public class AirBagDecorator extends CarOptionDecorator {
	private int airBagPrice;
	
	
	public AirBagDecorator(CarComponent decoratedCar, int airBagPrice) {
		super(decoratedCar);
		this.airBagPrice = airBagPrice;
	}
	public int getPrice() {
		int price = super.getPrice();
		return price + getAirBagPrice();
	}
	public String getCarinfo() {
		String carinfo = super.getCarinfo(); 
		return carinfo + " With AirBag ";
	}
	
	private int getAirBagPrice() {
		return airBagPrice;
	}
}
