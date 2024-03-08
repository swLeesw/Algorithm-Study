package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
    static int N, M, ans;
    static char[][] map;
    static boolean[][] visited;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());               //세로
        M = Integer.parseInt(st.nextToken());               //가로
        map = new char[N][M];
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[N][M];
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(int x, int y) {
        int cnt = 0;
        Queue<XY> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new XY(x, y));
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            while (qsize-- > 0) {
                XY cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 'W') continue;
                    visited[nx][ny] = true;
                    queue.offer(new XY(nx, ny));
                }
            }
            cnt++;
        }
        ans = Math.max(ans, cnt - 1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
