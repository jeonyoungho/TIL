
public class SCCDecorator extends CarOptionDecorator {
	private int sccPrice;
	
	
	public SCCDecorator(CarComponent decoratedCar, int airBagPrice) {
		super(decoratedCar);
		this.sccPrice = airBagPrice;
	}
	public int getPrice() {
		int price = super.getPrice();
		return price + getAirBagPrice();
	}
	public String getCarinfo() {
		String carinfo = super.getCarinfo(); 
		return carinfo + " With SCC ";
	}
	
	private int getAirBagPrice() {
		return sccPrice;
	}
}
