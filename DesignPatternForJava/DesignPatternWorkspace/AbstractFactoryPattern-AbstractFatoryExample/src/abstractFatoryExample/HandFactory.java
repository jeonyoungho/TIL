package abstractFatoryExample;

public class HandFactory {
	public static Hand getHand(Brand brand) {
		Hand hand = null;
		
		switch(brand) {
		case HANSUNG: hand = new HansungHand(); break;
		case SAMSUNG: hand = new SamsungHand(); break;
		}
		return hand;
	}
	
	
}
