
public class Main {

	public static void main(String[] args) {
		Display roadDisplay = new RoadDisplay();
		roadDisplay.draw();
		
		Display roadWithLane = new RoadDisplayWithLane(roadDisplay); //인자로는 추가시키려는 기능을 가지는 인스턴스 
		roadWithLane.draw();
		
		Display roadWithLaneTraffic = new RoadDisplayWithTraffic(roadWithLane);
		roadWithLaneTraffic.draw();
		
	}

}
