public class DiagonalMoveStrategy extends DirectionStrategy{
	public void move(Ball ball) {
		ball.setInterval(Ball.INTERVAL,Ball.INTERVAL); //���� �ʱ� �̵����� ����
		while(true) { // ���ѷ���
			ball.setX(ball.getX()+ball.getXinterval()); // �̵����ݸ�ŭ ���� x��ǥ�� ���Ͽ� x��ǥ ����
			ball.setY(ball.getY()+ball.getYinterval()); // �̵����ݸ�ŭ ���� y��ǥ�� ���Ͽ� y��ǥ ����
			
			/*if(((ball.getX()<0 && ball.getXinterval()<0) || 
					ball.getX() + Ball.SIZE > BallFrame.WIDTH - 15 && ball.getXinterval() > 0)|| //���� x��ǥ�� �гι��� �Ѿ�ų�
					((ball.getY()<0 && ball.getYinterval()<0) || 
							ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYinterval() > 0)) {//���� y��ǥ�� �гι��� �Ѿ�ٸ�
				ball.setInterval(-ball.getXinterval(),-ball.getYinterval());
			} //�� �밢���������θ� �����̴� �ڵ�*/
			
			if((ball.getX()<0 && ball.getXinterval()<0) || 
					ball.getX() + Ball.SIZE > BallFrame.WIDTH - 15 && ball.getXinterval() > 0) 
			{
				ball.setInterval(-ball.getXinterval(),ball.getYinterval());
			}
			
			if((ball.getY()<0 && ball.getYinterval()<0) || 
					ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYinterval() > 0) 
			{
				ball.setInterval(ball.getXinterval(),-ball.getYinterval());
			}
			
			
			try {
				Thread.sleep(30);
			}catch(InterruptedException e) {}
		}
	}
}
