import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException   {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 3;
		
		// 4 = (dp[2] * 2) + dp[3]
		
		for(int i=3;i<=n;i++) {
			dp[i] = (dp[i-2] * 2) + dp[i-1];
			dp[i] %= 10007;
			 
		}
		
		bw.write(dp[n] + "");
		bw.flush();
		bw.close();
		
		br.close();
		
	}
	
}
