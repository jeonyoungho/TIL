import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyFrameHomeWork extends JFrame {

	private Color[] colors = { Color.red, Color.green, Color.blue, Color.red, Color.green, Color.blue, Color.red,
			Color.green, Color.blue };
	
	private int a[] = new int[10];
	
	private JButton[] buttons = new JButton[10];
	
	public MyFrameHomeWork() {
		super("MyFrameHomework");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();

		c.add(new MyPanel(), BorderLayout.CENTER);

		setSize(400, 400);
		setVisible(true);

	}

	class MyPanel extends JPanel {

		public MyPanel() {

			setLayout(new GridLayout(3, 3, 5, 5));

			MyAction al = new MyAction();
			
			//버튼 생성
			for (int i = 0; i < 9; i++) {
				buttons[i] = new JButton(Integer.toString(i));
				buttons[i].setFont(new Font("고딕", Font.ITALIC, 25));
				buttons[i].addActionListener(al);
				add(buttons[i]);
			}
			
			generateRandomButton();
		}
	}
	
	// 난수를 발생하여 버튼의 색상 설정 
	public void generateRandomButton() {
		for (int i = 0; i < 9; i++) {
			a[i] = (int) (Math.random() * colors.length);
			for (int j = 0; j < i; j++) {
				if (a[i] == a[j]) {
					i--;
				}
			}
		}
		
		// 투명도 윤곽선 색 활성화
		for (int i = 0; i < 9; i++) {
			buttons[i].setBackground(colors[a[i]]);
			buttons[i].setOpaque(false);
			buttons[i].setBorderPainted(true);
		}
	}

	class MyAction implements ActionListener {
		
		private List<Color> checkList = new ArrayList<>();
		
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			button.setOpaque(true);
			button.setBorderPainted(false);
			
			checkList.add(button.getBackground());
			
			if(checkList.size() == 2 ) { //list value의 개수가 2개 일시 두 컬러가 같은지 비교
				
				if(checkList.get(0) == checkList.get(1)) {
					JOptionPane.showMessageDialog(null,"성공!");
				}else {
					JOptionPane.showMessageDialog(null,"실패!");
					
				}
				checkList.clear();
				generateRandomButton();
			}
		}

	}

	public static void main(String[] args) {
		new MyFrameHomeWork();
	}

}
