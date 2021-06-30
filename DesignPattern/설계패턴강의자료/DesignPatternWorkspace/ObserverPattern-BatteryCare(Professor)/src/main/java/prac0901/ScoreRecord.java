package prac0901;

import java.util.ArrayList;
import java.util.List;



public class ScoreRecord extends Subject {
	private List<DPScore> record = new ArrayList<DPScore>();


	public void addScore(DPScore score) {
		record.add(score);

		notifyObservers();
	}


	public void rmScore(String name) {
		for (DPScore score:record) {
			if (score.getName().equals(name)) {
				record.remove(score);
				notifyObservers();
				break;
			}
		}

	}

	public List<DPScore> getScore() {
		return record;
	}


}
