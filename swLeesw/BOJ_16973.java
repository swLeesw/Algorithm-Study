import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{

    static class Pos {
        int y, x, cnt;

        public Pos(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static int n, m, h, w, sy, sx, ey, ex, arr[][];
    static boolean visited[][];
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};

    static boolean isRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());


    }

    static int bfs() {
        Queue<Pos> que = new ArrayDeque<>();
        visited[sy][sx] = true;
        que.offer(new Pos(sy, sx, 0));

        while (!que.isEmpty()) {
            Pos cur = que.poll();
            if (cur.x == ex && cur.y == ey) {
                return cur.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                //범위 밖
                if (!isRange(ny, nx) || !isRange(ny + h - 1, nx + w - 1)
                    || visited[ny][nx]) continue;

                boolean blockCheck = false;

                for (int r = ny; r < ny + h; r++) {
                    if (blockCheck) break;
                    for (int c = nx; c < nx + w; c++) {
                        if (arr[r][c] == 1) {
                            blockCheck = true;
                            break;
                        }
                    }
                }
                if (blockCheck) continue;

                visited[ny][nx] = true;
                que.offer(new Pos(ny, nx, cur.cnt + 1));

            }


        }
        return -1;
    }


}
