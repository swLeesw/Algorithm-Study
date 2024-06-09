import java.util.*;

class Solution {
    
    static int n, wires[][];
    static List<Integer> graph[];
    static boolean visited[];
    static int sol = 210000000;
    
    public int solution(int _n, int[][] _wires) {
        n = _n;
        wires = _wires;
        
        graph = new List[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        //make graph
        for (int i = 0; i < wires.length ; i++) { 
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
        
        for (int i = 0; i < wires.length; i++) {
            bfs(wires[i]);   
        }
        
        return sol;
    }
    
    static void bfs(int wire[]) {
        int cnt = 0;
        visited = new boolean[n + 1];
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);
        visited[1] = true;
        cnt++;
        while (!que.isEmpty()) {
            int cur = que.poll();
            
            for (int next : graph[cur]) {
                
                if (!visited[next]) {
                    
                    if ((wire[0] == cur && wire[1] == next) || (wire[1] == cur && wire[0] == next)) continue;
                    
                    visited[next] = true;
                    que.offer(next);
                    cnt++;
                }
            }    
        }
        sol = Math.min(sol, Math.abs((n - cnt) - cnt));
        
    }
    
}
