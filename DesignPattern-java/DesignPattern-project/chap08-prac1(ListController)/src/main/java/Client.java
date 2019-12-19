
public class Client {

	public static void main(String[] args) {
		TV tv = new TV();
		
		MultiButtonController rc = new MultiButtonController();
		PowerOnOffCommand poComm = new PowerOnOffCommand();
		poComm.setTv(tv);
		rc.addCommand(poComm);
		
		muteOnOffCommand moComm = new muteOnOffCommand();
		moComm.setTV(tv);
		rc.addCommand(moComm);
		
		rc.pressed(0);
		rc.pressed(1);
		
		rc.pressed(1);
		rc.pressed(1);
		
		rc.pressed(0);
		rc.pressed(0);
	}

}
