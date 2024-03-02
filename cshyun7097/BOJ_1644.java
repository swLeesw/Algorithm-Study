package algo_sil;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> prime = new ArrayList<>();
        int N = sc.nextInt();
        //입력값 1이면 0개 출력 후 종료
        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }
        makePrime(N, prime, new boolean[N + 1]);

        int lPoint = 0, rPoint = 0;
        int sum = prime.get(0);
        int result = 0;

        while (lPoint < prime.size() && rPoint < prime.size()) {
            if (sum == N) {
                result++;
                sum -= prime.get(lPoint);
                lPoint++;
            } else if (sum > N) {
                sum -= prime.get(lPoint);
                lPoint++;
            } else {
                if (++rPoint >= prime.size()) break;
                sum += prime.get(rPoint);
            }
        }
        System.out.println(result);
    }

    private static void makePrime(int n, ArrayList<Integer> prime, boolean[] tmp) {
        for (int i = 2; i <= n; i++) {
            tmp[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (tmp[i]) {
                for (int j = i + i; j <= n; j += i) {
                    tmp[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (tmp[i]) {
                prime.add(i);
            }
        }
    }
}
