import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_15685 {

	static int[][] delta = {{0,1},{-1,0},{0,-1},{1,0}};
	static boolean[][] map = new boolean[101][101];
	static List<Integer> move = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t =0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			move.clear();
			move.add(d);
			curve(gen);
			map[row][col] = true;
			for(int m:move) {
				row+=delta[m][0];
				col+=delta[m][1];
				map[row][col] = true;
			}
		}
		
		int square = 0;
		for(int r=0;r<100;r++) {
			for(int c=0;c<100;c++) {
				if(map[r][c]&&map[r+1][c]&&map[r][c+1]&&map[r+1][c+1])square++;
			}
		}
		System.out.println(square);
	}
	private static void curve(int gen) {
		if(gen==0) {
			return;
		}
		int size = move.size();
		for(int s=size-1;s>=0;s--) {
			move.add((move.get(s)+1)%4);
		}
		curve(gen-1);
	}
}
