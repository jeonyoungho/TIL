
//출력 내용 시험
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
      score1.setName("insang1");
      score1.setScore(10);
      DPScore score2 = new DPScore();
      score2.setName("insang2");
      score2.setScore(20);
      DPScore score3 = new DPScore();
      score3.setName("insang3");
      score3.setScore(30);
      DPScore score4 = new DPScore();
      score4.setName("insang4");
      score4.setScore(40);
      DPScore score5 = new DPScore();
      score5.setName("insang5");
      score5.setScore(50);
      
      record.addScore(score1);
      record.addScore(score2);
      record.addScore(score3);
      record.addScore(score4);
      record.addScore(score5);
      
      DPScore score6 = new DPScore();
      score6.setName("insang6");
      score6.setScore(60);

      record.addScore(score6);
      
      record.rmScore("insang6");
   
   }

}