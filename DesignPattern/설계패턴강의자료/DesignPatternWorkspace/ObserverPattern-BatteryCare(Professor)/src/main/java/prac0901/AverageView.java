package prac0901;

import java.util.List;


public class AverageView implements Observer {

	private ScoreRecord record;
	public AverageView(ScoreRecord scoreRecord) {
		this.record = scoreRecord;
	}
	public void displayScores() {
		List<DPScore> scores = record.getScore();
		int tot=0;
		for (int  i=0; i<scores.size(); i++) {
			tot += scores.get(i).getScore();
		}

		System.out.println("Average score: "+ tot/scores.size());

	}
	public void update() {
		displayScores();
	}
}
