
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Event;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AutoThread extends Thread {


	private int shortTermTime = 1000 * 5;
	private int longTermTime = 1000 * 60 * 90;

	private Robot robot;
    private int naverAccessKeyInput[] =
        {
        KeyEvent.VK_W,
        
        KeyEvent.VK_CONVERT,
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
        KeyEvent.VK_ENTER
};
    private int naverAccessKeyInput2[] =
        {
        KeyEvent.VK_W,
        
        KeyEvent.VK_CONVERT,
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
        KeyEvent.VK_ENTER
};
    private int convetToKoreaKeyInput[] =
        {
        KeyEvent.VK_SHIFT,
        KeyEvent.VK_SPACE
};
	private void setStrings() { // Get random number  
		int indexFirst = (int) (Math.random() * 4 + 1);// 1~4중의 난수 발생
		int indexSecond = (int) (Math.random() * 4 + 1);// 1~4중의 난수 발생
	}

	public void run() {

		while (true) {
			
			//크롬접속
			try {
				robot = new Robot();
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
				
			     for(int i=0; i<naverAccessKeyInput.length; i++)
			      {
			        robot.keyPress(naverAccessKeyInput[i]);
			        robot.keyRelease(naverAccessKeyInput[i]);
			        //현재 해당 쓰레드를 200ms 동안 sleep시킨다.
			        robot.delay(300);
			      }
			     
	
			        robot.keyPress(convetToKoreaKeyInput[0]);
			        robot.keyPress(convetToKoreaKeyInput[1]);
			        
			        robot.keyRelease(convetToKoreaKeyInput[0]);
			        robot.keyRelease(convetToKoreaKeyInput[1]);
			        //현재 해당 쓰레드를 200ms 동안 sleep시킨다.
			        
				     for(int i=0; i<naverAccessKeyInput2.length; i++)
				      {
				        robot.keyPress(naverAccessKeyInput2[i]);
				        robot.keyRelease(naverAccessKeyInput2[i]);
				        //현재 해당 쓰레드를 200ms 동안 sleep시킨다.ㅈ
				        robot.delay(300);
				      }
			     
			}catch(AWTException e) {
				e.printStackTrace();
			}
			catch (java.io.IOException ex) {
				ex.printStackTrace();
			}
			
			
			
			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

			

		}

	}

}
