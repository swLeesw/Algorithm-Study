package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SEA_1949 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int max;
    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "XY{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());

            int size = Integer.parseInt(st.nextToken());            //지형 크기
            int canDel = Integer.parseInt(st.nextToken());          //공사가능 크기
            int[][] mountain = new int[size][size];                 //산 지형배열
            ArrayList<XY> peaks = new ArrayList<>();                //봉우리 배열
            visited = new boolean[size][size];

            int high = 0;

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    mountain[i][j] = Integer.parseInt(st.nextToken());
                    if (mountain[i][j] > high) {
                        peaks.clear();
                        high = mountain[i][j];
                        peaks.add(new XY(i, j));
                    } else if (mountain[i][j] == high) {
                        peaks.add(new XY(i, j));
                    }
                }
            }
            max = Integer.MIN_VALUE;
            for (XY peak : peaks) {
                visited[peak.x][peak.y] = true;
                dfs(peak.x, peak.y, 1, mountain, canDel);
                visited[peak.x][peak.y] = false;
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
    private static void dfs(int x, int y, int cnt, int[][] mountain, int canDel) {
        max = Math.max(cnt, max);
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!isIn(nx, ny, mountain.length) || visited[nx][ny]) continue;
            if (canDel == 0) {
                if (mountain[x][y] > mountain[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, cnt + 1, mountain, canDel);
                    visited[nx][ny] = false;
                }
            } else {
                if (mountain[x][y] > mountain[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, cnt + 1, mountain, canDel);
                    visited[nx][ny] = false;
                } else {
                    if (mountain[nx][ny] - canDel < mountain[x][y]) {
                        visited[nx][ny] = true;
                        int[][] copyMountain = copyMap(mountain);
                        copyMountain[nx][ny] = copyMountain[x][y] - 1;
                        dfs(nx, ny, cnt + 1, copyMountain, 0);
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }
    private static int[][] copyMap(int[][] mountain) {
        int size = mountain.length;
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = mountain[i][j];
            }
        }
        return tmp;
    }
    private static boolean isIn(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}