package algo_sil;

import java.io.*;
import java.util.*;


public class BOJ_21608 {
    static int map[][];
    static HashMap<Integer, int[]> classRoom = new HashMap<>();
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int[] tmp = new int[4];
            for (int j = 0; j < 4; j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
            }
            classRoom.put(student, tmp);
            place(student);
        }
        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int cnt = 0;
                int[] friends = classRoom.get(map[i][j]);
                for (int k = 0; k < 4; k++) {
                    int nx = i + dy[k];
                    int ny = j + dx[k];
                    if (isIn(nx, ny))
                        continue;
                    int now = map[nx][ny];
                    for (int p = 0; p < 4; p++)
                        if (now == friends[p]) cnt++;
                }
                switch (cnt) {
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
    }

    private static boolean isIn(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= map.length || ny >= map.length;
    }

    public static void place(int student) {
        int[] friends = classRoom.get(student);

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int likeCnt = 0;
                int emptyCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dy[k];
                    int ny = j + dx[k];
                    if (isIn(nx, ny))
                        continue;
                    int now = map[nx][ny];
                    if (now == friends[0] || now == friends[1] || now == friends[2] || now == friends[3])
                        likeCnt++;
                    if (now == 0)
                        emptyCnt++;
                }
                list.add(new int[]{likeCnt, emptyCnt, i, j});
            }

            Collections.sort(list, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0]<o2[0]) return 1;
                    else if (o1[0] == o2[0]) {
                        if(o1[1] < o2[1]) return 1;
                        else if (o1[1] == o2[1]) {
                            if (o1[2] > o2[2]) return 1;
                            else if (o1[2] == o2[2]) {
                                if (o1[3] > o2[3]) return 1;
                            }
                        }
                    }
                    return -1;
                }
            });
        }

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[2];
            int y = list.get(i)[3];
            if (map[x][y] == 0) {
                map[x][y] = student;
                return;
            }
        }
    }
}