import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_8983 {

	static class Animal implements Comparable<Animal>{
		int x,y,d;

		public Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Animal [x=" + x + ", y=" + y +" , d = "+d+"]";
		}

		@Override
		public int compareTo(Animal o) {
			
			return this.d-o.d;
		}
		
		public void setK(int k) {
			this.d = Math.abs(this.x-k)+this.y;

		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int arr[] = new int[M+1];
		st = new StringTokenizer(br.readLine());
		for(int m=0;m<M;m++) {
			arr[m] = Integer.parseInt(st.nextToken());
		}
		List<Animal> animals = new ArrayList<>();
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			animals.add(new Animal(a, b));
		}
		int cnt = 0;
		for(int m=0;m<M;m++) {
			for(Animal a:animals) {
				a.setK(arr[m]);
			}
			Collections.sort(animals);
			for(int idx=0;idx<animals.size();idx++) {
				if(animals.get(idx).d>L)break;
				animals.remove(idx);
				idx--;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
