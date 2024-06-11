import java.util.*;

class Solution {
    
    static class ConsultInfo {
        
        int start, end;
        
        public ConsultInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    
    static int k, n, reqs[][]; // k: 상담 유형, n: 멘토 수, reqs의 길이 : 3 ~ 300
    static int mentos[];
    static int answer = Integer.MAX_VALUE;
    static Queue<ConsultInfo> queues[];
    
    public int solution(int _k, int _n, int[][] _reqs) {
        k = _k;
        n = _n;
        reqs = _reqs;    
        
        mentos = new int[k];
        
        duPermutation(0, n);
        
        return answer;
    }
    
    //S(n, k)를 만드는 함수
    static void duPermutation(int cnt, int rest) {
        
        if (cnt >= k) {
            if (rest == 0) {    
                solve();
            }
            return;
        }
        
        for (int i = 1; i <= n - k + 1; i++) {
            if (rest - i >= 0) {
                mentos[cnt] = i;
                duPermutation(cnt + 1, rest - i);
            }
            
        }
    }
    
    //나눈 S(n, k)로 대기시간이 얼마나 걸리는지 확인하는 함수
    static void solve() {
        
        queues = new ArrayDeque[k];
        //유형별 대기 큐 만들기
        for (int i = 0; i < k; i++) {
            queues[i] = new ArrayDeque<>();
        }
        
        //각 유형별 대기시간의 총 합
        int sum = 0;
        
        //각 유형별로 queue에 담아두자(카테고리는 편의상 0부터 시작하는 걸로)
        
        for (int i = 0; i < reqs.length; i++) {
            
            int start = reqs[i][0];
            int end = reqs[i][1];
            int category = reqs[i][2] - 1; //0부터 시작하니 -1을 해주자
            
            queues[category].add(new ConsultInfo(start, end));
        }
        
        //각 유형별 대기 시간들을 더해준다
        for (int i = 0; i < k; i++) {
            sum += getWatingTime(i);
        }
        
        answer = Math.min(answer, sum);
        
    }
    
    //각 유형별 대기시간을 구하는 함수
    static int getWatingTime(int category) {
        int restMento = mentos[category]; //남은 상담사 수
        int currentTime = 0;
        int sum = 0;
        Queue<ConsultInfo> waitingPerson = new ArrayDeque<>(); //기다리는 사람들
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        
        while (!queues[category].isEmpty() || !waitingPerson.isEmpty()) {
            
            //끝난 상담이 있는지 확인
            while (!pq.isEmpty()) {//상담중인 상담원 확인
                if (pq.peek() <= currentTime) {//현재 시간이 더 크면
                    pq.poll();//상담 빼기
                    restMento++;//상담원 추가

                } else {
                    break;
                }
            }
            //시간이 됐으면 대기큐로 이동
            if (!queues[category].isEmpty() && queues[category].peek().start <= currentTime) {
                waitingPerson.add(queues[category].poll());
            }
            
            //상담 가능한 지 확인
            while (!waitingPerson.isEmpty()) {
                if (restMento == 0) {
                    sum += waitingPerson.size();//상담 불가능하다면 대기하는 사람의 수만큼 ++
                    break;
                } else {
                    pq.offer(currentTime + waitingPerson.poll().end);//상담하러 간다.
                    restMento--;
                }
            }
            
            currentTime++;
        }
        return sum;
    } 
}
