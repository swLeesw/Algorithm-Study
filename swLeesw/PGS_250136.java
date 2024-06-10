import java.util.*;


class Solution {
    
    static class Pos {
        int y, x;
        
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static int n, m, land[][], sol, connected[][], cnt;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static List<Integer> connectedSize;
    
    public int solution(int[][] _land) {
        int answer = 0;
        land = _land;
        n = land.length;
        m = land[0].length;
        connected = new int[n][m];
        connectedSize = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && connected[i][j] == 0) { //석유 위치 && 탐색 안한 석유
                    cnt++;
                    getConnected(i, j);
                }
            }
        }
        
        for (int i = 0; i < m ; i++) {
            answer = Math.max(answer, getOil(i));
        }
        
        return answer;
    }
    
    static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < m;
    }
    
    static void getConnected(int sy, int sx) {
        int sum = 1;
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(sy, sx));
        connected[sy][sx] = cnt;
        
        
        while (!que.isEmpty()) {
            Pos cur = que.poll();
            
            
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                
                if (!isRange(ny ,nx) || connected[ny][nx] != 0 || land[ny][nx] == 0) continue;
                
                que.offer(new Pos(ny, nx));
                connected[ny][nx] = cnt;
                sum++;
            }
        }
        
        connectedSize.add(sum);   
    }
    
    static int getOil(int x) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (connected[i][x] != 0) {
                set.add(connected[i][x]);
            }
        }
        
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            sum += connectedSize.get(iter.next() - 1);
        }
        
        return sum;
    }
}
