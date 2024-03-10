#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;


struct Pos { 
    int y, x, cost, dir; // 행, 열, 현재 최소 비용, 방향
};

struct cmp {
    bool operator()(const Pos& a, const Pos& b) {
        return a.cost > b.cost;
    }
};

int solution(vector<vector<int>> board) {
    int answer = 0;
    int n = board.size();
    int dy[4] = { -1, 1, 0, 0 };
    int dx[4] = { 0, 0, -1, 1 };


    int cost[25][25][4]; 
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            for (int k = 0; k < 4; ++k)
                cost[i][j][k] = INF;
    for (int i = 0; i < 4; ++i)
        cost[0][0][i] = 0;
    
    priority_queue<Pos, vector<Pos>, cmp> pq;
    pq.push({ 0, 0, 0, -1}); 
    while (!pq.empty()) {
        // 방문
        Pos now = pq.top();
        pq.pop();


        // 예약
        for (int i = 0; i < 4; ++i) {

            int nextY = now.y + dy[i];
            int nextX = now.x + dx[i];
            int nextDir = i;
            int nextCost = now.cost;

            if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n || board[nextY][nextX] == 1)
                continue;

            // cost cal
            nextCost += 100;
            if (now.dir == UP || now.dir == DOWN) 
                if (nextDir == LEFT || nextDir == RIGHT)
                    nextCost += 500;
            if (now.dir == LEFT || now.dir == RIGHT) 
                if (nextDir == UP || nextDir == DOWN)
                    nextCost += 500;


            if (nextCost < cost[nextY][nextX][nextDir]) {
                cost[nextY][nextX][nextDir] = nextCost;
                pq.push({ nextY, nextX, nextCost, nextDir });
            }
        }
    }
    answer = *min_element(cost[n - 1][n - 1], cost[n - 1][n - 1] + 4);
    return answer;
}
