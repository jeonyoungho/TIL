package junyoungho;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class LongScrollTest {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		Robot scrollrobot;
		scrollrobot = new Robot();

		scrollrobot.mouseMove(126, 272);

		int n=14;
		
		for(int i=0;i<n;i++) {
		scrollrobot.mouseWheel(1);
		}
		System.out.println("finish");

	}
		
}
