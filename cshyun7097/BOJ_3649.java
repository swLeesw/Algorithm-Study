package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = null;
        while ((tmp = br.readLine()) != null) {
            int width = Integer.parseInt(tmp) * 10_000_000;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
            int left = 0;
            int right = n - 1;
            boolean isNone = false;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == width) {
                    System.out.println("yes " + arr[left] + " " + arr[right]);
                    isNone = true;
                    break;
                } else if (sum < width) {
                    left++;
                } else {
                    right--;
                }
            }
            if (!isNone) {
                System.out.println("danger");
            }
            tmp = null;
        }
    }
}
