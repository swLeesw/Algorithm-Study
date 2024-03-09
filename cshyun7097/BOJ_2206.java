package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    static class XY{
        int x, y;
        boolean isbreak;

        public XY(int x, int y, boolean isbreak) {
            this.x = x;
            this.y = y;
            this.isbreak = isbreak;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        if (row == 1 && col == 1) {
            System.out.println(1);
            System.exit(0);
        }

        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
        bfs(0, 0, map, row, col);
    }

    private static void bfs(int x, int y, int[][] map, int row, int col) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        boolean[][][] visited = new boolean[2][row][col];
        int cnt = 0;
        Queue<XY> queue = new LinkedList<>();
        queue.offer(new XY(x, y, false));
        visited[0][x][y] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            while (size-- > 0) {
                XY cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                boolean isBreak = cur.isbreak;
                if (curX == row - 1 && curY == col - 1) {
                    System.out.println(cnt);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny, row, col)) continue;
                    if (!isBreak) {
                        if (visited[0][nx][ny]) continue;
                        if (map[nx][ny] == 1) {
                            visited[1][nx][ny] = true;
                            queue.offer(new XY(nx, ny, true));
                        } else {
                            visited[0][nx][ny] = true;
                            queue.offer(new XY(nx, ny, false));
                        }
                    } else {
                        if(visited[1][nx][ny] || map[nx][ny] == 1) continue;
                        visited[1][nx][ny] = true;
                        queue.offer(new XY(nx, ny, true));
                    }
                }
            }
        }
        System.out.println(-1);
        return;
    }

    private static boolean isIn(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
