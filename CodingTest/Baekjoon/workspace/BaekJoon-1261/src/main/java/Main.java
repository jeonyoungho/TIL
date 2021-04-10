import java.util.*;

public class Main {
	
	static class Point implements Comparable<Point> {
		int x; // m 두번째
		int y; // n 첫번째
		int cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Point o1) {
			return this.cnt - o1.cnt;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] splited = scanner.nextLine().split(" ");
		int m = Integer.parseInt(splited[0]); // 가로
		int n = Integer.parseInt(splited[1]); // 세로
		
		int[][] arr = new int[n+1][m+1];
		for(int i=1;i<=n;i++) {
			String s = scanner.nextLine();
			for(int j=1;j<=m;j++) {
				arr[i][j] = Integer.parseInt(s.substring(j-1, j));
			}
		}
		scanner.close();
		
		
//		for(int[] c:arr) {
//			System.out.println(Arrays.toString(c));
//		}
//		for(boolean[] b:isVisited) {
//			System.out.println(Arrays.toString(b));
//		}
		
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.add(new Point(1, 1, 0));
		boolean[][] isVisited = new boolean[n+1][m+1];
		isVisited[1][1] =true;
		
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		while(queue.isEmpty() == false) {
			
			Point p = queue.poll();
//			System.out.println("Polling (" + p.x + ", " + p.y + "), Cnt: " + p.cnt);
			
			if(p.x == n && p.y == m) { // n:2, m: 4 (n,m) (2,4) 
				System.out.println(p.cnt);
				return;
			}
			
			for(int i=0;i<4;i++) {
				int targetX = p.x + dx[i];
				int targetY = p.y + dy[i];
//				System.out.println("p.y: " + p.y + ", dy[" + i +"]: " + dy[i]);
				
				if(targetX < 1 || targetY < 1 || targetX > n || targetY > m) {
					continue;
				}
//				System.out.println("M: " + m + ", N: " + n + ", targetY : " + targetY + ", targetX: " + targetX);
				if(!isVisited[targetX][targetY]) {
					isVisited[targetX][targetY] = true;
					if(arr[targetX][targetY] == 1) {
//						System.out.println("arr[" + targetX + "][" + targetY + "]: " + arr[targetY][targetX]);
//						System.out.println("Add (" + targetX + ", " + targetY + ") , Cnt: " + (p.cnt+1));
						queue.add(new Point(targetX, targetY, p.cnt+1));
					} else {
//						System.out.println("Add (" + targetX + ", " + targetY + ") , Cnt: " + p.cnt);
						queue.add(new Point(targetX, targetY, p.cnt));
					}
				}
				
			}
			
		}
		
	}

}
