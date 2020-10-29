import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClockFrame extends JFrame{
	
	private static final long serialVersionUID = 3268575616143849857L;
	
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private TimerThread th1;
	private TimerThread th2;
	
	public ClockFrame() {
		super("타이머를 만듭니다.");
		setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.addKeyListener(new MyKey());
		
		label1.setText(Integer.toString(0));
		label1.setFont(new Font("고딕",Font.ITALIC, 50));
		label1.setBackground(Color.YELLOW);
		label1.setOpaque(true);
		c.add(label1);
		
		label2.setText(Integer.toString(0));
		label2.setFont(new Font("고딕",Font.ITALIC, 50));
		label2.setBackground(Color.GREEN);
		label2.setOpaque(true);
		c.add(label2);
		
		th1 = new TimerThread(label1,1000);
		//th.run(); //이렇게 run을 호출할시 setVisible이 돌지 않고 무한루프 걸려서 빠져나오질 못해 밑에 setVisible이 호출되지 않는다
		th1.start(); // 그래서 start로 스레드를 하나 생성해달라고 운영체제에 요구하여 스레드를 하나 생성하여 스레드 코드를 돌린다
		
		th2 = new TimerThread(label2,100);
		th2.start();
		
		
		
		setVisible(true); // 이벤트 디스패치 스레드 생성 후 메인스레드 종료, 이벤트 디스패치 스레드가 존재하기에 프로그램이 종료 되지 않는다
		
		label1.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				th1.interrupt();
			}
		});
		
		label2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				th2.interrupt();
			}
		});
		
		c.setFocusable(true); //이벤트 디스패치 스레드가 focus를 가진 놈한테 key event를 넘겨주게 됨, 여기선 컨텐트 팬에 넘겨 주게 됨
	}
	
	class MyKey extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			// 자바에서 new로 생성한 객체는 절대로 강제로 제거 할 수 없음
			
			if(c == 's') {
				if(th1.getFlag() == true) {
					th1.stopFlag();
				}else {
					th1.resumFlag();
				}
				
			}else if(c == 'r') {
				if(th2.getFlag() == true) {
					th2.stopFlag();
				}else {
					th2.resumFlag();
				}
			} //이 방법말고 스레드를 cpu를 차지 하지 않게 멈추려면 동기화기법이 필요함
				
		}
	}
	
	
	class TimerThread extends Thread{
		
		private int count = 0;
		private JLabel label = null;
		private int delay = 1000;
		
		private boolean flag = true;
		
		public TimerThread(JLabel label, int delay) {
			this.label = label;
			this.delay = delay;
		}
		
		public void stopFlag() { flag = false; }
		public boolean getFlag() { return flag; }
		public void resumFlag() { flag = true; }
		
		@Override //운영체제가 run함수를 부르는게 아니라 cpu의 프로그램카운터에 올리는 것 뿐
		public void run() { // 스레드 코드
//			Thread.currentThread().setName("Timer Thread");
			while(true) {
//				System.out.println("timer thread");
				try { 
					sleep(delay); // 1초 잠자기
				} catch (InterruptedException e) {
					Container c = label.getParent();
					c.remove(label);
					c.repaint();
					return;
					// 잠자고 있는데 누가 죽이려할때 잠에서 깨어야하기 때문에 처리를 해줘야함
				}
				/*
				if(flag == false)
					continue;
				*/
				
				//스레드가 죽는 것하고 객체가 사라지는 것 하곤 다르다!!!
				if(flag == false) {
					Container c = label.getParent();
					c.remove(label);
					c.repaint();
					return;
				}
				
				count++;
				label.setText(Integer.toString(count));
			}
		}
	}
	
	public static void main(String[] args) { // 메인스레드를 생성하여 메인 함수 호출
		new ClockFrame(); 
		int n;
		n=1;
		
		// 스레드의 구조와 어떻게 돌아가는지 이해하는게 중요!!
	}

}
