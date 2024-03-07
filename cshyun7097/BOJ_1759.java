package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_최승현 {
    static int L, C, combi[];
    static StringBuilder sb;
    static char[] alpha;
    private static void comb(int cnt, int start) {
        if (cnt == L) {
            //조합이 만들어지면
            String tmp = "";
            for (int i = 0; i < combi.length; i++) {
                tmp += alpha[combi[i]];             //조합된 문자 생성
            }
            if (isPass(tmp)) {                      //암호로 충족되면
                sb.append(tmp).append("\n");        //출력
            }
            return;
        }
        for (int i = start; i < C; i++) {
            combi[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    //모음 최소 1개, 자음 최소2개를 충족하는지 검사
    private static boolean isPass(String tmp) {
        int mo = 0;             //모음 개수
        int ja = 0;             //자음 개수
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == 'a' || tmp.charAt(i) == 'e' || tmp.charAt(i) == 'i' || tmp.charAt(i) == 'o' || tmp.charAt(i) == 'u') {
                mo++;
            } else {
                ja++;
            }
        }
        if (mo > 0 && ja > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());               //암호의 알파벳 개수
        C = Integer.parseInt(st.nextToken());               //알파벳 총 개수
        combi = new int[L];                                 //조합 배열생성

        alpha = new char[C];                                //알파벳 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpha);                                 //정렬순으로 조합하기 위해 정렬 수행
        comb(0, 0);                               //조합
        System.out.println(sb);
    }
}
