
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AutoThread extends Thread {

	private myRobot robot;

	private int shortTermTime = 1000+500;
	private int longTermTime = 1000 * 7;
	
	private int naverKeyInput[] = {
			KeyEvent.VK_W, 
			KeyEvent.VK_W, 
			KeyEvent.VK_W, 
			KeyEvent.VK_PERIOD,
			
			KeyEvent.VK_N,
			KeyEvent.VK_A,
			KeyEvent.VK_V,
			KeyEvent.VK_E,
			KeyEvent.VK_R,
			KeyEvent.VK_PERIOD,
			KeyEvent.VK_C,
			KeyEvent.VK_O, 
			KeyEvent.VK_M, 
			KeyEvent.VK_ENTER };//typing 'www.naver.com' Enter

	private int convetToKoreaKeyInput[] = { KeyEvent.VK_SHIFT, KeyEvent.VK_SPACE };

	private int yeoncheonDogPensionKeyInput[]= {
			KeyEvent.VK_D,
			KeyEvent.VK_U,
			KeyEvent.VK_S,
			KeyEvent.VK_C,
			KeyEvent.VK_J,
			KeyEvent.VK_S,
			KeyEvent.VK_SPACE,
			KeyEvent.VK_D,
			KeyEvent.VK_O,
			KeyEvent.VK_R,
			KeyEvent.VK_U,
			KeyEvent.VK_S,
			KeyEvent.VK_V,
			KeyEvent.VK_P,
			KeyEvent.VK_S,
			KeyEvent.VK_T,
			KeyEvent.VK_U,
			KeyEvent.VK_S,
			KeyEvent.VK_ENTER,
	};
	
	private int selectAllKeyInput[] = { KeyEvent.VK_CONTROL, KeyEvent.VK_A};
	
	private int deleteKeyInput[] = {KeyEvent.VK_DELETE};
	
	private int withdogKeyInput[] = {
			KeyEvent.VK_D,
			KeyEvent.VK_N,
			KeyEvent.VK_L,
			KeyEvent.VK_E,
			KeyEvent.VK_M,
			KeyEvent.VK_E,
			KeyEvent.VK_H,
			KeyEvent.VK_R,
			KeyEvent.VK_V,
			KeyEvent.VK_P,
			KeyEvent.VK_S,
			KeyEvent.VK_T,
			KeyEvent.VK_U,
			KeyEvent.VK_S,
			KeyEvent.VK_ENTER
	};
	
	public AutoThread() { //Crate myRobot instance
			robot = new myRobot();
	}

	private int  makeRandom(int from,int to) { // Get random number , between from and to
		int count = to - from + 1;
		return (int) (Math.random() * count + from);// 1~4중의 난수 발생
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {

		while (true) {

			// 크롬접속
			try {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

				sleep(shortTermTime);
				
				robot.clickMouse(118,51); //start browser and click to home button(connect naver)
				 
				sleep(longTermTime);
				
				robot.typingText(yeoncheonDogPensionKeyInput); //search yeoncheonDogPension
				
				sleep(shortTermTime);
				
				robot.move(126, 272); //move scroll to scroll
				
				sleep(shortTermTime);
				
				robot.shortwheel(15);//scroll to blog page
				
				sleep(shortTermTime);
				
				robot.clickMouse(317, 517);//blog1 connect
				
				sleep(longTermTime);
				
				robot.longWheel(900);//scroll bottom
				
				sleep(shortTermTime);
				
				robot.clickMouse(320,285); //click to other post in first blog1
				
				sleep(longTermTime);
				
				robot.longWheel(100);
				
				sleep(longTermTime);
				
				robot.clickMouse(470, 15); //second tab close
				
				sleep(longTermTime);
				
				robot.move(170, 310); //move scroll to scroll
				
				sleep(shortTermTime);
				
				robot.shortwheel(-15);//scroll to blog page
				
				sleep(shortTermTime);
				
				robot.clickMouse(590,160);//move cursor to search tab
				
				sleep(shortTermTime);
				
				robot.typingCommand(selectAllKeyInput); // Ctrl + A , selectAll
				
				sleep(shortTermTime);
				
				robot.typingText(deleteKeyInput);//typing deletekey 
				
				sleep(shortTermTime);
				
				robot.typingText(withdogKeyInput); //search withdogpension
				
				sleep(shortTermTime);
				
				robot.clickMouse(270,713);//move cursor to search tab
				
				sleep(longTermTime);
				
				robot.shortwheel(25); // scroll 
				
				sleep(shortTermTime);
				
				robot.clickMouse(1419,7); //close the chrome
				
				sleep(longTermTime);
				
			}catch (java.io.IOException ex) {
				ex.printStackTrace();
			}
			sleep(shortTermTime);
			
			

		}

	}


}
