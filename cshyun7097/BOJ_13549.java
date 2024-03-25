package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {
    static int N, K, road[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        road = new int[100001];
        System.out.println(dp());
    }

    private static int dp() {
        road[N] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            if (top == K) {
                return road[top] - 1;
            }
            if (2 * top <= 100000 && road[2 * top] == 0) {
                road[2 * top] = road[top];
                queue.add(top * 2);
            }
            if (top - 1 >= 0 && road[top - 1] == 0) {
                road[top - 1] = road[top] + 1;
                queue.add(top - 1);
            }
            if (top + 1 <= 100000 && road[top + 1] == 0) {
                road[top + 1] = road[top] + 1;
                queue.add(top + 1);
            }
        }
        return -1;
    }
}
