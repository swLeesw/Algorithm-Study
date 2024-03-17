package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= str.length(); i++) {
            int cur = str.charAt(i - 1) - '0';
            if (1 <= cur && cur <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= 1000000;
            }
            if (i==1) continue;
            int bef = str.charAt(i - 2) - '0';
            if (bef == 0) continue;
            int val = bef * 10 + cur;
            if (val >= 10 && val <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= 1000000;
            }
        }
        System.out.println(dp[str.length()]);
    }
}
