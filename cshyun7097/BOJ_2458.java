import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {
    static ArrayList<Integer>[] arrOut;
    static int[] indeg;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        indeg = new int[N + 1];
        arrOut = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arrOut[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrOut[a].add(b);
        }
        for (int i = 1; i <= N; i++) {
            if (arrOut[i].isEmpty()) continue;
            bfs(i);
        }
        for (int i : indeg) {
            if (i == (N - 1)) {
                cnt++;
            }
        }
        sb.append(cnt);
        System.out.println(sb);
    }

    private static void bfs(int idx) {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);
        visited[idx] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < arrOut[cur].size(); i++) {
                int tmp = arrOut[cur].get(i);
                if (!visited[tmp]) {
                    queue.offer(tmp);
                    visited[tmp] = true;
                    cnt++;
                    indeg[tmp]++;
                }
            }
        }
        indeg[idx] += cnt;
    }
}