package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int H, N, M;
    static char[][][] map;
    static boolean[][][] visited;
    static StringBuilder sb;

    static class XYZ {
        int z, x, y;

        public XYZ(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (H == 0 && N == 0 && M == 0) break;
            map = new char[H][N][M];
            visited = new boolean[H][N][M];
            XYZ start = null;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < N; j++) {
                    String tmp = br.readLine();
                    for (int k = 0; k < M; k++) {
                        map[i][j][k] = tmp.charAt(k);
                        if (map[i][j][k] == 'S') {
                            start = new XYZ(i, j, k);
                        }
                    }
                }
                br.readLine();
            }
            bfs(start);
        }
        System.out.println(sb);
    }

    private static void bfs(XYZ start) {
        Queue<XYZ> queue = new LinkedList<>();
        visited[start.z][start.x][start.y] = true;
        queue.offer(start);
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            while (size-- > 0) {
                XYZ cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                int curZ = cur.z;
                if (map[curZ][curX][curY] == 'E') {
                    sb.append("Escaped in " + (cnt - 1) + " minute(s).").append("\n");
                    return;
                }
                for (int d = 0; d < 6; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    int nz = curZ + dz[d];
                    if (!isIn(nx, ny, nz) || visited[nz][nx][ny] || map[nz][nx][ny] == '#') continue;
                    queue.offer(new XYZ(nz, nx, ny));
                    visited[nz][nx][ny] = true;
                }
            }
        }
        sb.append("Trapped!").append("\n");
    }

    private static boolean isIn(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 && x < N && y < M && z < H;
    }
}
