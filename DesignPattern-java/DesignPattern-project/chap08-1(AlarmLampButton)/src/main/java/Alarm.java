
public class Alarm {
	boolean start = false;
	public void doStart() {
		start = !start;
		if(start)
			System.out.println("Alarm starts");
		else
			System.out.println("Alarm finished");
	}
}
