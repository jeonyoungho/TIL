import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	private JTextField tf = new JTextField();

	public MyFrame() {
		super("hello");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		tf.setFont(new Font("고딕", Font.ITALIC, 25));
		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,tf.getText());
			}
			
		});
		
		c.add(tf, BorderLayout.NORTH);
		c.add(new MyPanel(), BorderLayout.CENTER);
		
		setSize(400, 400);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		
		Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green};
		
		public MyPanel() {
			setLayout(new GridLayout(3, 3, 5, 5));
			
			MyAction al = new MyAction();
			
			for (int i = 0; i < 9; i++) {
				JButton button = new JButton(Integer.toString(i));
				button.setFont(new Font("고딕", Font.ITALIC, 25));
				button.addActionListener(al);
				
//				int r = (int)(Math.random()*256); // 0~255사이의 정
//				int g = (int)(Math.random()*256); // 0~255사이의 정
//				int b = (int)(Math.random()*256); // 0~255사이의 정
				
				int index = (int)(Math.random()*colors.length);
				
				button.setBackground(colors[index]);
				button.setOpaque(true);
				button.setBorderPainted(false);
				add(button);
			}
		}
	}


	class MyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand(); //버튼 표면의 문자열을 얻어 올 수 있
			tf.setText(text);
		}

	}

	public static void main1(String[] args) {
//		try {   
//		    UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");   
//		} catch(Exception e) { }  
		
		new MyFrame();
		/*
		 * JFrame이 setVisble이 실행되면서 이벤트 디스패치 스레드가 돌아서 메인스레드가 죽어도 상관이 없다
		 */
	}

}
