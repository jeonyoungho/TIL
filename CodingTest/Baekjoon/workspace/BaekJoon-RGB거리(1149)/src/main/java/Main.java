import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N][3];
		int[][] arr = new int[N][3];
		for(int i=0;i<N;i++) {
			String[] splited = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(splited[0].trim());
			arr[i][1] = Integer.parseInt(splited[1].trim());
			arr[i][2] = Integer.parseInt(splited[2].trim());
		}
		
		dp[0][0] = arr[0][0]; // 맨처음에 R 선택
		dp[0][1] = arr[0][1]; // 맨처음에 G 선택
		dp[0][2] = arr[0][2]; // 맨처음에 B 선택
		
		for(int i=1;i<arr.length;i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		
		
		bw.write(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]) + "");
		
		br.close();
		
		bw.flush();
		bw.close();
	}

}
