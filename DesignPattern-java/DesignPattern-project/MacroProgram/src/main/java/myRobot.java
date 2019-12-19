import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class myRobot{ //나중에 싱글턴으로 하나만 반환하게 할것
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
		robot.mouseMove(xPosition, yPosition); // 마우스 포인터를 모니터의 가로 1005픽셀, 세로 10픽셀의 위치로 옮긴다.
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // 마우스 왼쪽 버튼 클릭.
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // 마우스 왼쪽 버튼 클릭 상태를 해제 함으로써 한 번의 클릭을 완성한다.
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
