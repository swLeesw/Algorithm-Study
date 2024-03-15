package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {
    static class Node {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class PqFormat implements Comparable<PqFormat> {
        int index, dist;

        PqFormat(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(PqFormat o) {
            // dist 기준 오름차순 정렬
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[N + 1];
        int[] minDistance = new int[N + 1];
        final int INF = Integer.MAX_VALUE;
        Arrays.fill(minDistance, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[from].add(new Node(to, weight));
            arr[to].add(new Node(from, weight));
        }
        //pq 선언
        PriorityQueue<PqFormat> pq = new PriorityQueue<>();
        minDistance[1] = 0;
        pq.offer(new PqFormat(1, 0));
        while (!pq.isEmpty()) {
            PqFormat cur = pq.poll();
            if (visited[cur.index] || arr[cur.index] == null) continue;
            for (Node node : arr[cur.index]) {
                if (minDistance[node.to] > minDistance[cur.index] + node.weight) {
                    minDistance[node.to] = minDistance[cur.index] + node.weight;
                    pq.add(new PqFormat(node.to, minDistance[node.to]));
                }
            }
        }
        System.out.println(minDistance[N]);
    }
}
