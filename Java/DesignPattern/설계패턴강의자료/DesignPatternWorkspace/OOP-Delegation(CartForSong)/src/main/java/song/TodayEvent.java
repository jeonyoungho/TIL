package song;

public  class TodayEvent extends DiscountedMode {
	  
	  @Override
	  public double getDiscountedPrice(double price) {
		  return price-0.3*price;
	  }
}
