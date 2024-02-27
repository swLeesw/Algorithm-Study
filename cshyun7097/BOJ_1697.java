package algo_sil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();           //수빈이 위치
        int K = sc.nextInt();           //동생 위치

        int[] street = new int[100001];

        int res = dp(N, K, street);

        System.out.println(res);

    }

    private static int dp(int n, int K, int[] road) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(n);

        int idx = n;
        road[idx] = 1;

        while (!q.isEmpty()) {
            int top = q.poll();
            if (top == K) {
                return road[top] - 1;
            }
            if (top - 1 >= 0 && road[top - 1] == 0) {
                road[top - 1] = road[top] + 1;
                q.offer(top - 1);
            }
            if (top + 1 <= 100000 && road[top + 1] == 0) {
                road[top+1] = road[top] + 1;
                q.offer(top + 1);
            }
            if (2 * top <= 100000 && road[2 * top] == 0) {
                road[2 * top] = road[top] + 1;
                q.offer(2 * top);
            }
        }
        return  -1;
    }
}