public class Client {

	public static void main(String[] args) {
		MultiButtonController btn = new MultiButtonController();
		RobotFireCommand gfComm = new RobotFireCommand();
		Robot robot = new Robot();
		gfComm.setTheRobot(robot);
		btn.addCommand(gfComm);
		
		Car car = new Car();
		CarEngineCommand ceComm = new CarEngineCommand();
		ceComm.setCar(car);
		btn.addCommand(ceComm);
		
		
		
		RobotMissileCommand rmComm = new RobotMissileCommand();
		rmComm.setTheRobot(robot);
		btn.addCommand(rmComm);
		
		
		btn.pressed(1);
		btn.pressed(1);
		btn.pressed(0);
		btn.pressed(0);
		
		btn.pressed(2);
		btn.pressed(2);
		btn.pressed(0);
		btn.pressed(1);
		
		btn.pressed(0);
		btn.pressed(2);
		btn.pressed(1);
		btn.pressed(1);
	}

}
