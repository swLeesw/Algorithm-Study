package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {
    static int N, M, L, arr[];
    static Animal[] animals;
    static class Animal{
        int x, y;

        public Animal(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[M];
        animals = new Animal[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i] = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int ans = 0;

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            ans += search(i);
        }
        System.out.println(ans);
    }

    private static int search(int i) {
        int start = 0;
        int end = M;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (mid >= M) return 0;

            int dist = getDistance(animals[i].x, animals[i].y, arr[mid]);
            if (L < dist && animals[i].x < arr[mid]) end = mid - 1;
            else if (L < dist && animals[i].x >= arr[mid]) start = mid + 1;
            else if (L >= dist) return 1;
        }
        return 0;
    }

    private static int getDistance(int x, int y, int i) {
        return Math.abs(i - x) + y;
    }
}
