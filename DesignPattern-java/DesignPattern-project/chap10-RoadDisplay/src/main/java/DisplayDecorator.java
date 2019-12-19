public abstract class DisplayDecorator extends Display{
	private Display decoratedDisplay;

	public DisplayDecorator(Display decoratedDisplay) {
		super();
		this.decoratedDisplay = decoratedDisplay;
	}
	
	public void draw() {
		decoratedDisplay.draw();
	}
	
	

}
