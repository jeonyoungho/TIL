
public class Lamp {
	Boolean on = false;
	
	public void power() {
		on = !on;
		if(on){
			System.out.println("Lamp On");
		}
		else {
			System.out.println("Lamp Off");
		}
	}
	
}
