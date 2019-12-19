package junyoungho;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class ShortScrollTest {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		Robot scrollrobot;
		scrollrobot = new Robot();

		scrollrobot.mouseMove(126, 272);

		scrollrobot.mouseWheel(25);
		System.out.println("finish");
	}
		
}
