#include <iostream>
#include <string>
#include <vector>


using namespace std;

vector<vector<int>> users;
int usersSize;//유저 총 몇명
vector<int> emoticons;
int permu[7], emoSize;//이모티콘 할인율, 이모티콘 최대 크기
int maxPlus, maxEarning;


void solve() {
    int plusUser = 0;
    int eSum = 0;//어닝 합
    //n명의 사람
    for (int i = 0; i < usersSize; i++) {
        int pSum = 0; //개인 어닝 합
        
        for (int j = 0; j < emoSize; j++) {
            if (permu[j] < users[i][0]) continue; //할인 만족 안하면 다음으로
            pSum += (100 - permu[j]) * emoticons[j] / 100; //이모티콘 사기
        }
        //pSum의 합이 user i의 한계치를 넘으면 || 같으면
        if (pSum >= users[i][1]) {
            plusUser++; //플러스 유저로 전환하기
        } else { //한계 아래이면 일반 이모티콘 구매
            eSum += pSum;
        }
    }
    
    //test
    //test end
    
    if (maxPlus < plusUser) {//플러스 더 가입 많이 하는 경우의 수이면
        maxPlus = plusUser;
        maxEarning = eSum;
    } else if (maxPlus == plusUser) { //플러스 가입자 수가 같으면
        maxEarning = max(maxEarning, eSum); //더 큰 earning을 얻어야 함.
    }
}


void permutation(int c) { //중복 순열로 모든 할인율의 경우의 수 구하기
    if (c == emoSize) {
        solve();//구하기
        return;
    }
    
    for (int i = 1; i <= 4; i++) {
        permu[c] = i * 10;
        permutation(c + 1);
    }
    
}

vector<int> solution(vector<vector<int>> users_, vector<int> emoticons_) {
    //init
    users = users_;
    emoticons = emoticons_;
    vector<int> answer;
    emoSize = emoticons.size();
    usersSize = users.size();
    //init end
    
    //dupermutation
    permutation(0);
    
    
    answer.push_back(maxPlus);
    answer.push_back(maxEarning);
    
    return answer;
}
