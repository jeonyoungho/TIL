import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String str = scanner.nextLine();
		int n = Integer.parseInt(str);
		
		boolean[][] isConnected = new boolean[n][n];
		int[][] arr = new int[n-1][2];
		for(int i=0;i<n-1;i++) {
			String s = scanner.nextLine();
			String[] splited = s.split(" ");
			
			int node1 = Integer.parseInt(splited[0])-1;
			int node2 = Integer.parseInt(splited[1])-1;
			
			arr[i][0] = node1;
			arr[i][1] = node2;
			
			isConnected[node1][node2] = true;
			isConnected[node2][node1] = true;
		}
		
		String expected = scanner.nextLine();
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		
		StringBuilder sb = new StringBuilder();
		while(queue.isEmpty() == false) {
			int node = queue.poll();
			sb.append((node+1) + " ");
			for(int i=0;i<n;i++) {
				if(isConnected[i][node] == true) {
					queue.offer(i);
					isConnected[i][node] = false;
					isConnected[node][i] = false;
				}
			}
			
		}
		
		String result = sb.toString();
		result = result.substring(0, result.length()-1);
		if(expected.equals(result)) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}
		
		scanner.close();
	}

}
