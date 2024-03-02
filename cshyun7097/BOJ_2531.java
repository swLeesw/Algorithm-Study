package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int onBeltCnt = Integer.parseInt(st.nextToken());               //벨트 위 접시 수
        int sushiKind = Integer.parseInt(st.nextToken());               //초밥 종류수
        int relayEatCnt = Integer.parseInt(st.nextToken());             //연속으로 먹을 수 있는 초밥 수
        int couponNum = Integer.parseInt(st.nextToken());               //쿠폰 번호

        int[] sushi = new int[onBeltCnt + relayEatCnt];                 //끝 번호에서 연속먹는거까지 생각해서 배열 생성
        int[] isEat = new int[sushiKind + 1];                           //초밥 먹었는지 확인배열

        for (int i = 0; i < onBeltCnt; i++) {
            int tmp = Integer.parseInt(br.readLine());
            sushi[i] = tmp;
        }
        int ans = 1;
        isEat[couponNum]++;
        ans = makeWindow(onBeltCnt, relayEatCnt, sushi, isEat, ans);
        ans = twoPoint(ans, relayEatCnt, onBeltCnt, sushi, isEat);
        System.out.println(ans);
    }

    private static int twoPoint(int ans, int relayEatCnt, int onBeltCnt, int[] sushi, int[] isEat) {
        int nowSum = ans;
        for (int i = relayEatCnt; i < onBeltCnt + relayEatCnt; i++) {
            int j = sushi[i - relayEatCnt];
            isEat[j]--;
            if (isEat[j] == 0) {
                nowSum--;
            }
            isEat[sushi[i]]++;
            if (isEat[sushi[i]] == 1) {
                nowSum++;
            }
            ans = Math.max(ans, nowSum);
        }
        return ans;
    }

    private static int makeWindow(int onBeltCnt, int relayEatCnt, int[] sushi, int[] isEat, int ans) {
        for (int i = onBeltCnt; i < onBeltCnt + relayEatCnt; i++) {
            sushi[i] = sushi[i - onBeltCnt];
            if (isEat[sushi[i]] == 0) {
                ans++;
            }
            isEat[sushi[i]]++;
        }
        return ans;
    }
}
