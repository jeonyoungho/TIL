
public class Button {
	private Lamp theLamp;
	private Alarm theAlarm;
	Mode mode;
	
	public Button(Lamp theLamp,Alarm theAlarm) {
		this.theLamp = theLamp;
		this.theAlarm = theAlarm;
	}
	
	
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void pressed() {
		if(mode == Mode.ALARM)
			theAlarm.doStart();
		else
			theLamp.power();
	}
}
