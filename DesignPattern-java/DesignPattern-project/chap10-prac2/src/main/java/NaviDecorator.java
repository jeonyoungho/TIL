
public class NaviDecorator extends CarOptionDecorator {
	private int NaviPrice;
	
	
	public NaviDecorator(CarComponent decoratedCar, int airBagPrice) {
		super(decoratedCar);
		this.NaviPrice = airBagPrice;
	}
	public int getPrice() {
		int price = super.getPrice();
		return price + getAirBagPrice();
	}
	public String getCarinfo() {
		String carinfo = super.getCarinfo(); 
		return carinfo + " With Navi ";
	}
	
	private int getAirBagPrice() {
		return NaviPrice;
	}
}
