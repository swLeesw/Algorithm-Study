package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ladderCnt = Integer.parseInt(st.nextToken());
        int snakeCnt = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> ladders = new HashMap<>();
        HashMap<Integer, Integer> snakes = new HashMap<>();

        for (int i = 0; i < ladderCnt; i++) {
            st = new StringTokenizer(br.readLine());
            ladders.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < snakeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            snakes.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bfs(ladders, snakes);
    }

    private static void bfs(HashMap<Integer, Integer> ladders, HashMap<Integer, Integer> snakes) {
        int[] board = new int[101];
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        Arrays.fill(board, 10000);
        queue.offer(1);
        visited[1] = true;
        board[1] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 100) {
                System.out.println(board[100]);
                return;
            }
            for (int i = 1; i <= 6; i++) {
                int next = cur + i;
                if (next > 100 || visited[next]) continue;
                if (snakes.get(next) != null) {
                    board[snakes.get(next)] = Math.min(board[snakes.get(next)], board[cur] + 1);
                    queue.offer(snakes.get(next));
                    visited[snakes.get(next)] = true;
                    continue;
                }
                if (ladders.get(next) != null) {
                    board[ladders.get(next)] = Math.min(board[ladders.get(next)], board[cur] + 1);
                    queue.offer(ladders.get(next));
                    visited[ladders.get(next)] = true;
                    continue;
                }
                queue.offer(next);
                visited[next] = true;
                board[next] = Math.min(board[cur] + 1, board[next]);
            }
        }
    }
}
