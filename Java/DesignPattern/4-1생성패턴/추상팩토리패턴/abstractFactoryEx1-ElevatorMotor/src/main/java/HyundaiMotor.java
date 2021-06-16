public class HyundaiMotor extends Motor{
	private static Motor hMotor = null;
	public static Motor createMotor() {
		if(hMotor == null)
			hMotor = new LGMotor();
		
		return hMotor;
	}
	
	protected void moveMotor(Direction direction) {
		//Hyundai Motor를 구동시킨다
		if(direction == Direction.UP)
			System.out.println("현대 모터 상승");
		else
			System.out.println("현대 모터 하강");
	}
	
}