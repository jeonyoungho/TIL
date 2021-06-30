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

	// extract Method방법 -> 중복되는 부분을 뽑아서 메소드로 만드는 리팩토리 방법
	public void notifyObservers() {
		for (Observer o : observers) {
			o.updateScore();
		}
	}

}
