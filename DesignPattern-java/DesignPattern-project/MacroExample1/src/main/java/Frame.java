import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	
	public Frame() {
		super("Macro");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		ButtonPanel buttonPanel = new ButtonPanel();
		add(buttonPanel,BorderLayout.NORTH);
		
		StatePanel statePanel = new StatePanel();
		add(statePanel,BorderLayout.CENTER);
		
		setSize(WIDTH,HEIGHT);
		setVisible(true);
	}
}
