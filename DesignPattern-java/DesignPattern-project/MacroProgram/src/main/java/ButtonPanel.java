import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	private JButton StartBtn = new JButton();
	private JButton StopBtn = new JButton();
	private JButton FinishBtn = new JButton();
	private MouseAdapter adapter;
	public ButtonPanel() {
		
		setLayout(new FlowLayout());
		
		adapter = new MouseAdapter();
		
		StartBtn = new JButton("Start");
		StartBtn.addMouseListener(adapter);
		
		FinishBtn = new JButton("Finish");
		FinishBtn.addMouseListener(adapter);
		
		add(StartBtn);
		add(FinishBtn);
	}
}
