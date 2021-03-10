import java.util.*;
public class ScoreRecord extends Subject {
	private List<DPScore> scores = new ArrayList<DPScore>();
	
	public void addScore(DPScore newScore) {
		this.scores.add(newScore);
		notifyObservers();
	}
	
	public void rmScore(String name) {
		for(DPScore score:scores) {
			if(score.getName().equals(name)) {
				scores.remove(score);
				break;
			}
		}
		
		notifyObservers();
	}
	
	public List<DPScore> getScores() {
		return this.scores;
	}
}
