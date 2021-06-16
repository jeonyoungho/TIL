public class LampOnCommand implements Command { // 램프를 켜는 기능의 캡슐화
	private Lamp theLamp;
	public LampOnCommand(Lamp theLamp) {
		this.theLamp = theLamp ;
	}
	public void execute() { theLamp.turnOn() ; }
}