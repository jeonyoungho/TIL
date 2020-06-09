package abstractFatoryExample;

public class HeadFactory {
	public static Head getHead(Brand brand) {
		Head head = null;
		
		switch(brand) {
		case HANSUNG: head = new HansungHead(); break;
		case SAMSUNG: head = new SamsungHead(); break;
		}
		return head;
	}
	
	
}
