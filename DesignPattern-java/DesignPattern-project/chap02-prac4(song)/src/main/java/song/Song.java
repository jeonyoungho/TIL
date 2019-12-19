package song;

public class Song{
	private String title;
	private double price;
	private DiscountedMode discountedMode;

	
	public DiscountedMode getDiscountedMode() {
		return discountedMode;
	}

	public void setDiscountedMode(DiscountedMode discountedMode) {
		this.discountedMode = discountedMode;
	}

	public Song(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

}
