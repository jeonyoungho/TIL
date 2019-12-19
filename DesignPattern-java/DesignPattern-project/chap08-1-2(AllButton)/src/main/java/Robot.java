
public class Robot{
	boolean gun = false;
	
	public void fireGun() {
		gun = !gun;
		
		if(gun) {
			System.out.println("Gun Fires....");
		}else {
			System.out.println("Gun fire stops");
		}
	}
	
	public void missile() {
		System.out.println("Missile!!!");
	}
}
