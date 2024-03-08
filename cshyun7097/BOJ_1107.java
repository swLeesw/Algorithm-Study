package algo_sil;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_1107 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int channel = sc.nextInt();
        int brokeCnt = sc.nextInt();

        boolean[] broken = new boolean[10];
        for (int i = 0; i < brokeCnt; i++) {
            broken[sc.nextInt()] = true;
        }
        int res = Math.abs(channel - 100);
        for (int i = 0; i < 1000000; i++) {
            String num = String.valueOf(i);
            int numLength = num.length();
            boolean cantGo = false;

            for (int j = 0; j < numLength; j++) {
                int tmp = num.charAt(j) - '0';
                if (broken[tmp]) {
                    cantGo = true;
                    break;
                }
            }
            if (cantGo) continue;
            res = Math.min(res, numLength + Math.abs(channel - i));
        }
        System.out.println(res);
    }
}