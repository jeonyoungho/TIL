public class TestMain {

	private static final int num = 10;
	private static final int target = 7;
	
	public static void main(String[] args) {
		
		
		int[] arr = new int[num];
		for(int i=0;i<num;i++) {
			arr[i] = i;
		}
		
		int start = 0;
		int end = num-1;
		int mid = 0;
		int answer = num -1;
		int cnt = 0;
		while(end >= start) {
			mid = (end + start)/2;
			System.out.println("start: " + start + ", end: " + end +", mid: " + mid);
			
//			if(arr[mid] == target) {
//				answer = mid;
//				break;
//			}
			
			if(arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
				answer = arr[mid];
			}
		}
		System.out.println("finish " + answer);
		
	}

}
