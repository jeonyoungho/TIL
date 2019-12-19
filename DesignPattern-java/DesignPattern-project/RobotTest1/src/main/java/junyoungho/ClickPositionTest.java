package junyoungho;

import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class ClickPositionTest {
	public ClickPositionTest() {
		for (int i = 0; i < 10; i++) {
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			System.out.println("Mouse Position: " + pointerInfo.getLocation());
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		new ClickPositionTest();
	}
}
