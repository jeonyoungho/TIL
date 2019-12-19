package junyoungho;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MouseClickExample {
	private Robot robot;

	MouseClickExample() throws AWTException {
		robot = new Robot();
		robot.setAutoDelay(1);
	}

	public void mouseLeftClick() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public void mouseRightClick() {
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}

//	public static void main(String[] args) throws IOException, AWTException {
//		MouseClick c = new MouseClick();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		while (true) {
//			String s = br.readLine();
//			if (s.equals("l")) {
//				c.mouseLeftClick();
//			} else if (s.equals("r")) {
//				c.mouseRightClick();
//			}
//		}
//	}
}
