package singleCombatGame;

public class GameLog {
	private static StringBuffer sb = new StringBuffer();
	synchronized public static void putLog(String msg) {
		sb.append(msg+"\n");
	}
	
    public static void printLog(){
    	System.out.println(sb.toString());
    }
    
    public static void clear(){
    	sb = null;
    }
}

