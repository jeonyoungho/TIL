public class LGMotor extends Motor{
	private static Motor lMotor = null;
	public static Motor createMotor() {
		if(lMotor == null)
			lMotor = new LGMotor();
		
		return lMotor;
	}
	
	protected void moveMotor(Direction direction) {
		//Lg Motor를 구동시킨다
		if(direction == Direction.UP)
			System.out.println("LG 모터 상승");
		else
			System.out.println("LG 모터 하강");
	}
	
}
