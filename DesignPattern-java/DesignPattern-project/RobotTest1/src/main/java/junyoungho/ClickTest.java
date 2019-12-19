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
			
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // 마우스 왼쪽 버튼 클릭.
	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // 마우스 왼쪽 버튼 클릭 상태를 해제 함으로써 한 번의 클릭을 완성한다.

	}

//	robot.mouseMove(1419, 7); // 마우스 포인터를 모니터의 가로 1005픽셀, 세로 10픽셀의 위치로 옮긴다.
//	robot.mouseWheel(10);
}