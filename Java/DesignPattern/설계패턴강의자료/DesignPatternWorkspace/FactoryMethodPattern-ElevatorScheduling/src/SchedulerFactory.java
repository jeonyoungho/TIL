import java.util.Calendar;

public class SchedulerFactory {
	public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID) {
		ElevatorScheduler scheduler = null;
		switch(strategyID) {
		case RESPONS_TIME: //��� �ð� �ּ�ȭ ����
			scheduler = ResponseTimeScheduler.getInstance();
			break;
		case THROUGHPUT: //ó���� �ִ�ȭ ����
			scheduler = ThroughputScheduler.getInstance();
			break;
		case DYNAMIC: //��� �ð� �ּ�ȭ ����
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hour < 12)//����
				scheduler = ResponseTimeScheduler.getInstance();
			else
				scheduler = ThroughputScheduler.getInstance();
			break;

		}
		
		return scheduler;
	}
}
