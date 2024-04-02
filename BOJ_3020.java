import java.io.*;
import java.util.*;


public class Main {
	
	
	static int n, h, down[], up[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		int[] down = new int[n/2];
		int[] up = new int[n/2];
		
		for (int i = 0; i < n / 2; i++) {
			down[i] = Integer.parseInt(br.readLine());
			up[i] = Integer.parseInt(br.readLine());
		}
		
		
		Arrays.sort(down);
		Arrays.sort(up);
		
		int min = n; //장애물의 최소 개수
		int cnt = 0;
		
		for (int i = 1; i <= h; i++) {
			int conf = binarySearch(0, n/2, i, down) + binarySearch(0, n/2, h-i+1, up);//높이에서 i를 빼주
			
			if (min == conf) {
				cnt++;
			} else if (min > conf) {
				min = conf;
				cnt = 1;
			}
			
		}
		
		System.out.println(min + " " + cnt);
	}
	
	static int binarySearch(int left, int right, int h, int[] arr) {
		int mid;
		if (left >= right) {
			return arr.length - right; //idx가 0부터 시작하는 것을 생각1
		}
		
		mid = (left + right) / 2;
		
		if (arr[mid] >= h) { //가운데의 높이가 같거나 더 크면
			return binarySearch(left, mid, h, arr);
		} else {
			return binarySearch(mid + 1, right, h, arr);
		}
		
		
	}
	
	
}
