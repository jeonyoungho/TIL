package prac0901;

public class Main {

	public static void main(String[] args) {
		Battery battery = new Battery();
		Observer bld = new BatteryLevelDisplay(battery);
		battery.addObserver(bld);

		Observer lbw = new LowBatteryWarning(battery);
		battery.addObserver(lbw);

		battery.consume(20);
		battery.consume(10);
		battery.consume(30);
		battery.consume(15);

	}

}
