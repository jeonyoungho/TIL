
public class Client {

	public static void main(String[] args) {
		TV tv = new TV();
		
		TwoButtonController rc = new TwoButtonController();
		PowerOnOffCommand poComm = new PowerOnOffCommand();
		poComm.setTv(tv);
		rc.setCommand(poComm);
		
		rc.buttonPressed();
		
		rc.buttonPressed();
	}

}
