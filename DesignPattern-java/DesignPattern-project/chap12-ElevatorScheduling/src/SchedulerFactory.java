import java.util.Calendar;

public class SchedulerFactory {
	public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID) {
		ElevatorScheduler scheduler = null;
		switch(strategyID) {
		case RESPONS_TIME: //대기 시간 최소화 전략
			scheduler = ResponseTimeScheduler.getInstance();
			break;
		case THROUGHPUT: //처리량 최대화 전략
			scheduler = ThroughputScheduler.getInstance();
			break;
		case DYNAMIC: //대기 시간 최소화 전략
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hour < 12)//오전
				scheduler = ResponseTimeScheduler.getInstance();
			else
				scheduler = ThroughputScheduler.getInstance();
			break;

		}
		
		return scheduler;
	}
}
