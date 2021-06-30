import java.util.ArrayList;
import java.util.List;

public abstract class ElevatorManager {
	private List<ElevatorController> controllers;

	// 주어진 수만큼의 ElevatorController를 생성함
	public ElevatorManager(int controllerCount) {
		controllers = new ArrayList<ElevatorController>(controllerCount);

		for (int i = 0; i < controllerCount; i++) {
			ElevatorController controller = new ElevatorController(i + 1);
			controllers.add(controller);
		}
	}

	protected abstract ElevatorScheduler getScheduler();// primitive 또는 hook 메서드

	void requestElevator(int destination, Direction direction) { // 템플릿 메소드
		// 하위 클래스에서 오버라이드된 getScheduler를 호출함
		ElevatorScheduler scheduler = getScheduler();
		System.out.println(scheduler);
		int selectedElevator = scheduler.selectElevator(this, destination, direction);
		// 선택된 엘리베이터를 이동시킴
		controllers.get(selectedElevator).gotoFloor(destination);
	}
}
