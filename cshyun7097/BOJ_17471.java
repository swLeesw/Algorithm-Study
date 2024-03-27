package algo_sil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {

    static int N;
    static int peoples[];
    static List<ArrayList<Integer>> arr;
    static boolean selected[];
    static boolean visited[];
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());        // 지역 개수
        ans = Integer.MAX_VALUE;                    // 인구차
        peoples = new int[N];                       // 지역별 인구 수
        selected = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            peoples[i] = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken()); // 주변 구역 수
            for (int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr.get(i).add(num - 1);
            }
        }

        comb(0);
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);

    }

    private static void comb(int idx) {
        if (idx == N) {
            //선거구를 a, b로 나누기
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (selected[i])
                    aList.add(i);
                else
                    bList.add(i);
            }
            if (aList.size() == 0 || bList.size() == 0) // 한 지역으로 모였는지 확인
                return;

            if (isLink(aList) && isLink(bList)) { // 구역끼리 모두 연결되있으면
                getNum(); // 인구차
            }
            return;
        }

        //경우의 수 확인
        selected[idx] = true;
        comb(idx + 1);
        selected[idx] = false;
        comb(idx + 1);

    }

    private static boolean isLink(List<Integer> list) {

        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N];
        visited[list.get(0)] = true;
        q.offer(list.get(0));

        int count = 1; // 방문한 지역 개수
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < arr.get(cur).size(); i++) {
                int y = arr.get(cur).get(i);
                if(list.contains(y) && !visited[y]) {
                    q.offer(y);
                    visited[y] = true;
                    count ++;
                }
            }
        }
        if(count==list.size()) // 선거구에 해당하는 지역수와 방문한 지역수가 같은 경우
            return true;
        else
            return false;
    }


    private static void getNum() {
        // a, b구역 사람수
        int cntA = 0, cntB = 0;
        for (int i = 0; i < N; i++) {
            if (selected[i])
                cntA += peoples[i];
            else
                cntB += peoples[i];
        }
        int diff = Math.abs(cntA - cntB);
        ans = Math.min(ans, diff);
    }

}