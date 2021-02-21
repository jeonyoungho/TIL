package ElevatorMotor;

public class LGMotor extends Motor{
	private static Motor lMotor = null;
	public static Motor createMotor() {
		if(lMotor == null)
			lMotor = new LGMotor();
		
		return lMotor;
	}
	
	protected void moveMotor(Direction direction) {
		//Lg Motor�� ������Ų��
		if(direction == Direction.UP)
			System.out.println("LG ���� ���");
		else
			System.out.println("LG ���� �ϰ�");
	}
	
}