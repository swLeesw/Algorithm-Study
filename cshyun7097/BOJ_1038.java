package algo_sil;

import java.util.*;

public class BOJ_1038 {

    static ArrayList<Long> arr;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        arr = new ArrayList<>();

        if(N <= 10) System.out.println(N);
        else if(N > 1022) System.out.println("-1");
        else {
            for(int i = 0; i < 10; i++) {
                getNum(i, 1);
            }
            Collections.sort(arr);
            System.out.println(arr.get(N));
        }
    }

    public static void getNum(long num, int idx) {
        if(idx > 10) return;

        arr.add(num);
        for(int i = 0; i < num % 10; i++) {
            getNum((num * 10) + i, idx + 1);
        }
    }
}
