package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1931 {
    static class Meeting implements Comparable<Meeting> {
        int start, end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end != o.end ? this.end - o.end : this.start - o.start;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings);

        // 회의 선택의 최대로 하고 선택된 회의들의 내용을 출력
        List<Meeting> list = new ArrayList<>();
        list.add(meetings[0]); // 첫 회의 선택
        for (int i = 1; i < N; i++) { // 고려하는 회의
            if (list.get(list.size() - 1).end <= meetings[i].start) {
                list.add(meetings[i]);
            }
        }
//		System.out.println(list.toString());
        System.out.println(list.size());
    }
}