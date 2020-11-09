import java.util.ArrayList;
import java.util.List;

public abstract class ElevatorManager {
	private List<ElevatorController> controllers;
	
	//�־��� ����ŭ�� ElevatorController�� ������
	public ElevatorManager(int controllerCount) {
		controllers = new ArrayList<ElevatorController>(controllerCount);
		
		for(int i=0;i<controllerCount;i++) {
			ElevatorController controller = new ElevatorController(i+1);
			controllers.add(controller);
		}
	}
	
	protected abstract ElevatorScheduler getScheduler();//primitive �Ǵ� hook �޼���

	void requestElevator(int destination, Direction direction) { //���ø� �޼ҵ�
		//���� Ŭ�������� �������̵�� getScheduler�� ȣ����
		ElevatorScheduler scheduler = getScheduler();
		System.out.println(scheduler);
		int selectedElevator = scheduler.selectElevator(this,destination,direction);
		//���õ� ���������͸� �̵���Ŵ
		controllers.get(selectedElevator).gotoFloor(destination);
	}
}