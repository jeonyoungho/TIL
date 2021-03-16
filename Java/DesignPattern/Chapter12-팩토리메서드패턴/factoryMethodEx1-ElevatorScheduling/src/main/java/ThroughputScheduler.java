public class ThroughputScheduler implements ElevatorScheduler{
	private static ElevatorScheduler throughputScheduler;
	
	public static ElevatorScheduler getInstance() {
		if(throughputScheduler == null)
			throughputScheduler = new ThroughputScheduler();
		
		return throughputScheduler;
	}

	public int selectElevator(ElevatorManager manager, int destination, Direction direction) {
		// TODO Auto-generated method stub
		return 0;
	}
}
