import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		
		int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		int n;
		for(int i=0;i<T;i++) {
			n = Integer.parseInt(br.readLine());
			for(int j=4;j<=n;j++) {
				dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
			}
			bw.write(dp[n] + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

}
