public class Button {
	private Command theCommand;

	public Button(Command theCommand) {
		setCommand(theCommand);
	}

	public void setCommand(Command newCommand) {
		this.theCommand = newCommand;
	}

	// 버튼이 눌리면 주어진 Command의 execute 메서드를 호출함
	public void pressed() {
		theCommand.execute();
	}
}
