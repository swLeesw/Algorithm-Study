package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());           //집의 개수
        int C = Integer.parseInt(st.nextToken());           //공유기 개수
        int[] home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        //이분 탐색을 위한 정렬
        Arrays.sort(home);

        //가질수 있는 거리의 최소값과 최댓값
        int min = 1;
        int max = Math.abs(home[0] - home[N - 1]) + 1;

        //포인터가 넘어가면 종료
        while (min < max) {
            int mid = (min + max) / 2;
            if (getCnt(mid, home) < C) {        //설치가능한 공유기 수 < 공유기 개수 -> 최대포인트를 중간값으로
                max = mid;
            } else {                            //아니면 최소를 최대포인트+1로
                min = mid + 1;
            }
        }
        System.out.println(min -1);
    }

    private static int getCnt(int mid, int[] home) {

        int cnt = 1;
        int last = home[0];

        for (int i = 1; i < home.length; i++) {
            int cur = home[i];
            if (cur - last >= mid) {
                cnt++;
                last = cur;
            }
        }
        return cnt;
    }
}
