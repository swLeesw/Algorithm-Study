import java.util.*;

class Solution {
    
    static class Pos {
        int y, x;
        
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        public boolean isEqual(Pos cmp) {
            return this.y == cmp.y && this.x == cmp.x;
        }
        
    }
    
    static int maze[][], n, m, visited[][][], answer = Integer.MAX_VALUE;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Pos rs, bs, re, be;

    public int solution(int[][] _maze) {

        maze = _maze;
        n = maze.length;
        m = maze[0].length;
        visited = new int[n][m][2];
        
        getPos();
        dfs(rs, bs, 1);
        return answer == Integer.MAX_VALUE ? 0 : answer - 1;
    }
    
    //시작 끝 좌표 구하는 함수
    static void getPos() {
        for (int i = 0; i < n ;i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    rs = new Pos(i, j);
                } else if (maze[i][j] == 2) {
                    bs = new Pos(i, j);
                } else if (maze[i][j] == 3) {
                    re = new Pos(i, j);
                } else if (maze[i][j] == 4) {
                    be = new Pos(i, j);
                }
            }
        }
    }
    
    static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < m;
    }
    
    static void dfs(Pos rPos, Pos bPos, int cnt) {
        if ((rPos.isEqual(re)) && (bPos.isEqual(be))) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        visited[rPos.y][rPos.x][0] = cnt;
        visited[bPos.y][bPos.x][1] = cnt;

        
        if (!(rPos.isEqual(re)) && !(bPos.isEqual(be))) {//둘 다 아님
            for (int i = 0; i < 4; i++) {
                int rny = rPos.y + dy[i];
                int rnx = rPos.x + dx[i];
                for (int j = 0; j < 4; j++) {
                    int bny = bPos.y + dy[j];
                    int bnx = bPos.x + dx[j];

                    //같은 좌표, 범위 밖, 벽, 이미 방문, 서로 겹칩 
                    if ((rny == bny && rnx == bnx) || !isRange(rny, rnx) || !isRange(bny, bnx)
                        || maze[rny][rnx] == 5 || maze[bny][bnx] == 5 
                        || visited[rny][rnx][0] != 0 || visited[bny][bnx][1] != 0
                        || ((rny == bPos.y && rnx == bPos.x) && (rPos.y == bny && rPos.x == bnx))) continue;

                    dfs(new Pos(rny, rnx), new Pos(bny, bnx), cnt + 1);

                }
            }
        } else if ((rPos.isEqual(re)) && !(bPos.isEqual(be))) { //red완료
            
            for (int i = 0; i < 4; i++) {
                int bny = bPos.y + dy[i];
                int bnx = bPos.x + dx[i];
                
                //같은 좌표, 범위 밖, 벽, 이미 방문
                if ((rPos.y == bny && rPos.x == bnx) || !isRange(bny, bnx)
                    || maze[bny][bnx] == 5
                    || visited[bny][bnx][1] != 0) continue;
                
                dfs(rPos, new Pos(bny, bnx), cnt + 1);
                
            }
            
        } else if (!(rPos.isEqual(re)) && (bPos.isEqual(be))) { //blue완료
            
            for (int i = 0; i < 4; i++) {
                int rny = rPos.y + dy[i];
                int rnx = rPos.x + dx[i];
                
                //같은 좌표, 범위 밖, 벽, 이미 방문
                if ((bPos.y == rny && bPos.x == rnx) || !isRange(rny, rnx)
                    || maze[rny][rnx] == 5
                    || visited[rny][rnx][0] != 0) continue;
                
                dfs(new Pos(rny, rnx), bPos, cnt + 1);
                
            }
            
        }
        visited[rPos.y][rPos.x][0] = 0;
        visited[bPos.y][bPos.x][1] = 0;
    }
}
