package algo_sil;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[N / 2];
        int[] up = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            int downVal = Integer.parseInt(br.readLine());
            int upVal = Integer.parseInt(br.readLine());
            down[i] = downVal;
            up[i] = upVal;
        }
        Arrays.sort(up);
        Arrays.sort(down);
        int min = N;
        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int line = search(0, N / 2, i, down) + search(0, N / 2, H - i + 1, up);
            if (min == line) {
                cnt++;
                continue;
            }
            if (min > line) {
                min = line;
                cnt = 1;
            }
        }
        sb.append(min).append(" ").append(cnt);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int search(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr.length - right;
    }
}