
public class RoadDisplayWithTraffic extends DisplayDecorator {
	public RoadDisplayWithTraffic(Display decoratedDisplay) {
		super(decoratedDisplay);
	}

	public void draw() {
		super.draw();
		displayTraffic();
	}

	private void displayTraffic() {
		System.out.println("���뷮 ǥ��");
	}
	
}
