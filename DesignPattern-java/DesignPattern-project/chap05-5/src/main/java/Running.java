import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Running extends JFrame{
	private Field field;
	
	public Running() {
		super("Running");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		field = new Field();
		
		add(field,BorderLayout.CENTER);
		
		setSize(500,500);
		setVisible(true);
		
		Thread th = new Thread(field);
		th.start();
		
		
	}
}
