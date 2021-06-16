
public class Main {
	public static void main(String[] args) {
		ScoreRecord record = new ScoreRecord();

		DataSheetView dataSheetView = new DataSheetView(record, 2);
		record.addObserver(dataSheetView);

		MinMaxView minMaxView = new MinMaxView(record);
		record.addObserver(minMaxView);

		AverageView averageView = new AverageView(record);
		record.addObserver(averageView);

		DPScore score1 = new DPScore();
		score1.setName("alice");
		score1.setScore(10);
		DPScore score2 = new DPScore();
		score2.setName("bob");
		score2.setScore(20);
		DPScore score3 = new DPScore();
		score3.setName("charlie");
		score3.setScore(30);

		record.addScore(score1);
		record.addScore(score2);
		record.addScore(score3);
		record.rmScore("alice");

	}
}
