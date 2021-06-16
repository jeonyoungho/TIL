package abstractFatoryExample;

public class LegFactory {
	public static Leg getLeg(Brand brand) {
		Leg leg = null;
		
		switch(brand) {
		case HANSUNG: leg = new HansungLeg(); break;
		case SAMSUNG: leg = new SamsungLeg(); break;
		}
		return leg;
	}
	
	
}
