import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseAdapter extends java.awt.event.MouseAdapter {
	// private boolean flag = false;
	//private LogicThread logicThread = new LogicThread();
	private AutoThread autoThread = new AutoThread();
	
	public void mouseClicked(MouseEvent e) {
		/* flag = !flag;
		 System.out.println(flag);

		if (flag) {*/
		JButton btn = (JButton)e.getSource(); 
		if(btn.getText() == "Start") {
		StatePanel.setLabel("Running");
			autoThread.start();

		} else if(btn.getText() == "Finish"){
			StatePanel.setLabel("Stop");
			System.exit(0);
		}

	}
}
