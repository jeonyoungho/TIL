public class Main {

	public static void main(String[] args) {
		Box box = new Box();
		
		Keyboard keyboard = new Keyboard(20000,10);
		
		Monitor monitor = new Monitor(100000,20);
		
		box.addDevice(keyboard);
		box.addDevice(monitor);
		
		System.out.println("total price :" + box.getPrice());
		System.out.println("total power :" + box.getPower());
		
		Speaker speaker = new Speaker(50000,50);
		box.addDevice(speaker);
		System.out.println("total price :" + box.getPrice());
		System.out.println("total power :" + box.getPower());
		
		Box smallBox = new Box();
		Keyboard keyboard1 = new Keyboard(10000,5);
		Keyboard keyboard2 = new Keyboard(10000,5);
		
		smallBox.addDevice(keyboard1);
		smallBox.addDevice(keyboard2);
		
		box.addDevice(smallBox);
		
		System.out.println("total price :" + box.getPrice());
		System.out.println("total power :" + box.getPower());
		
		
	}

}
