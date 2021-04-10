import java.util.ArrayList;
import java.util.List;

public class TestMain2 {

	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		System.out.println("===== list1 =====");
		for(Integer i:list1) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		List<Integer> list2 = new ArrayList<Integer>(list1);
		list2.add(4);
		
		System.out.println("===== list2 =====");
		for(Integer i:list2) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		Integer[] arr = list2.toArray(new Integer[0]);
		System.out.println("===== arr =====");
		for(Integer i:arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	

}
