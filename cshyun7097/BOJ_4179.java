package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int N, M;
    static char[][] map;
    static Queue<XY> fires = new LinkedList<>();
    static Queue<XY> jihoon = new LinkedList<>();
    static class XY{
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'J') {
                    jihoon.offer(new XY(i, j));
                }
                if (map[i][j] == 'F') {
                    fires.offer(new XY(i, j));
                    visited[i][j] = true;
                }
            }
        }
        bfs();
    }
    private static void bfs() {
        int cnt = 0;
        while (!jihoon.isEmpty()) {
            cnt++;
            int jSize = jihoon.size();
            int fireSize = fires.size();
            while (fireSize-- > 0) {
                XY fire = fires.poll();
                int curX = fire.x;
                int curY = fire.y;
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == '#') continue;
                    visited[nx][ny] = true;
                    fires.offer(new XY(nx, ny));
                    map[nx][ny] = 'F';
                }
            }
            while (jSize-- > 0) {
                XY jh = jihoon.poll();
                int curX = jh.x;
                int curY = jh.y;
                if (curX == 0 || curX == N - 1 || curY == 0 || curY == M - 1) {
                    System.out.println(cnt);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == '#') continue;
                    jihoon.offer(new XY(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        System.out.println("IMPOSSIBLE");
        return;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
