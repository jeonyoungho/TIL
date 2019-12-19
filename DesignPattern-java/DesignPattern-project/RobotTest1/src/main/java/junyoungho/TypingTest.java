package junyoungho;
import java.awt.*;
import java.awt.event.*;

public class TypingTest
{
  public TypingTest()
  {
    int keyInput[] =
                     {
                     KeyEvent.VK_H,
                     KeyEvent.VK_E,
                     KeyEvent.VK_L,
                     KeyEvent.VK_L,
                     KeyEvent.VK_O
    };

    try
    {
      // notepad 프로그램 활성화
      Runtime.getRuntime().exec("notepad");
      Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
      
      Robot robot = new Robot();
      // Hello글자 입력
      for(int i=0; i<keyInput.length; i++)
      {
        robot.keyPress(keyInput[i]);
        robot.keyRelease(keyInput[i]);
        //현재 해당 쓰레드를 200ms 동안 sleep시킨다.
        robot.delay(200);
      }

      // 메모장에 입력한 글자를 모두 선택한다.
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_A);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_A);

      //현재 해당 쓰레드를 500ms 동안 sleep시킨다.
      robot.delay(500);
      // 마우스 오른쪽 버튼 클릭
      robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
      robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

    }
    catch (AWTException ae)
    {
      ae.printStackTrace();
    }
    catch (java.io.IOException ex)
    {
      ex.printStackTrace();
    }

  }

//  public static void main(String[] args)
//  {
//    new RobotTestII();
//    System.exit(0);
//  }

}