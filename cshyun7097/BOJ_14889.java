package algo_sil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main_14889_최승현 {

    static int N;
    static int[][] score;
    static boolean[] number;

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N][N];
        number = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0);
        System.out.println(res);

    }
    static void comb(int idx, int count) {
        if(count == N / 2) {
            getScore();
            return;
        }
        for(int i = idx; i < N; i++) {
            if(!number[i]) {
                number[i] = true;
                comb(i + 1, count + 1);
                number[i] = false;
            }
        }
    }
    static void getScore() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (number[i] && number[j]) {
                    team_start += score[i][j];
                    team_start += score[j][i];
                }
                else if (!number[i] && !number[j]) {
                    team_link += score[i][j];
                    team_link += score[j][i];
                }
            }
        }
        int diff = Math.abs(team_start - team_link);
        if (diff == 0) {
            System.out.println(diff);
            System.exit(0);
        }
        res = Math.min(diff, res);
    }

}