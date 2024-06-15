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

    static int n, m, arr[][];
    static boolean visited[][];
    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};

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

        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs() + 1);
    }

    static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if (cur.y == n - 1 && cur.x == m - 1) {
                return cur.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                q.offer(new Pos(ny, nx, cur.cnt + 1));
            }

        }
        return -1;
    }

}
