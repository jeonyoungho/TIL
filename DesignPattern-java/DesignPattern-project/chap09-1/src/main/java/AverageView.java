
import java.util.*;

public class AverageView implements Observer{
   private ScoreRecord scoreRecord;
   
   public AverageView(ScoreRecord scoreRecord) {
      this.scoreRecord = scoreRecord;
   }
   
   public void displayScores() {
      List<DPScore> scores = scoreRecord.getScores();
      int tot=0;
      for(int i=0; i<scores.size(); i++) {
         tot += scores.get(i).getScore();
      }
      System.out.println("Average score: " + tot/scores.size());
   }
   
   public void updateScore() {
      displayScores();
      System.out.println("---------------");
   }
}   