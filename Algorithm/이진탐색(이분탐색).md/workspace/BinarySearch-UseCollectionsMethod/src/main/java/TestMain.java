import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(3);
		list.add(10);
		list.add(2);
		list.add(20);
		
		// binary search는 정렬된 상태로 수행되어야 함
		// 메소드 실행 후 1 -> 2 -> 3 -> 10 -> 20
		Collections.sort(list);
		
		int index = Collections.binarySearch(list, 10);
		System.out.println("10의 인덱스: " + index);

		// 만약 해당 요소를 찾지 못했을때는 (- insertion point) - 1에 해당하는 값을 리턴
		// 13은 현재 list안에 포함되어 있지 않음, 만약 포함되었다면 4번째 위치에 존재하게 되어있음
		// 그래서 메소드 실행결과 (-4-1), 즉 -5를 리턴하게 됨
		int index2 = Collections.binarySearch(list, 13);
		System.out.println("13의 인덱스: " + index2);
	}

}
