import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static int R, C;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<XY> water = new LinkedList<>();

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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (map[i][j] == '*') {
                    water.offer(new XY(i, j));
                }
            }
        }
        int ans = bfs(startX, startY);
        System.out.println(ans == -1 ? "KAKTUS" : ans - 1);
    }

    private static int bfs(int x, int y) {
        boolean[][] visited = new boolean[R][C];
        Queue<XY> queue = new LinkedList<>();
        queue.offer(new XY(x, y));
        visited[x][y] = true;
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int waterSize = water.size();
            time++;
            while (waterSize-- > 0) {
                XY cur = water.poll();
                int curX = cur.x;
                int curY = cur.y;
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
                    water.offer(new XY(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = '*';
                }
            }
            while (size-- > 0) {
                XY cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                if (map[curX][curY] == 'D') {
                    return time;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
//                    map[curX][curY] = '.';
//                    map[nx][ny] = 'S';
                    queue.offer(new XY(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}