package singleCombatGame;

public class SingleCombatGame {
	public void play() {
		/*Character player1 = new Character("insang1", 100, 70, 50);
		Character player2 = new Character("insang2", 80, 100, 50);*/
		
		
		Character player1 = Character.getBatman("insang1");
		Character player2 = Character.getSuperman("insang2");
		
		player1.setEnemy(player2);
		player2.setEnemy(player1);
		
		player2.start();
		player1.start();
		
		try {
			player1.join();
			player2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameLog.printLog();
		
		
		System.out.println("Batman health"+player1.getHealth());
		System.out.println("superman health"+player2.getHealth());
		
		if (player1.getHealth()>0) {
			System.out.println("Batman wins");
		}
		else System.out.println("Superman wins");
		

	}

}
