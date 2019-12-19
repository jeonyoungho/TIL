package singleCombatGame;

public class Main {

	public static void main(String[] args) {
//		SingleCombatGame sb = new SingleCombatGame();
		SingleCombatGame sb = CombatFactory.createSingleCombatGame();
		sb.play();
	}

}
