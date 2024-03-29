import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_3020 {

	static int N,H;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int[] top = new int[N/2];
		int[] bottom = new int[N/2];
		for(int n=0;n<N/2;n++) {
			int num1 = Integer.parseInt(br.readLine());
			int num2 = Integer.parseInt(br.readLine());
			bottom[n] = num1;
			top[n] = num2;
		}
		Arrays.sort(top);
		Arrays.sort(bottom);
		
		int min = Integer.MAX_VALUE;
		int cnt = 1;
		
		for(int h=0;h<H;h++) {
			int destroy = calc(top,h)+calc(bottom,H-h+1);
			if(destroy<min) {
				min = destroy;
				cnt = 1;
			}else if(min==destroy)cnt++;
		}
		System.out.println(min+" "+cnt);
	}
	private static int calc(int[] arr,int h) {
		int left = 0;
		int right = N/2;
		while(left<right) {
			int mid = (left+right)/2;
			if(arr[mid]>=h) right = mid;
			else left = mid+1;
		}
		return arr.length-right;
	}

}
