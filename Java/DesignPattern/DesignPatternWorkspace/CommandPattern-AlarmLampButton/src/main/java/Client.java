
public class Client {

	public static void main(String[] args) {
		Lamp theLamp = new Lamp();
		Alarm theAlarm = new Alarm();
		Button button1 = new Button(theLamp,theAlarm);
		button1.setMode(Mode.LAMP);
		button1.pressed();
		button1.pressed();
		button1.pressed();
		button1.pressed();
		button1.setMode(Mode.ALARM);
		button1.pressed();
		button1.pressed();
		button1.pressed();
		button1.pressed();
	}

}
