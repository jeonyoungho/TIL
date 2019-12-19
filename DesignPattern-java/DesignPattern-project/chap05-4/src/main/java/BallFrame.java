import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BallFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	private Field field;
	
	public BallFrame(Ball[] balls) {
		super("Balls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		field = new Field(balls);
		Thread th = new Thread(field);
		th.start();
		
		add(field,BorderLayout.CENTER);
		
		setSize(WIDTH,HEIGHT);
		setVisible(true);
	}
}
