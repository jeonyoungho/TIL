
public class BasicCar extends CarComponent {

	int price = 0;
	
	public BasicCar(int price) {
		this.price = price;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public String getCarinfo() {
		// TODO Auto-generated method stub
		return "This is Basic Car";
	}

}
