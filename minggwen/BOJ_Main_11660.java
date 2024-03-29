import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_11660{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] result = new int[m];
		int[][] map = new int[n+1][n+1];
		int[][] dp = new int[n+1][n+1];
		
		for(int x = 1; x<=n;x++) {
			st = new StringTokenizer(br.readLine());
			for(int y = 1; y<=n; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				dp[x][y] = dp[x-1][y]+dp[x][y-1]+map[x][y]-dp[x-1][y-1];
			}
		}
		
		int x1,y1,x2,y2;
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			result[i] = dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1];
		}
		for(int i = 0; i<m; i++) {
			System.out.println(result[i]);
		}
	}
}