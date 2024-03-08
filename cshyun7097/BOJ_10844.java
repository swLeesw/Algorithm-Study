package algo_sil;

import java.util.Scanner;

public class BOJ_10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][] dp = new long[N + 1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] % 1_000_000_000;
            for (int j = 1; j < 10; j++) {
                if (j == 9) {
                    dp[i][j] = dp[i - 1][8] % 1_000_000_000;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] % 1_000_000_000;
                }
            }
        }
        long res = 0L;
        for (int i = 0; i < 10; i++) {
            res = (res + dp[N][i]) % 1_000_000_000;
        }
        System.out.println(res);
    }
}
