import java.util.ArrayList;
import java.util.List;

public class Box extends ComputerDevice{
	private List<ComputerDevice> devices = new ArrayList<ComputerDevice>();

	public void addDevice(ComputerDevice computerDevice) {
		devices.add(computerDevice);
	}
	
	public void rmDevice(ComputerDevice computerDevice) {
		devices.remove(computerDevice);	
	}
	
	public int getPrice() {
		int totalPrice=0;
		for(ComputerDevice device:devices) {
			totalPrice += device.getPrice();
		}
		return totalPrice;
	}
	
	public int getPower() {
		int totalPower=0;
		for(ComputerDevice device:devices) {
			totalPower += device.getPower();
		}
		return totalPower;
	}
}
