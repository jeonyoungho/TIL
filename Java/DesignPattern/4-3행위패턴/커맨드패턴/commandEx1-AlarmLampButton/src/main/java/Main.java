
public class Main {
	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Command lampOnCommand = new LampOnCommand(lamp);

		Button button1 = new Button(lampOnCommand); // 램프를 켜는 기능을 설정함
		button1.pressed();

		Alarm alarm = new Alarm();
		Command alarmOnCommand = new AlarmOnCommand(alarm); // 알람을 울리는 기능을 설정함
		Button button2 = new Button(alarmOnCommand);
		button2.pressed();

		button2.setCommand(lampOnCommand); // 알람을 울리는 기능을 설정함
		button2.pressed();
	}
}
