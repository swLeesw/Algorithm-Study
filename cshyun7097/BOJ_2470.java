package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int solutionCnt = Integer.parseInt(br.readLine());                //용액의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] solution = new int[solutionCnt];
        for (int i = 0; i < solutionCnt; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solution);
        search(solution, solutionCnt);
    }

    private static void search(int[] solution, int solutionCnt) {
        if (solution[solutionCnt - 1] < 0) {                //모두 음수일때는 마지막 2개가 0에 가까움
            System.out.println(solution[solutionCnt - 2] + " " + solution[solutionCnt - 1]);
        } else if (solution[0] > 0) {                       //모두 양수일때는 앞 2개가 가장 0에 가까움
            System.out.println(solution[0] + " " + solution[1]);
        } else {
            int posNum = Integer.MAX_VALUE, posI = 0, negNum = Integer.MIN_VALUE, negI = 0;
            for (int i = 0; i < solutionCnt; i++) {
                if (solution[i] < 0 && solution[i] > negNum) {
                    negI = i;
                    negNum = solution[i];
                } else if (solution[i] > 0 && solution[i] < posNum) {
                    posNum = solution[i];
                    posI = i;
                }
            }
            //최소일때의 포인터 저장
            int ansI = 0, ansJ = 0;
            int ans = Integer.MAX_VALUE;
            //양수 중 가장 작은것 2개를 더해서 ans값 초기화
            if (posI + 1 < solutionCnt && solution[posI] + solution[posI + 1] < ans) {
                ansI = posI;
                ansJ = posI + 1;
                ans = solution[posI] + solution[posI + 1];
            }
            //음수 중 가장 큰 2개를 더해서 ans보다 최소인지 확인
            if (negI >= 1 && Math.abs(solution[negI] + solution[negI - 1]) < ans) {
                ansI = negI;
                ansJ = negI - 1;
                ans = Math.abs(solution[negI] + solution[negI - 1]);
            }
            //투포인터 시작
            int tmp = 0;
            while (posI < solutionCnt && negI >= 0) {
                tmp = solution[posI] + solution[negI];
                //더 최소값이면 포인터값 저장 및 최소값 초기화
                if (Math.abs(tmp) < ans) {
                    ans = Math.abs(tmp);
                    ansI = negI;
                    ansJ = posI;
                }
                //음수면 양수포인터 ++
                if (tmp < 0) {
                    posI++;
                } else {        //양수면 음수포인터 --
                    negI--;
                }
            }
            System.out.println(solution[ansI] + " " + solution[ansJ]);
        }
    }
}
