package algo_sil;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647 {
    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());                   //집의 개수
        int M = Integer.parseInt(st.nextToken());                   //길의 개수

        Edge[] edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList);

        int[] parents = make(N);
        int cnt = 0;
        long weight = 0L;
        long bigWeight = 0;
        for (Edge edge : edgeList) {
            if (!union(edge.from, edge.to, parents)) continue;
            weight += edge.weight;
            bigWeight = edge.weight;
            if (++cnt == N - 1) break;
        }
        bw.write(weight - bigWeight + "\n");
        bw.flush();
        bw.close();
    }

    private static boolean union(int a, int b, int[] parents) {
        int aRoot = find(a, parents);
        int bRoot = find(b, parents);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int a, int[] parents) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a], parents);
    }

    private static int[] make(int n) {
        int[] tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tmp[i] = i;
        }
        return tmp;
    }
}
