package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensor = new int[N];
        Integer[] diff = new Integer[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);
        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensor[i + 1] - sensor[i];
        }
        Arrays.sort(diff, Collections.reverseOrder());
        int sum = 0;
        for (int i = K - 1; i < N - 1; i++) {
            sum += diff[i];
        }
        System.out.println(sum);
    }
}
