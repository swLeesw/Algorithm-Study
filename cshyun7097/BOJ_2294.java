package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {
    static final long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        long[] dp = new long[K + 1];
        Arrays.fill(dp, INF);
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = coin[i]; j <= K; j++) {
                if (j == coin[i]) dp[j] = 1;
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + dp[coin[i]]);
            }
        }
        System.out.println(dp[K] == INF ? -1 : dp[K]);
    }
}