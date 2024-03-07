package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4014 {
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int airStripSize = Integer.parseInt(st.nextToken());             //지형 크기
            int length = Integer.parseInt(st.nextToken());                  //내리막 길이
            int[][] airStrip = new int[airStripSize][airStripSize];         //활주로 지형
            int[][] reverseAirStrip = new int[airStripSize][airStripSize];         //활주로 지형 세로버전

            for (int i = 0; i < airStripSize; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < airStripSize; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    airStrip[i][j] = tmp;
                    reverseAirStrip[j][i] = tmp;
                }
            }

            ans = 0;
            getAns(airStripSize, airStrip, length);
            getAns(airStripSize, reverseAirStrip, length);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void getAns(int airStripSize, int[][] airStrip, int length) {
        for (int i = 0; i < airStripSize; i++) {
            int distance = 0;
            boolean isAble = true;
            for (int j = 0; j < airStripSize - 1; j++) {
                distance++;
                if (airStrip[i][j] == airStrip[i][j + 1]) continue;
                else if (airStrip[i][j] > airStrip[i][j + 1]) {             //내리막이면
                    if (Math.abs(airStrip[i][j] - airStrip[i][j + 1]) != 1) {
                        isAble = false;
                        break;
                    }
                    for (int k = j + 1; k < j + length + 1; k++) {
                        if (k >= airStripSize || airStrip[i][k] != airStrip[i][j + 1]) {
                            isAble = false;
                            break;
                        }
                    }
                    j = j + length - 1;
                    distance = -1;
                } else if (airStrip[i][j] < airStrip[i][j + 1]) {           //오르막이면
                    if (Math.abs(airStrip[i][j] - airStrip[i][j + 1]) != 1) {
                        isAble = false;
                        break;
                    }
                    if (distance < length) {
                        isAble = false;
                        break;
                    }
                    for (int k = j; k > j - length; k--) {
                        if (k < 0 || airStrip[i][k] != airStrip[i][j]) {
                            isAble = false;
                            break;
                        }
                    }
                    distance = 0;
                }
            }
            if (isAble) {
                ans++;
            }
        }
    }
}
