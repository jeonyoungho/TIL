import java.util.*;
public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void rmObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public void notifyObservers() {
		for (Observer o:observers) {
			o.updateScore();
		}
	}
}
