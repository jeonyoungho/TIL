
public class PayLogic1 implements PayCalculator {

	public int getPay(int workHours, int overtimeHours) {
		return 10*workHours + 15 * overtimeHours;
	}

}
