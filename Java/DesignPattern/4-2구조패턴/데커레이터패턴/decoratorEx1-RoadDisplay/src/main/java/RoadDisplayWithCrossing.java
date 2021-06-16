
public class RoadDisplayWithCrossing extends DisplayDecorator {
	public RoadDisplayWithCrossing(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	public void draw() {
		super.draw();
		displayCrossing();
	}
	
	private void displayCrossing() {
		System.out.println("\t교차로 표시");
	}
}
