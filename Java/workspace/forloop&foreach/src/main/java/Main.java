import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	static Long START = 0L;
	static List<String> arrayList = null;
	static List<String> linkedList = null;
	static String[] array = null;

	public static void main(String[] args) {
//		int size1 = 1000;
//		init(size1);
//		test(size1);
		
//		int size2 = 10000;
//		init(size2);
//		test(size2);
		
//		int size3 = 1000000;
//		init(size3);
//		test(size3);
		
		int size4 = 2000000;
		init(size4);
		test(size4);
	}
	
	// Initialize
	public static void init(int size) {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		array = new String[size];
		for(int i=0;i<size;i++) {
			arrayList.add("test" + i);
			linkedList.add("test" + i);
			array[i] = "test" + i;
		}
	}
	
	public static void test(int size) {
		System.out.println("============================= element size is " + size + " =============================");
		START = System.currentTimeMillis();
		forEach(array);
		System.out.println("[Array] for: " + (System.currentTimeMillis() - START) + "ms");
		
		START = System.currentTimeMillis();
		forEach(array);
		System.out.println("[Array] for-each: " + (System.currentTimeMillis() - START) + "ms\n");
		
		START = System.currentTimeMillis();
		forEach(arrayList);
		System.out.println("[ArrayList] for: " + (System.currentTimeMillis() - START) + "ms");
		
		START = System.currentTimeMillis();
		forEach(arrayList);
		System.out.println("[ArrayList] for-each: " + (System.currentTimeMillis() - START) + "ms\n");
		
		START = System.currentTimeMillis();
		forEach(linkedList);
		System.out.println("[LinkedList] for: " + (System.currentTimeMillis() - START) + "ms");
		
		START = System.currentTimeMillis();
		forEach(linkedList);
		System.out.println("[LinkedList] for-each: " + (System.currentTimeMillis() - START) + "ms");
		
		System.out.println("==================================================================================\n");
	}
	
	public static void forLoop(List<String> list) {
		int size = list.size();
		for(int i=0;i<size;i++) {
			String word = list.get(i);
//			System.out.print(num + " ");
		}
//		System.out.println();
	}
	
	public static void forLoop(String[] arr) {
		int size = arr.length;
		for(int i=0;i<size;i++) {
			String word = arr[i];
//			System.out.print(num + " ");
		}
//		System.out.println();
	}
	
	public static void forEach(String[] arr) {
		for(String word:arr) {
//			System.out.print(num + " ");
		}
//		System.out.println();
	}
	
	public static void forEach(List<String> words) {
		for(String word:words) {
//			System.out.print(num + " ");
		}
//		System.out.println();
	}

}
