import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	public ElevatorController controller;
	public ElevatorFrame(JPanel p,ElevatorController c) {
		
		super("Elevator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.controller = c;
		Thread t = new Thread((Runnable)p);
		t.start();
		add(p,BorderLayout.CENTER);
		
		JLabel la = new JLabel("3 floor");
		add(la,BorderLayout.NORTH);
		la.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.gotoFloor(3);
			}
		});
		
		
		
		
		
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		
		
	}
	
	
}
