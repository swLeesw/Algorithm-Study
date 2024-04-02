package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609 {
    static int N, M, map[][];
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static PriorityQueue<Group> pq = new PriorityQueue<>();

    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group implements Comparable<Group> {
        List<XY> members;
        int x, y, rainbowCnt;

        public Group(int x, int y) {
            members = new ArrayList<>();
            this.x = x;
            this.y = y;
            rainbowCnt = 0;
        }

        void add(int x, int y) {
            members.add(new XY(x, y));
            if (map[x][y] == 0) rainbowCnt++;
        }

        @Override
        public int compareTo(Group o) {
            if (this.members.size() != o.members.size())
                return o.members.size() - this.members.size();
            if (rainbowCnt != o.rainbowCnt)
                return o.rainbowCnt - this.rainbowCnt;
            if (x != o.x)
                return o.x - this.x;
            return o.y - this.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());           //격자 한 변의 크기
        M = Integer.parseInt(st.nextToken());           //색깔의 종류
        map = new int[N][N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] != 0 && map[i][j] != -1 && map[i][j] != 6) {
                        bfs(i, j);
                    }
                }
            }
            if (pq.isEmpty()) break;
            Group group = pq.poll();
            if (group.members.size() < 2) break;
            ans += Math.pow(group.members.size(), 2);
            //부수기
            breaking(group);
            //내리기
            fall();

            //반시계 90도
            rotate();

            //내리기
            fall();

            //pq초기화
            pq.clear();
        }
        System.out.println(ans);
    }

    //회전
    private static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N - 1 - i][j] = map[j][i];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    //떨구기
    private static void fall() {
        for (int x = 0; x < N; x++) {
            int bottomIdx = N;
            for (int y = N - 1; y > -1; y--) {
                if (map[y][x] == 6) {
                    if (bottomIdx == N)
                        bottomIdx = y;
                } else if (map[y][x] == -1) {
                    bottomIdx = y - 1;
                } else if (bottomIdx != N) {
                    if (bottomIdx == y) bottomIdx--;
                    else {
                        map[bottomIdx--][x] = map[y][x];
                        map[y][x] = 6;
                    }
                }
            }
        }
    }

    //그룹 부수기
    private static void breaking(Group group) {
        for (XY member : group.members) {
            map[member.x][member.y] = 6;
        }
    }

    //그룹 생성
    private static void bfs(int x, int y) {
        List<XY> rainbow = new ArrayList<>();
        Queue<XY> queue = new LinkedList<>();
        Group group = new Group(x, y);
        group.add(x, y);
        visited[x][y] = true;
        queue.offer(new XY(x, y));
        while (!queue.isEmpty()) {
            XY cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 6 || map[nx][ny] == -1) continue;
                if (map[nx][ny] == map[x][y] || map[nx][ny] == 0) {
                    if (map[nx][ny] == 0) {
                        rainbow.add(new XY(nx, ny));
                    }
                    visited[nx][ny] = true;
                    group.add(nx, ny);
                    queue.offer(new XY(nx, ny));
                }
            }
        }
        backRainbow(rainbow);
        pq.offer(group);
    }

    private static void backRainbow(List<XY> rainbow) {
        for (XY xy : rainbow) {
            visited[xy.x][xy.y] = false;
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}