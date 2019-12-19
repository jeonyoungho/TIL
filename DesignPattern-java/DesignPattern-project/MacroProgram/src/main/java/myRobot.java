import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class myRobot{ //���߿� �̱������� �ϳ��� ��ȯ�ϰ� �Ұ�
	private Robot robot;
	public myRobot() {
		try {
			robot= new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		} 
	}
	
	public void typingText(int[] KeyInput) {
		for (int i = 0; i < KeyInput.length; i++) {
			robot.keyPress(KeyInput[i]);
			robot.delay(300);
			robot.keyRelease(KeyInput[i]);
			robot.delay(300);
			
		}
	} //typing key
	
	public void typingCommand(int[] KeyInput) {
		robot.keyPress(KeyInput[0]);
		robot.keyPress(KeyInput[1]);
		robot.keyRelease(KeyInput[0]);
		robot.keyRelease(KeyInput[1]);
		
	}
	
	
	public void clickMouse(int xPosition, int yPosition) {
		robot.mouseMove(xPosition, yPosition); // ���콺 �����͸� ������� ���� 1005�ȼ�, ���� 10�ȼ��� ��ġ�� �ű��.
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // ���콺 ���� ��ư Ŭ��.
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // ���콺 ���� ��ư Ŭ�� ���¸� ���� �����ν� �� ���� Ŭ���� �ϼ��Ѵ�.
	} //click mouse point method
	
	public void move(int xPosition,int yPosition) {
		robot.mouseMove(xPosition, yPosition);
	}
	
	public void longWheel(int wheel) {
		
		int n= wheel;
		for(int i=0;i<n;i++) {
		robot.mouseWheel(1);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {	e.printStackTrace();}
		}
	}
	
	public void shortwheel(int wheel) {
		robot.mouseWheel(wheel);
	}
	
	
	

}
