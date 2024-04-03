import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17070 {
    static class Pipe {
        //direction : 1 : 가로, 2: 대각, 3: 세로
        int x, y, direction;

        public Pipe(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    //우, 대, 하
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 1, 0};
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());                //집 크기
        int[][] room = new int[N][N];                           //집 구조
        Pipe pipe = new Pipe(0, 1, 1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                room[i][j] = tmp;
            }
        }
        bfs(pipe.x, pipe.y, pipe.direction, N, room);
        System.out.println(cnt);
    }

    private static void bfs(int x, int y, int direction, int N, int[][] room) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, direction});
        while (!queue.isEmpty()) {
            int start = 0, end = 0;
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDir = cur[2];
            if (curX == N - 1 && curY == N - 1) {
                cnt++;
            }
            if (curDir == 1) {
                start = 0;
                end = 1;
            } else if (curDir == 2) {
                start = 0;
                end = 2;
            } else {
                start = 1;
                end = 2;
            }
            for (int i = start; i <= end; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if (isIn(nx, ny, N)  && room[nx][ny] != 1) {
                    if (i == 1) {
                        if (room[nx - 1][ny] == 1 || room[nx][ny - 1] == 1) {
                            continue;
                        }
                    }
                    queue.offer(new int[]{nx, ny, i + 1});
                }
            }
        }
    }

    private static boolean isIn(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}