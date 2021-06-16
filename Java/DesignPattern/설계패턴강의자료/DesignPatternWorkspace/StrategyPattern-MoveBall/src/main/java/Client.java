
public class Client {
	private static final int INIT_LOCATION[] = {50, 100, 150};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ball[] balls = new Ball[3];
		for(int i=0;i<balls.length;i++) {
			balls[i] = new Ball(INIT_LOCATION[i],INIT_LOCATION[i]);
			balls[i].setDirectionStrategy(new DiagonalMoveStrategy());
			balls[i].setDrawStrategy(new RedDrawStrategy());
			
			balls[i].start();
		}
		new BallFrame(balls);
		
	}

}
