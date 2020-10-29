import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class BiClientEx extends JFrame implements ActionListener{
	
	private JTextField sender = null;
	private Receiver receiver = null;
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;

	public BiClientEx() {
		super("클라이언트");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		sender = new JTextField();
		sender.addActionListener(this);
		sender.setFont(new Font("Gothic", Font.PLAIN, 25));
		c.add(sender, BorderLayout.SOUTH);

		receiver = new Receiver();
		receiver.setFont(new Font("Gothic", Font.PLAIN, 25));
		c.add(new JScrollPane(receiver), BorderLayout.CENTER);
		// 스크롤을 표시하기 위해 JScrollPane의 인자로 넘겨줘서 출력창 생성

		setSize(400, 400);
		setVisible(true);

		setup();

		Thread th = new Thread(receiver); // run메소드를 가지고 있는 객체를 파라미터로 넘겨줘서 스레드로 만들면 이 인자를 운영체제에게 신고해준다
		th.start();
	}
	
	private void setup() {
		try {
			socket = new Socket("localhost",9999); //서버로 접속!
			receiver.append("서버에 연결 완료");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			handleError(e.getMessage());
		}

	}

	
	public void actionPerformed(ActionEvent e) {
		String text = sender.getText();
		try {
			out.write(text + "\n");
			out.flush();
			receiver.append("\n클라이언트: " + text);
		} catch (IOException e1) {
			handleError(e1.getMessage());
		}

	}
	
	class Receiver extends JTextArea implements Runnable {

		@Override
		public void run() {

			while (true) {
				try {
					String msg = in.readLine();
					this.append("\n서버: " + msg);
				} catch (IOException e) {
					handleError(e.getMessage());
				}
			}

		}

	}
	
	private void handleError(String string) {
		System.out.println(string);
		System.exit(0); // 열어 놨던 것을 포트까지 다 close시켜주는 작업도 수행
	}
	
	public static void main(String[] args) {
		new BiClientEx();

	}

}
