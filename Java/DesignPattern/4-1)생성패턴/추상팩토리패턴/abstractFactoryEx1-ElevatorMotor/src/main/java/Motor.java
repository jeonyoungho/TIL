public abstract class Motor {
	private MotorStatus motorStatus;
	protected Door door;
	public void setDoor(Door door) {
		this.door = door;
	}
	public Motor() {
		this.motorStatus = MotorStatus.STOPED;
	}
	public MotorStatus getMotorStatus() {
		return motorStatus;
	}
	
	protected void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}
	
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if( motorStatus == MotorStatus.MOVING)
			return;
		
		DoorStatus doorStatus = door.getDoorStatus();
		if(doorStatus == DoorStatus.OPENED)
			door.close();
		
		moveMotor(direction);
		setMotorStatus(MotorStatus.MOVING);
	}
	
	protected abstract void moveMotor(Direction direction);
}
