
public class Client {
	public static void main(String[] args) {
		Battery battery = new Battery();
		
		Observer bld = new BatteryLevelDisplay(battery);
		battery.addObserver(bld);

		Observer lbd = new LowBatteryWarning(battery);
		battery.addObserver(lbd);
		
		battery.consume(20);
		battery.consume(10);
		battery.consume(30);
		battery.consume(15);
		
	}

}
