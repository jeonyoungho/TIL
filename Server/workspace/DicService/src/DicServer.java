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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DicServer extends JFrame{
	private JTextField eng = new JTextField(10);
	private JTextField kor = new JTextField(10);
	private JButton saveBtn = new JButton("저장");
	private JButton searchBtn = new JButton("검색");
	private JTextArea ta = new JTextArea(7,40);
	private HashMap<String, String> dic = new HashMap<String, String>();
	
	public DicServer() {
		super("사전 서버 입니다.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("영어"));
		c.add(eng);
		c.add(new JLabel("한글"));
		c.add(kor);
		c.add(saveBtn);
		c.add(searchBtn);
		
		
		eng.setFont(new Font("고딕",Font.PLAIN,8));
		kor.setFont(new Font("고딕",Font.PLAIN,8));
		saveBtn.setFont(new Font("고딕",Font.PLAIN,8));
		searchBtn.setFont(new Font("고딕",Font.PLAIN,8));
		ta.setFont(new Font("고딕",Font.PLAIN,8));
		
		c.add(new JScrollPane(ta));
		
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dic.put(eng.getText(), kor.getText());
				
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = dic.get(eng.getText());
				if(s == null)
					s = "없음";
				kor.setText(s);
			}
		});
		
		setSize(400, 400);
		setVisible(true);
		
		startService();
	}
	
	private void handleError(String msg) {
		System.out.print(msg);
		System.exit(1);
	}
	
	private void startService() {
		ServerThread th = new ServerThread();
		th.start();
	}
	
	class ServerThread extends Thread{
		
		@Override
		public void run() {
			ServerSocket listener = null;
			Socket socket = null;
			
			try {
				listener = new ServerSocket(9999);
				ta.append("서버 대기중,,,,\n");
				while(true) {
					socket = listener.accept(); // 사용자의 접속이 올때까지 밑에라인으로 못내려가고 기다리고 있음, blocking call
					ta.append("클라이언트 접속됨\n");
					ServiceThread th = new ServiceThread(socket); //이 소켓을 가지고 클라와 대화를 하라고 서비스스레드에 넘겨줌
					th.start(); // 클라의 접속을 기다렸다가 접속이 되면 서비스스레드에 넘겨주는 걸 무한 루프로 반복
					
				}
				
				
				
			} catch (IOException e) {
				handleError(e.getMessage());
			}
			
		}
	}
	
	class ServiceThread extends Thread{
		
		private Socket socket = null;
		
		
		public ServiceThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while(true) {
					String engText = in.readLine(); // 클라로 부터 한 라인 전송 받기
					String korText = dic.get(engText);
					
					if(korText == null)
						korText = "없음";
					
					out.write(korText + "\n");
					out.flush();
					ta.append(engText + ":" + korText + "\n");
				}
				
			} catch (IOException e) {
				// handleError(e.getMessage());
				ta.append("클라이언트 하나 종료함");
				return; // 현재 스레드 하나만 종료
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		new DicServer();
	}

}
