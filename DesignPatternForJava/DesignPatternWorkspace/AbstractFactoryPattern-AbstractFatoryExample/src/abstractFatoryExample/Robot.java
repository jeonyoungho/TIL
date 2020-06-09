package abstractFatoryExample;

public class Robot { 
	private Brand brand;

	private Head head;
	private Body body;
	private Leg leg;
	private Hand hand;
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public Leg getLeg() {
		return leg;
	}
	public void setLeg(Leg leg) {
		this.leg = leg;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
}
