public class DiagonalMoveStrategy extends DirectionStrategy{
	public void move(Ball ball) {
		ball.setInterval(Ball.INTERVAL,Ball.INTERVAL); //볼의 초기 이동간격 설정
		while(true) { // 무한루프
			ball.setX(ball.getX()+ball.getXinterval()); // 이동간격만큼 볼의 x좌표에 더하여 x좌표 설정
			ball.setY(ball.getY()+ball.getYinterval()); // 이동간격만큼 볼의 y좌표에 더하여 y좌표 설정
			
			/*if(((ball.getX()<0 && ball.getXinterval()<0) || 
					ball.getX() + Ball.SIZE > BallFrame.WIDTH - 15 && ball.getXinterval() > 0)|| //볼의 x좌표가 패널범위 넘어가거나
					((ball.getY()<0 && ball.getYinterval()<0) || 
							ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYinterval() > 0)) {//볼의 y좌표가 패널범위 넘어간다면
				ball.setInterval(-ball.getXinterval(),-ball.getYinterval());
			} //한 대각선방향으로만 움직이는 코드*/
			
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
