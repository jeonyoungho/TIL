import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();

	// CRUD : create read update delete

	public void addObserver(Observer o) {
		this.observers.add(o);
	}

	public void rmObserver(Observer o) {
		this.observers.remove(o);
	}

	// extract Method��� -> �ߺ��Ǵ� �κ��� �̾Ƽ� �޼ҵ�� ����� �����丮 ���
	public void notifyObservers() {
		for (Observer o : observers) {
			o.updateScore();
		}
	}

}
