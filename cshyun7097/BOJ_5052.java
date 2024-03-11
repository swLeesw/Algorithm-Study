package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] phone = new String[N];

            for (int i = 0; i < N; i++) {
                phone[i] = br.readLine();
            }
            Arrays.sort(phone);

            if (same(N, phone)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean same(int N, String[] phone) {
        for (int i = 0; i < N - 1; i++) {
            if (phone[i + 1].startsWith(phone[i])) {
                return false;
            }
        }
        return true;
    }
}
