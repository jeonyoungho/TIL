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
      // notepad ���α׷� Ȱ��ȭ
      Runtime.getRuntime().exec("notepad");
      Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
      
      Robot robot = new Robot();
      // Hello���� �Է�
      for(int i=0; i<keyInput.length; i++)
      {
        robot.keyPress(keyInput[i]);
        robot.keyRelease(keyInput[i]);
        //���� �ش� �����带 200ms ���� sleep��Ų��.
        robot.delay(200);
      }

      // �޸��忡 �Է��� ���ڸ� ��� �����Ѵ�.
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_A);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_A);

      //���� �ش� �����带 500ms ���� sleep��Ų��.
      robot.delay(500);
      // ���콺 ������ ��ư Ŭ��
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