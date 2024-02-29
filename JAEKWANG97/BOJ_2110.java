package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        // 각각의 집의 좌표가 주어짐
        // 집에 공유기 C개를 설치해야함
        // 최대한 많은 곳에서 와이파이 사용 --> 한집에는 공유기 하나
        // 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치
        // C개의 공유기를 N개의 집에 적당히 설치, 가장 인접한 두 공유기 사이의 거리를 최대호 하는 프로그램
        // N 집의 개수 , C 공유기 개수
        // 집의 좌표
        //5 3
        //1
        //2
        //8
        //4
        //9
        // 공유기를 1,4,8 또는 1,4,9,에 설치하면 가장 인접한 두 공유기의 사이의 거리는 3임
        // 이 거리보다 크게 공유기를 3개 설치할 수 없음
        // 1 2 4 8 9
        // 최대한 사이 범위 가 큰 C개를 고르는 작업
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        int max;
        int min;

        for (int i = 0; i < n; i++) {
            int item = Integer.parseInt(br.readLine());
            list.add(item);
        }

        Collections.sort(list);
        max = list.getLast();
        min = list.getFirst();
        int answer = Integer.MAX_VALUE;
        // 1 2 4 8 9 

        System.out.println(answer);
    }
}
