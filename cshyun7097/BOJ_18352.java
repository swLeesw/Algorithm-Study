package algo_sil;

import java.io.*;
import java.util.*;

public class BOJ_18352 {
    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    static class PqFormat implements Comparable<PqFormat>{
        int index, dist;
        PqFormat(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(PqFormat o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());                   //도시의 개수
        int M = Integer.parseInt(st.nextToken());                   //도로의 개수
        int K = Integer.parseInt(st.nextToken());                   //거리 정보
        int X = Integer.parseInt(st.nextToken());                   //출발 도시 번호
        final int INF = Integer.MAX_VALUE;
        int[] minDistance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        ArrayList<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = 1;
            nodes[from].add(new Node(to, weight));
        }
        PriorityQueue<PqFormat> pq = new PriorityQueue<>();
        Arrays.fill(minDistance, INF);
        minDistance[X] = 0;
        pq.offer(new PqFormat(X, 0));
        while (!pq.isEmpty()) {
            PqFormat cur = pq.poll();
            if (visited[cur.index]) continue;
            for (Node node : nodes[cur.index]) {
                if (minDistance[node.vertex] > minDistance[cur.index] + node.weight) {
                    minDistance[node.vertex] = minDistance[cur.index] + node.weight;
                    pq.add(new PqFormat(node.vertex, minDistance[node.vertex]));
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < minDistance.length; i++) {
            if (minDistance[i] == K) {
                cnt++;
                sb.append(i).append("\n");
            }
        }
        if (cnt == 0) sb.append(-1);
        System.out.println(sb);
    }
}
