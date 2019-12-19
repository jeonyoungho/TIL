
public class Main {

	public static void main(String[] args) {
		Display roadDisplay = new RoadDisplay();
		roadDisplay.draw();
		
		Display roadWithLane = new RoadDisplayWithLane(roadDisplay); //���ڷδ� �߰���Ű���� ����� ������ �ν��Ͻ� 
		roadWithLane.draw();
		
		Display roadWithLaneTraffic = new RoadDisplayWithTraffic(roadWithLane);
		roadWithLaneTraffic.draw();
		
	}

}
