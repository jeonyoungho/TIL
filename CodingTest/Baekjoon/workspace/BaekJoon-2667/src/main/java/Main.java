import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<s.length();j++) {
				map[i][j] = Integer.parseInt(s.substring(j, j+1));
			}
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[N][N];
		int total = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map[i].length;j++) {
				if(isVisited[i][j] == false && map[i][j] == 1) {
					int cnt = dfs(map, isVisited, i, j);
					cnt++;
					queue.add(cnt);
					
					total++;
				}
			}
		}
		
		bw.write(String.valueOf(total) + "\n");
		while(queue.isEmpty() == false) {
			bw.write(String.valueOf(queue.poll()) + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	private static int dfs(int[][] map, boolean[][] isVisited, int x, int y) {
		isVisited[x][y] = true;
		int cnt = 0;
		
		for(int i=0;i<4;i++) {
			int targetX = x + dx[i];
			int targetY = y + dy[i];
			
			if(targetX < 0 || targetY < 0 || targetX >= map.length || targetY >= map[0].length) continue;
			
			if(isVisited[targetX][targetY] == false && map[targetX][targetY] == 1) {
				cnt++;
				cnt += dfs(map, isVisited, targetX, targetY);
			}
		}
		
		return cnt;
		
	}

}
