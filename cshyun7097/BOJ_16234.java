package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234 {
    static int N, L, R, map[][];
    static ArrayList<XY> arr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;

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
        N = Integer.parseInt(st.nextToken());   //칸의 개수
        L = Integer.parseInt(st.nextToken());   //L이상
        R = Integer.parseInt(st.nextToken());   //R이하 병합 가능
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(go());
    }

    private static int go() {
        int cnt = 0;
        while (true) {
            boolean canGo = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);
                        if (arr.size() > 1) {
                            canGo = true;
                            movePeople(sum);
                        }
                    }
                }
            }
            if (!canGo) return cnt;
            cnt++;
        }
    }

    private static void movePeople(int sum) {
        int avg = sum / arr.size();
        for (XY xy : arr) {
            map[xy.x][xy.y] = avg;
        }
    }

    private static int bfs(int x, int y) {
        Queue<XY> queue = new LinkedList<>();
        arr = new ArrayList<>();
        queue.offer(new XY(x, y));
        arr.add(new XY(x, y));
        visited[x][y] = true;
        int sum = map[x][y];
        while (!queue.isEmpty()) {
            XY cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx, ny)) continue;
                int difference = Math.abs(map[curX][curY] - map[nx][ny]);
                if (L <= difference && difference <= R && !visited[nx][ny]) {
                    sum += map[nx][ny];
                    arr.add(new XY(nx, ny));
                    queue.offer(new XY(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return sum;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
