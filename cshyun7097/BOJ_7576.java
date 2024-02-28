package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());                       //열
        N = Integer.parseInt(st.nextToken());                       //행
        int[][] tomato = new int[N][M];                             //토마토 상자
        Queue<int[]> queue = new LinkedList<>();                    //bfs용 큐
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                tomato[i][j] = tmp;
                if (tmp == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        bfs(tomato, queue, new boolean[N][M]);
    }

    private static void bfs(int[][] tomato, Queue<int[]> queue, boolean[][] visited) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && !visited[nx][ny] && tomato[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    tomato[nx][ny] = tomato[x][y] + 1;
                }
            }
        }
        int res = check(tomato);
        System.out.println(res - 1);
    }

    private static int check(int[][] tomato) {
        int cnt = 0;
        for (int[] tom : tomato) {
            for (int i : tom) {
                if (i == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
                cnt = Math.max(cnt, i);
            }
        }
        return cnt;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
