package singleCombatGame;

import java.util.Random;

public class Character extends Thread {
	final private int MAXRANGE = 200;
	private Character enemy;
	private int intelligence;//적을 탐색할때 사용
	private int hitPower;
	private int health;
	
	public Character(String name,int intelligence, int hitPower, int health) {
		this.intelligence = intelligence;
		this.hitPower = hitPower;
		this.health = health;
		this.setName(name);
	}
	
	public static Character getBatman(String name) {
		return new Character(name, 100, 70, 50);
	}
	public static Character getSuperman(String name) {
		return new Character(name, 80, 100, 50);
	}
	

	private int spinRoullett(int n) {
		Random gen = new Random();
		gen.setSeed(System.currentTimeMillis());
		int luckyNum = gen.nextInt(n)+1;
		return luckyNum;
	}
	private boolean  enemyIsFound() {
		GameLog.putLog(this.getName()+" searching enemy ----> ");
		if (getHealth()>0 && enemySpotted()) {
			GameLog.putLog(this.getName()+" finds enemy "+enemy.getName());
			return true;
		}
		return false;
	}

	private boolean enemySpotted(){
		int num = spinRoullett(MAXRANGE); //sipnRoullett 메소드 -> MAXRANGE 보다 작은 난수 하나 발생하는 메소드 , 룰렛돌리는거
		return 1<=num && num<=this.intelligence;
	}
	
	private boolean attackSucceeds(){
		int num = spinRoullett(MAXRANGE);
		return 1<=num && num<=this.hitPower;
	}
	
	public void attack() {
		if (attackSucceeds()) {
			if (strongAttack()) {
				GameLog.putLog(this.getName()+" STRONGLY attacks >>> "+enemy.getName());
				enemy.setHealth(enemy.getHealth()-5);
			}
			else {
				GameLog.putLog(this.getName()+" attacks> "+enemy.getName());
				enemy.setHealth(enemy.getHealth()-1);
			}
			GameLog.putLog(this.getName()+":"+ this.getHealth()+enemy.getName()+":"+enemy.getHealth());
			System.out.println();
		}
		else
			GameLog.putLog(this.getName()+" attack fails ---");
		
	}

	private boolean strongAttack() {
		int num = spinRoullett(10);
		return 1<=num && num<=5;
	}

	@Override
	public void run() {
//		while (true){
//		
//			if (enemyIsFound()) {
//				attack();
//				
//			}
//			if (this.getHealth()<0 || enemy.getHealth()<0) break;
//		}
		
		while (this.getHealth()>0 && enemy.getHealth()>0){
			
			if (enemyIsFound()) {
				attack();
				
			}
		
		}
		
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Character getEnemy() {
		return enemy;
	}

	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}
	

}
