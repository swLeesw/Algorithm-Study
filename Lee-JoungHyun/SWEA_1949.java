import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			answer = 1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int maxHight = 0;
			int topCnt = 0;
			// 가장 높은 곳의 y, x 좌표
			int[][] top = new int[5][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {	
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int tmp = map[i][j];
					if (tmp > maxHight) {
						maxHight = tmp;
						topCnt = 1;
						top[0][0] = i;
						top[0][1] = j;
					} else if (tmp == maxHight) {
						top[topCnt][0] = i;
						top[topCnt++][1] = j;
					}
				}
			}
//			for (int i = 0; i < topCnt; i++)			
//				System.out.print(Arrays.toString(top[i]));
//			System.out.println();
			int answer = find(N, Arrays.copyOf(top, topCnt));
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static int find(int N, int[][] top) {
		//1. top별로 BFS 진행하며 max등산로 찾아주기!
		for (int i = 0; i < top.length; i++) {
			DFS(top[i][0], top[i][1], false, 1);
		}	
		return answer;
	}

	private static class Poz {
		public int y, x, hight, cutCnt;

		public Poz(int y, int x, int hight ,int cutCnt) {
			this.y = y;
			this.x = x;
			this.hight = hight;
			this.cutCnt = cutCnt;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return y + ", " + x + " = hight: " + hight + " cutCnt: " + cutCnt;
		}
		
	}
	static int answer, map[][], K, N;
	private static void DFS(int y, int x, boolean cutFlag, int length) {
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };	
		answer = Math.max(answer, length);
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			// 4방 그래프 확인 + 남은 자른 횟수 써서 잘랐을 떄 갈 수 있는지
			if (0 <= ny && ny < N && 0 <= nx && nx < N) {
				//System.out.println(y + " " + x + " - " + ny + " " + nx);
				// 나보다 클때 공사 가능여부 확인
				if (map[ny][nx] >= map[y][x] && !cutFlag && map[ny][nx] - map[y][x] < K) {
					int tmp = map[ny][nx];
					map[ny][nx] = map[y][x]-1;
					DFS(ny, nx, true, length+1);
					map[ny][nx] = tmp;
				} else if (map[ny][nx] < map[y][x]) {
					DFS(ny, nx, cutFlag, length+1);
				}
			}
		}
		
	}
}
