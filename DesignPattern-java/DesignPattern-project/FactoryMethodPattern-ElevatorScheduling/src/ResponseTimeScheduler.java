
public class ResponseTimeScheduler implements ElevatorScheduler{

	private static ElevatorScheduler scheduler;
	private ResponseTimeScheduler() {}
	
	public static ElevatorScheduler getInstance() {
		if(scheduler == null)
			scheduler = new ResponseTimeScheduler();
		
		return scheduler;
	}
	
	@Override
	public int selectElevator(ElevatorManager manager, int destination, Direction direction) {
		// TODO Auto-generated method stub
		return 0;
	}

}
