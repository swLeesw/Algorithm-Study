package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138 {
    static int N;
    static int res = Integer.MAX_VALUE;


    private static void change(int[] arr, int i) {
        //이전
        if (i - 1 >= 0) {
            arr[i - 1] = arr[i - 1] == 0 ? 1 : 0;
        }
        //현재
        arr[i] = arr[i] == 0 ? 1 : 0;
        //다음
        if (i + 1 <= N - 1) {
            arr[i + 1] = arr[i + 1] == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cntA = 0, cntB = 1;
        boolean isAble = false;


        int[] now = new int[N];
        int[] target = new int[N];

        String nowStr = br.readLine();
        String targetStr = br.readLine();

        for (int i = 0; i < N; i++) {
            now[i] = (int) nowStr.charAt(i) - '0';
            target[i] = (int) targetStr.charAt(i) - '0';
        }

        int[] arr = now.clone();
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] != target[i - 1]) {
                change(arr, i);
                cntA++;
            }
        }

        if (arr[N - 1] == target[N - 1]) {
            isAble = true;
            res = Math.min(res, cntA);
        }

        arr = now.clone();
        change(arr, 0);
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] != target[i - 1]) {
                change(arr, i);
                cntB++;
            }
        }

        if (arr[N - 1] == target[N - 1]) {
            isAble = true;
            res = Math.min(res, cntB);
        }
        //마지막 전구가 다르면 똑같이 못바꾸므로 -1출력
        if (isAble) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }
}
