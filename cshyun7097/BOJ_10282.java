package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {
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
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            ArrayList<Node>[] arr = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                arr[i] = new ArrayList<>();
            }
            boolean[] visited = new boolean[n + 1];
            int[] minDistance = new int[n + 1];
            final int INF = Integer.MAX_VALUE;
            Arrays.fill(minDistance, INF);
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                arr[from].add(new Node(to, weight));
            }
            PriorityQueue<PqFormat> pq = new PriorityQueue<>();
            minDistance[s] = 0;
            pq.offer(new PqFormat(s, 0));
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
            int cnt = 0;
            int max = -1;
            for (int i = 1; i < minDistance.length; i++) {
                if (minDistance[i] != INF) {
                    cnt++;
                    if (minDistance[i] > max)
                        max = minDistance[i];
                }
            }
            System.out.println(cnt + " " + max);
        }
    }
}
