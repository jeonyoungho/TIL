
public class LGMotor extends Motor {
	
	private String traceId = "[LGMotor]";
	
	public LGMotor(Door door) {
		super(door) ;
	}

	@Override
	protected void moveMotor(Direction direction) {
		System.out.println(traceId + "move " + direction);
	}	

}
