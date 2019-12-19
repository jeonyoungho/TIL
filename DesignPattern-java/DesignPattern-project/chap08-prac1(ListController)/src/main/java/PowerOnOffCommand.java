
public class PowerOnOffCommand implements Command {
	private TV tv;
	public void setTv(TV tv) {
		this.tv = tv;
	}
	public void execute() {
		tv.power();
	}

}
