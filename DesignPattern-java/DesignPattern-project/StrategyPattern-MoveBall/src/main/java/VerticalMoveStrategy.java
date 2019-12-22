
public class VerticalMoveStrategy extends DirectionStrategy {
	@Override
	public void move(Ball ball) {

		// TODO Auto-generated method stub
		ball.setInterval(0,Ball.INTERVAL);
		while(true) {
			ball.setY(ball.getY()+ball.getYinterval());
			if((ball.getY()<0 && ball.getYinterval()<0) || 
					ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYinterval() > 0) 
			{
				ball.setInterval(0,-ball.getYinterval());
			}
			try {
				Thread.sleep(30);
			}catch(InterruptedException e) {}
		}
	}

}
