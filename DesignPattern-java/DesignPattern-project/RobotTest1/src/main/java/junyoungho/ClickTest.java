package junyoungho;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClickTest {
	public static void main(String[] args) throws AWTException {

		Robot robot = new Robot();
		
		robot.mouseMove(1419, 7);
			
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // ���콺 ���� ��ư Ŭ��.
	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // ���콺 ���� ��ư Ŭ�� ���¸� ���� �����ν� �� ���� Ŭ���� �ϼ��Ѵ�.

	}

//	robot.mouseMove(1419, 7); // ���콺 �����͸� ������� ���� 1005�ȼ�, ���� 10�ȼ��� ��ġ�� �ű��.
//	robot.mouseWheel(10);
}