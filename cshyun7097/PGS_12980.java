package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PGS_12980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        while (N != 0) {
            if (N % 2 == 0) {
                N /= 2;
            } else {
                N--;
                ans++;
            }
        }
        System.out.println(ans);
    }
}
