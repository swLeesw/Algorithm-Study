package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());   //x좌표
            int y = Integer.parseInt(st.nextToken());   //y좌표
            int d = Integer.parseInt(st.nextToken());   //방향
            int g = Integer.parseInt(st.nextToken());   //세대
            dragonCurve(x, y, d, g);
        }
        System.out.println(getCnt());
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> arr = new ArrayList<>();
        arr.add(d);
        for (int i = 0; i < g; i++) {
            for (int j = arr.size() - 1; j >= 0; j--) {
                int tmp = arr.get(j);
                arr.add((tmp + 1) % 4);
            }
        }
        map[y][x] = true;
        for (int i = 0; i < arr.size(); i++) {
            int nx = x + dx[arr.get(i)];
            int ny = y + dy[arr.get(i)];
            map[ny][nx] = true;
            x = nx;
            y = ny;
        }
    }
}
