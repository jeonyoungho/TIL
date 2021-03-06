import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ElevatorPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private int fdLeftX;
	private int fdLeftY;
	private int fdLeftWidth;

	private int fdRightWidth;
	private int fdRightX;
	private int fdRightY;

	private int edLeftX;
	private int edLeftY;
	private int edLeftWidth;
	private int edRightWidth;
	private int edRightX;
	private int edRightY;

	private String fdText = "1";
	private String edText = "1";
	private String crText = "1";
	private String vnText = "1";

	public ElevatorPanel() {

		setLayout(null);

		fdLeftX = 150;
		fdLeftY = 100;
		fdLeftWidth = 0;
		fdRightX = 350;
		fdRightY = 100;
		fdRightWidth = 0;

		edLeftX = 650;
		edLeftY = 100;
		edLeftWidth = 0;
		edRightX = 850;
		edRightY = 100;
		edRightWidth = 0;
		
	}

	public void setFdText(String fdText) {
		this.fdText = fdText;
	}

	public void setEdText(String edText) {
		this.edText = edText;
	}
	
	public void setCrText(String crText) {
		this.crText = crText;
	}
	public void setVnText(String vnText) {
		this.vnText = vnText;
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, ElevatorFrame.WIDTH, ElevatorFrame.HEIGHT);

		g.setColor(Color.yellow);
		g.fillRect(fdLeftX, fdLeftY, fdLeftWidth, 200);
		g.fillRect(fdRightX, fdRightY, fdRightWidth, 200);

		g.setColor(Color.black);
		g.drawRect(150, 100, 200, 200);
		g.drawLine(50, 300, 450, 300);
		g.drawRect(210, 60, 80, 30);
		g.drawString("FloorDisplay", 50, 50);
		g.drawString(fdText, 245, 80); // 층 수 표시
		g.drawRect(380, 230, 30, 50); // 버튼 큰 박스
		int x1[] = { 390, 400, 395 };
		int y1[] = { 250, 250, 240 };
		g.drawPolygon(x1, y1, x1.length);

		int x2[] = { 390, 400, 395 };
		int y2[] = { 260, 260, 270 };
		g.drawPolygon(x2, y2, x2.length);

		// floor display

		g.setColor(Color.yellow);

		g.fillRect(edLeftX, edLeftY, edLeftWidth, 200);
		g.fillRect(edRightX, edRightY, edRightWidth, 200);

		g.setColor(Color.black);
		g.drawRect(710, 60, 80, 30);
		g.drawRect(650, 100, 200, 200);
		g.drawLine(650, 300, 850, 300);

		g.drawString("ElevatorDisplay", 550, 50);
		g.drawString(edText, 745, 80);

		for (int i = 1; i < 10; i++) {
			g.drawString(Integer.toString(i), 875, 300 - (i*20));
			g.drawRect(870, 290 - (i*20), 15, 15);
		}
		// elevator display

		g.drawString("ControllRoom", 200,500);
		g.drawString("first elevator", 205,530);
		
		g.drawRect(190, 550, 100, 30);
		g.drawString(crText + " floor",225, 570);
		// controllroom
		
		g.drawString("VoiceNotice",700,500);
		g.drawString(vnText,700, 530);
		//voicenotice
	}

	public void close() {
		for (int i = 0; i < 10; i++) {
			
			fdLeftWidth += 10;
			fdRightWidth += 10;
			fdRightX -= 10;
			
			edLeftWidth += 10;
			edRightWidth += 10;
			edRightX -= 10;
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}

	}

	public void open() {

		for (int i = 0; i < 10; i++) {

			fdLeftWidth -= 10;
			fdRightWidth -= 10;
			fdRightX += 10;
			
			edLeftWidth -= 10;
			edRightWidth -= 10;
			edRightX += 10;
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}

	}

	public void run() {
		
		while (true) {
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

		}

	}

}
