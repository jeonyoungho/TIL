
public class RoadDisplayWithLane extends DisplayDecorator {
	public RoadDisplayWithLane(Display decoratedDisplay) {
		super(decoratedDisplay);
	}

	public void draw() {
		super.draw();
		displayLane();
	}

	private void displayLane() {
		System.out.println("\t차선 표시");
	}
}
