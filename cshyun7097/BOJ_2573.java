package algo_sil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_2573 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N, M;
    static int[][] map;

    static class XY {
        int x;
        int y;

        XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        int cnt = 0;
        while ((cnt = getCmt()) < 2) {
            if (cnt == 0) {
                ans = 0;
                break;
            }
            bfs();
            ans++;
        }
        System.out.println(ans);
    }
    public static int getCmt() {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    DFS(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static void DFS(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOut(nx, ny)) continue;
            if (map[nx][ny] != 0 && !visited[nx][ny]) {
                DFS(nx, ny, visited);
            }
        }
    }
    private static boolean isOut(int dx, int dy) {
        return dx < 0 || dy < 0 || dx >= N || dy >= M;
    }

    public static void bfs() {
        Queue<XY> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    q.offer(new XY(i, j));
                    visited[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()) {
            XY ice = q.poll();

            int cnt = 0;

            for (int i = 0; i < 4; i++) {
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if (isOut(nx, ny)) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == 0) {
                    cnt++;
                }
            }
            if (map[ice.x][ice.y] - cnt < 0) map[ice.x][ice.y] = 0;
            else map[ice.x][ice.y] -= cnt;
        }
    }
}
