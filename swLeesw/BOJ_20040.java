import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (!union(x, y)) {
				System.out.println(i + 1);
				return;
			}
			
		}
		System.out.println(0);
	}
	
	static int find(int x) {
		if (arr[x] == x) return x; //자기 자신 기리킴
		return arr[x] = find(arr[x]);
	}
	
	static boolean union(int x, int y) {
		int tx = find(x);
		int ty = find(y);
		
		if (tx == ty) return false;
		if (tx > ty) {
			arr[tx] = ty;
		} else {
			arr[ty] = tx;
		}
		
		return true;
	}
	
	
}
