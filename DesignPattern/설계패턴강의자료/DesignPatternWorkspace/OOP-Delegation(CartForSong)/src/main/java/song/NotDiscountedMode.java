package song;

public class NotDiscountedMode extends DiscountedMode{

	@Override
	public double getDiscountedPrice(double price) {
		return price;
	}

}
