package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_2251 {
    static Set<Integer> res = new TreeSet<>();
    static int aSize, bSize, cSize;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        visited = new boolean[201][201];

        aSize = Integer.parseInt(st.nextToken());
        bSize = Integer.parseInt(st.nextToken());
        cSize = Integer.parseInt(st.nextToken());

        //하노이탑처럼
        move(0, 0, cSize);
        for (Integer i : res) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void move(int a, int b, int c) {
        if (a == 0) {
            res.add(c);
        }
        if (visited[a][b]) return;
        visited[a][b] = true;
        // 0 -> 1
        if (a + b > bSize) {
            move(a + b - bSize, bSize, c);
        } else {
            move(0, a + b, c);
        }
        // 0 -> 2
        if (a + c > cSize) {
            move(a + c - cSize, b, cSize);
        } else {
            move(0, b, a + c);
        }
        // 1 -> 2
        if (b + c > cSize) {
            move(a, b + c - cSize, cSize);
        } else {
            move(a, 0, b + c);
        }
        // 1 -> 0
        if (a + b > aSize) {
            move(aSize, a + b - aSize, c);
        } else {
            move(a + b, 0, c);
        }
        // 2 -> 1
        if (b + c > bSize) {
            move(a, bSize, b + c - bSize);
        } else {
            move(a, b + c, 0);
        }
        // 2 -> 0
        if (a + c > aSize) {
            move(aSize, b, a + c - aSize);
        } else {
            move(a + c, b, 0);
        }
    }
}
