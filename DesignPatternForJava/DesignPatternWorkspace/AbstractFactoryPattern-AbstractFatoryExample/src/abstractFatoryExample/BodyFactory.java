package abstractFatoryExample;

public class BodyFactory {
	public static Body getBody(Brand brand) {
		Body body = null;
		
		switch(brand) {
		case HANSUNG: body = new HansungBody(); break;
		case SAMSUNG: body = new SamsungBody(); break;
		}
		return body;
	}
	
	
}
