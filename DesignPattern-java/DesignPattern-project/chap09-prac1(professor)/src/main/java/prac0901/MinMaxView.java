package prac0901;

import java.util.List;

public class MinMaxView implements Observer {
	private ScoreRecord record;

	public MinMaxView(ScoreRecord record) {
		this.record = record;
	}

	public void displayScores() {
		List<DPScore> scores = record.getScore();
		int min=101, max=-1;
		for (int  i=0; i<scores.size(); i++) {
			if (min>scores.get(i).getScore())
				min = scores.get(i).getScore();
			if (max<scores.get(i).getScore())
				max = scores.get(i).getScore();
		}

		System.out.println("Minimum score: "+ min);
		System.out.println("Maximum score: "+ max);


	}

	public void update() {
		displayScores();

	}

}
