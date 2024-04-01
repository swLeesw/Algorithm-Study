package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][N + 1];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], 101);
        }
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 0;
        }
        while (true) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1) {
                break;
            }
            dp[from][to] = 1;
            dp[to][from] = 1;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (j == k || j == i) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE, max = -1;
        for (int i = 1; i <= N; i++) {
            max = -1;
            for (int j = 1; j <= N; j++) {
                max = Math.max(max, dp[i][j]);
            }
            if (min > max) {
                arr.clear();
                arr.add(i);
                min = max;
            } else if (min == max) {
                arr.add(i);
            }
        }
        Collections.sort(arr);
        sb.append(min).append(" ").append(arr.size()).append("\n");
        for (Integer i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
