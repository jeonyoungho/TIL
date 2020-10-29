
public class Car {
	Boolean on=false;
		
	public void engine() {
		on =! on;
		if(on) {
			System.out.println("Car Engine On...");
		}else {
			System.out.println("Car Engine Off...");
		}
	}
}
