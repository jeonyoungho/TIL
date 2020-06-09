import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DicClient extends JFrame{
	private JTextField eng = new JTextField(10);
	private JTextField kor = new JTextField(10);
	private JButton searchBtn = new JButton("검색");
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	public DicClient() {
		super("사전 클라이언트 입니다.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("영어"));
		c.add(eng);
		c.add(new JLabel("한글"));
		c.add(kor);
		c.add(searchBtn);
		
		eng.setFont(new Font("고딕",Font.PLAIN,8));
		kor.setFont(new Font("고딕",Font.PLAIN,8));
		searchBtn.setFont(new Font("고딕",Font.PLAIN,8));
		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					out.write(eng.getText() + "\n");
					out.flush();
					
					String korText = in.readLine();
					kor.setText(korText);
				} catch (IOException e1) {
					handleError(e1.getMessage());
				}
			}
		});
		
		setSize(400, 400);
		setVisible(true);
		
		setup();
	}
	
	private void setup() {
		try {
			socket = new Socket("localhost",9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
		} catch (IOException e) {
			handleError(e.getMessage());
		}
		
	}
	
	private void handleError(String msg) {
		System.out.print(msg);
		System.exit(1);
	}
	
	public static void main(String[] args) {
		new DicClient();
	}

}
