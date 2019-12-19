
public class muteOnOffCommand implements Command {
	private TV tv;
	
	public void setTV(TV tv) {
		this.tv = tv;
	}

	public void execute() {
		tv.mute();
	}

}
