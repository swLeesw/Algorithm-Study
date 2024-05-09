import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] square;
    static int n, m;
    static long maxValue = -1;

    static long getSum(int startY, int endY, int startX, int endX) {
        long sum = 0;

        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                sum += square[i][j];
            }
        }
        return sum;
    }

    static long solution() {
        long first, second, third;
        // 1번
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                first = getSum(0, i - 1, 0, m - 1);
                second = getSum(i, j - 1, 0, m - 1);
                third = getSum(j, n - 1, 0, m - 1);
                maxValue = Math.max(maxValue, first * second * third);
            }
        }

        // 2번
        for (int i = 1; i <= m - 2; i++) {
            for (int j = i + 1; j <= m - 1; j++) {
                first = getSum(0, n - 1, 0, i - 1);
                second = getSum(0, n - 1, i, j - 1);
                third = getSum(0, n - 1, j, m - 1);
                maxValue = Math.max(maxValue, first * second * third);
            }
        }

        // 3번, 4번, 5번, 6번은 동일하게 작성

        return maxValue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        square = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                square[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(solution());
    }
}
