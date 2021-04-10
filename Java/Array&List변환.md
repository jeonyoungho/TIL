# Array&List변환

## List -> Array 변환
### case1 - 반복문 이용
~~~
ArrayList<String> arrayList = new ArrayList<>();

arrayList.add("Test1");
arrayList.add("Test2");
arrayList.add("Test3");


String[] array = new String[arrayList.size()];
int size=0;
for(String temp : arrayList){
  array[size++] = temp;
}
~~~

### case2 - List에서 제공하는 toArray메서드 이용
~~~
ArrayList<String> arrayList = new ArrayList<>();

arrayList.add("Test1");
arrayList.add("Test2");
arrayList.add("Test3");

String[] array = arrayList.toArray(new String[arrayList.size()]);
~~~

## Array -> List 변환
### case1 - 반복문 이용
~~~
String[] array = new String[3];

array[0] = "Test1";
array[1] = "Test2";
array[2] = "Test3";

ArrayList<String> arrayList = new ArrayList<>();
for(String temp : array){
    arrayList.add(temp);
}
~~~

### case2 - Arrays에서 제공하는 메서드 이용 (Arrays.asList())
~~~
String[] array = new String[3];

array[0] = "Test1";
array[1] = "Test2";
array[2] = "Test3";

ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));
~~~

## 테스트 코드
~~~
import java.util.ArrayList;
import java.util.List;

public class TestMain2 {

	private final static int NODE_NUM = 6;
	
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
		
        // Convert ArrayList -> Array
		Integer[] arr = list2.toArray(new Integer[0]);
		System.out.println("===== arr =====");
		for(Integer i:arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	

}
~~~

#### 출처
- https://mommoo.tistory.com/32