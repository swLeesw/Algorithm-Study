package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stairCnt = Integer.parseInt(br.readLine());
        int[] stair = new int[stairCnt + 1];
        int[] dp = new int[stairCnt + 1];
        for (int i = 1; i <= stairCnt; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }
        if (stairCnt <= 2) {
            int sum = 0;
            for (int i = 1; i <= stairCnt; i++) {
                sum += stair[i];
            }
            System.out.println(sum);
        } else {
            dp[1] = stair[1];
            dp[2] = stair[2] + dp[1];
            for (int i = 3; i <= stairCnt; i++) {
                //2개연속오르고 2칸 오르기 or 1개 오르고 2칸오르고 2개연속 오르기
                dp[i] = Math.max(dp[i - 2] + stair[i], dp[i - 3] + stair[i - 1] + stair[i]);
            }
            System.out.println(dp[stairCnt]);
        }
    }
}
