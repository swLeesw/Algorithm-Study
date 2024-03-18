package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        long[] dp = new long[1101];
        final int INF = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = INF;
        }
        dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] >= INF) continue;
            for (int j = 1; j <= arr[i]; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        if (dp[n] == INF) {
            System.out.print(-1);
        } else {
            System.out.print(dp[n]);
        }
    }
}