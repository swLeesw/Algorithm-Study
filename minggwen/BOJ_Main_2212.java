import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_2212 {

	static int min = Integer.MAX_VALUE;
	static int N,K;
	static int[] centers;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		if(K>=N) {
			System.out.println(0);
			System.exit(0);
		}
		centers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			centers[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(centers);
		int diff[] = new int[N];
		for(int idx=0;idx<N-1;idx++) {
			diff[idx]=centers[idx+1]-centers[idx];
		}
		int sum = 0;
		Arrays.sort(diff);
		for(int idx=0;idx<N-K+1;idx++) {
			sum+=diff[idx];
		}
		System.out.println(sum);
	}
}
