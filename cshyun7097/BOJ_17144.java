package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_17144 {
    static int R, C, T;
    static int[][] map;
    static int[] up = {1, -1, 0, 0}, side = {0, 0, 1, -1};
    static int airPos1, airPos2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);
        T = Integer.parseInt(s[2]);

        map = new int[R][C];
        int num = 0;
        for (int i = 0; i < R; i++) {
            String[] s1 = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(s1[j]);
            }
        }

        findAir();
        for (int i = 0; i < T; i++) {
            map = dust();
            air();
        }
        int result = getCnt();

        System.out.println(result + 2);
    }

    public static void findAir() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                airPos1 = i;
                airPos2 = i + 1;
                break;
            }
        }
    }

    public static void air() {
        int top = airPos1;
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
        map[top][1] = 0;
        int bottom = airPos2;
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
        map[bottom][1] = 0;
    }

    public static int[][] dust() {
        int[][] tmp = new int[50][50];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    tmp[i][j] = -1;
                    continue;
                }
                tmp[i][j] += map[i][j];
                for (int d = 0; d < 4; d++) {
                    int nx = j + side[d];
                    int ny = i + up[d];

                    if (isOut(ny, nx)) continue;
                    if (map[ny][nx] == -1) continue;

                    tmp[ny][nx] += (map[i][j] / 5);
                    tmp[i][j] -= (map[i][j] / 5);
                }
            }
        }
        return tmp;
    }

    private static boolean isOut(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }

    public static int getCnt() {
        int tmp = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp += map[i][j];
            }
        }
        return tmp;
    }
}
