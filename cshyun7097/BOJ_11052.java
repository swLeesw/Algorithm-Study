package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + cards[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
