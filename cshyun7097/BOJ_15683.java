package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static int min = Integer.MAX_VALUE;
    static int N, M;
    static List<CCTV> cctvs = new ArrayList<>();

    //CCTV 클래스
    static class CCTV {
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    private static int getCnt(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());               //행의 개수
        M = Integer.parseInt(st.nextToken());               //열의 개수
        int[][] room = new int[N][M];                                   //사무실

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                room[i][j] = tmp;                           //값 넣기
                if (1 <= tmp && tmp <= 5) {
                    cctvs.add(new CCTV(tmp, i, j));
                }
            }
        }
        dfs(0, room);
        System.out.println(min);
    }

    private static int[][] clone(int[][] map) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    private static void dfs(int cnt, int[][] room) {
        if (cnt == cctvs.size()) {
            min = Math.min(min, getCnt(room));
            return;
        }
        int[][] tmp;

        if (cctvs.get(cnt).num == 1) {
            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);
        } else if (cctvs.get(cnt).num == 2) {
            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);
        } else if (cctvs.get(cnt).num == 3) {
            tmp = clone(room);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);
        }else if (cctvs.get(cnt).num == 4) {
            tmp = clone(room);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);

            tmp = clone(room);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);
        } else if (cctvs.get(cnt).num == 5) {
            tmp = clone(room);
            changeUp(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeDown(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeRight(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            changeLeft(cctvs.get(cnt).x, cctvs.get(cnt).y, tmp);
            dfs(cnt + 1, tmp);
        }
    }

    //위 변경
    private static void changeUp(int x, int y, int[][] tmp) {
        for (int i = x - 1; i >= 0; i--) {
            if (tmp[i][y] == 6) return;
            if (tmp[i][y] != 0) continue;
            tmp[i][y] = -1;
        }
    }

    //아래 변경
    private static void changeDown(int x, int y, int[][] tmp) {
        for (int i = x + 1; i < N; i++) {
            if (tmp[i][y] == 6) return;
            if (tmp[i][y] != 0) continue;
            tmp[i][y] = -1;
        }
    }

    //왼쪽 변경
    private static void changeLeft(int x, int y, int[][] tmp) {
        for (int i = y - 1; i >= 0; i--) {
            if (tmp[x][i] == 6) return;
            if (tmp[x][i] != 0) continue;
            tmp[x][i] = -1;
        }
    }

    //오른쪽 변경
    private static void changeRight(int x, int y, int[][] tmp) {
        for (int i = y + 1; i < M; i++) {
            if (tmp[x][i] == 6) return;
            if (tmp[x][i] != 0) continue;
            tmp[x][i] = -1;
        }
    }
}