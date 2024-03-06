package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static int N, M;
    static class Bus{
        int start, end, weight;

        public Bus(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());       //도시 개수
        M = Integer.parseInt(st.nextToken());       //버스 노선 개수
        final int INF = Integer.MAX_VALUE;

        Bus[] buses = new Bus[M + 1];
        long[] minDistance = new long[N + 1];
        Arrays.fill(minDistance, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            buses[i] = new Bus(start, end, weight);
        }
        //
        if (bellman(minDistance, buses, INF)) {
            for (int i = 2; i <= N; i++) {
                sb.append(minDistance[i] != INF ? minDistance[i] : -1).append("\n");
            }
        } else {
            sb.append(-1).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bellman(long[] minDistance, Bus[] buses, int INF) {
        minDistance[1] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Bus bus = buses[j];
                if (minDistance[bus.start] != INF && minDistance[bus.end] > minDistance[bus.start] + bus.weight) {
                    minDistance[bus.end] = minDistance[bus.start] + bus.weight;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            Bus bus = buses[i];
            if (minDistance[bus.start] != INF && minDistance[bus.end] > minDistance[bus.start] + bus.weight) return false;
        }
        return true;
    }
}
