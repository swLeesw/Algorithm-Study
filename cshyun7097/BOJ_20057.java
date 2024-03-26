package algo_sil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_20057 {
    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] forwardCnt = {1, 1, 2, 2};
    static int[][] sandX = {{-1, 1, -2, -1, 1, 2, -1, 1, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2},
            {1, -1, 2, 1, -1, -2, 1, -1, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2}};
    static int[][] sandY = {{1, 1, 0, 0, 0, 0, -1, -1, -2}, {-1, 1, -2, -1, 1, 2, -1, 1, 0},
            {-1, -1, 0, 0, 0, 0, 1, 1, 2}, {1, -1, 2, 1, -1, -2, 1, -1, 0}};
    static int[] sandRatio = {1, 1, 2, 7, 7, 2, 10, 10, 5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bw.write(move(N / 2, N / 2) + "\n");
        bw.flush();
        bw.close();
    }

    static int move(int x, int y) {
        int cnt = 0;

        int curX = x;
        int curY = y;

        while (true) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < forwardCnt[d]; i++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];

                    if (isOut(nx, ny)) {
                        return cnt;
                    }
                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int tmp = 0;


                    for (int j = 0; j < 9; j++) {
                        int sX = nx + sandX[d][j];
                        int sY = ny + sandY[d][j];
                        int amount = (sand * sandRatio[j]) / 100;

                        if (isOut(sX, sY)) {
                            cnt += amount;
                        } else {
                            map[sX][sY] += amount;
                        }
                        tmp += amount;
                    }
                    int aX = nx + dx[d];
                    int aY = ny + dy[d];
                    int aAmount = sand - tmp;
                    if (isOut(aX, aY)) {
                        cnt += aAmount;
                    } else {
                        map[aX][aY] += aAmount;
                    }
                    curX = nx;
                    curY = ny;
                }
            }
            updateCnt();
        }
    }

    private static void updateCnt() {
        for (int i = 0; i < 4; i++) {
            forwardCnt[i] += 2;
        }
    }

    private static boolean isOut(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }

}