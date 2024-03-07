package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=N ; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    union(i, j, parents);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()), parents);
        for (int i = 1; i < M; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (start != find(now, parents)) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }

    private static int find(int a, int[] parents) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a], parents);
    }

    private static void union(int a, int b, int[] parents) {
        int aRoot = find(a, parents);
        int bRoot = find(b, parents);
        if (aRoot != bRoot) {
            if (aRoot < bRoot) parents[bRoot] = aRoot;
            else parents[aRoot] = bRoot;
        }
    }
}
