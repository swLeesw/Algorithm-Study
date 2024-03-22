package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3190 {
    static int N, K, L;
    static int[][] map;
    static List<XY> snake = new ArrayList<>();
    static HashMap<Integer, String> hash = new HashMap<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            hash.put(tmp, str);
        }

        game();
    }

    private static void game() {
        int curX = 0;
        int curY = 0;
        int time = 0;
        int d = 0;
        snake.add(new XY(0, 0));
        while (true) {
            time++;

            int nx = curX + dx[d];
            int ny = curY + dy[d];

            if (isEnd(nx, ny)) break;

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
                snake.add(new XY(nx, ny));
            } else {
                snake.add(new XY(nx, ny));
                snake.remove(0);
            }

            if (hash.get(time) != null) {
                if (hash.get(time).equals("D")) {
                    d = (d + 1) % 4;
                } else {
                    d = d - 1 == -1 ? 3 : d - 1;
                }
            }
            curX = nx;
            curY = ny;
        }
        System.out.println(time);
    }

    public static boolean isEnd(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return true;
        }

        for (int i = 0; i < snake.size(); i++) {
            XY tmp = snake.get(i);
            if (x == tmp.x && y == tmp.y)
                return true;
        }
        return false;
    }
}
