package ElevatorMotor;

public class HyundaiMotor extends Motor{
	private static Motor hMotor = null;
	public static Motor createMotor() {
		if(hMotor == null)
			hMotor = new LGMotor();
		
		return hMotor;
	}
	
	protected void moveMotor(Direction direction) {
		//Hyundai Motor�� ������Ų��
		if(direction == Direction.UP)
			System.out.println("���� ���� ���");
		else
			System.out.println("���� ���� �ϰ�");
	}
	
}