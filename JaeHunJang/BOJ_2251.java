import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 물통 - 150분
public class BOJ_2251 {
	static StringBuilder sb = new StringBuilder();
	static List<Basket> list;
	static int A, B, C;
	static class Basket {
		int a, b, c;
		
		public Basket(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Basket [a=" + a + ", b=" + b + ", c=" + c + "]";
		}

		@Override
		public boolean equals(Object obj) {
			Basket other = (Basket) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		solution();
	}
	
	private static void solution() throws Exception {
//		recursive(0, 0, C);
		bfs();
		
		int arr[] = new int[list.size()];
		int idx = 0;
		for (Basket basket : list) {
			arr[idx++] = basket.c;
		}
		Arrays.sort(arr);
		for (int i : arr) {
			sb.append(i).append(" ");
		}
		
//		System.out.println(set.toString());
	}
	
// 12% 실패
//	private static void recursive(int a, int b, int c) {
//		Basket cur = new Basket(a, b, c);
//		if (set.contains(cur)) return;
//		set.add(cur);
////		System.out.println(set.toString());
//		
//		int ta = a, tb = b, tc = c;
//		
//		if (tc >= A) {
//			ta = A;
//			tc = tc - ta;
//		} else {
//			ta = tc; 
//			tc = 0;
//		}
//		if (tb + ta <= B) {
//			tb = tb + ta;
//			ta = 0;
//		} else {
//			if (B-tb == 0) {
//				tc = tc + tb;
//				tb = ta;
//				ta = 0;
//			} else {
//				ta = ta - (B-tb);
//				tb = B;
//				tc += ta;
//				ta = 0;
//			}
//		}
//		
//		recursive(ta, tb, tc);
//		if (tb + tc <= B) {
//			tb = tb + tc;
//			tc = 0;
//		} else {
//			ta = ta + tc;
//			tc = tb;
//			tb = ta;
//			ta = 0;
//		}
//		recursive(ta, tb, tc);
//	}
	
	private static void bfs() {
		List<Basket> visited = new ArrayList<>();
		Queue<Basket> q = new ArrayDeque<>();
		q.offer(new Basket(0, 0, C));
//		Basket basket = new Basket(a, b, c);
//		if (set.contains(basket)) return;
		while(!q.isEmpty()) {
			Basket basket = q.poll();
			int a = basket.a;
			int b = basket.b;
			int c = basket.c;
			if (visited.contains(basket)) continue;
			visited.add(basket);
//		System.out.println(list);
			if (a == 0) list.add(basket);
			
			if (a + b > B) {
				q.offer(new Basket(a + b - B, B, c));
			} else {
				q.offer(new Basket(0, a + b, c));
			}
			
			if (a + c > C) {
				q.offer(new Basket(a + b - C, b, C));
			} else {
				q.offer(new Basket(0, b, a + c));
			}
			
			if (b + a > A) {
				q.offer(new Basket(A, b + a - A, c));
			} else {
				q.offer(new Basket(b + a, 0, c));
			}
			
			if (b + c > C) {
				q.offer(new Basket(a, b + c - C, C));
			} else {
				q.offer(new Basket(a, 0, b + c));
			}
			
			if (c + a > A) {
				q.offer(new Basket(A, b, c + a - A));
			} else {
				q.offer(new Basket(c + a, b, 0));
			}
			
			if (c + b > B) {
				q.offer(new Basket(a, B, c + b - B));
			} else {
				q.offer(new Basket(a, c + b, 0));
			}
		}
		
	}
}