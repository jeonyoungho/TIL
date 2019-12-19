package prac0901;

public class Main {

	public static void main(String[] args) {
		ScoreRecord record = new ScoreRecord();
		DataSheetView d = new DataSheetView(record);
		record.addObserver(d);

		MinMaxView m = new MinMaxView(record);
		record.addObserver(m);

		AverageView a = new AverageView(record);
		record.addObserver(a);

		DPScore s1 = new DPScore();
		s1.setName("insang1");
		s1.setScore(80);
		record.addScore(s1);

		DPScore s2 = new DPScore();
		s2.setName("insang2");
		s2.setScore(70);
		record.addScore(s2);

	}

}
