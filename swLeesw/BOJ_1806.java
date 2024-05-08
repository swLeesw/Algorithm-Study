import java.util.*;
import java.io.*;

public class Main {
    static int[] arr = new int[100001];
    static int n, s;
    static int sol = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i] += arr[i - 1];
        }

        int start = 0;
        int end = 1;

        while (start < end && end <= n) {
            int sum = arr[end] - arr[start];
            int idx = end - start;
            if (sum < s) {
                end++;
                continue;
            }

            if (sol > idx) {
                sol = idx;
            }
            start++;
        }

        StringBuilder sb = new StringBuilder();
        if (sol == 100001) {
            sb.append(0);
        } else {
            sb.append(sol);
        }
        System.out.println(sb);
    }
}
