import java.util.*;

public class MinMaxView implements Observer {
	private ScoreRecord scoreRecord;

	public MinMaxView(ScoreRecord scoreRecord) {
		this.scoreRecord = scoreRecord;
	}

	public void displayScores() {
		List<DPScore> scores = scoreRecord.getScores();
		int min = 101, max = -1;
		for (int i = 0; i < scores.size(); i++) {
			if (min > scores.get(i).getScore())
				min = scores.get(i).getScore();
			if (max < scores.get(i).getScore())
				max = scores.get(i).getScore();
		}
		System.out.println("min score: " + min);
		System.out.println("max score: " + max);
	}

	public void updateScore() {
		displayScores();
		System.out.println("--------------------");
	}
}
