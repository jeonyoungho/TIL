package prac0901;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void rmObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers() {
		for (Observer o: observers) {
			o.update();
		}
	}
}
