  package song;
  
  public class OnSale extends DiscountedMode {
	  
	  @Override
	  public double getDiscountedPrice(double price) {
		  return price-0.1*price;
	  }

  
  }
 