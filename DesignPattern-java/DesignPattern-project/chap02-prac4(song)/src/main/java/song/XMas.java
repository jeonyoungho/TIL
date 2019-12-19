package song;

public class XMas extends DiscountedMode {

	@Override
	public double getDiscountedPrice(double price) {
		  return price-0.2*price;
	}

}
