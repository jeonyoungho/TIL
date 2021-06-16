public class AlarmOnCommand implements Command { // 알람을 울리는 기능의 캡슐화
	private Alarm theAlarm;

	public AlarmOnCommand(Alarm theAlarm) {
		this.theAlarm = theAlarm;
	}

	public void execute() {
		theAlarm.start();
	}
}