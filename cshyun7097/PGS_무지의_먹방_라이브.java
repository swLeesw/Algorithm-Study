package algo_sil;
import java.util.*;

public class PGS_무지의_먹방_라이브 {
    static class Solution {
        static class Food implements Comparable<Food>{
            int time;
            int turn;
            public Food(int time, int turn){
                this.time = time;
                this.turn = turn;
            }

            @Override
            public int compareTo(Food o){
                return Integer.compare(this.time, o.time);
            }
        }

        public int solution(int[] food_times, long k) {
            int answer = 0;
            long time_sum = 0;
            for (int i = 0; i < food_times.length; i++){
                time_sum += food_times[i];
            }
            if (time_sum <= k){
                return -1;
            }

            PriorityQueue<Food> pq = new PriorityQueue<>();
            for(int i = 0; i < food_times.length; i++){
                pq.offer(new Food(food_times[i], i+1));
            }

            long all = 0;
            long bef = 0;
            long length = food_times.length;

            while (all + ((pq.peek().time - bef) * length) <= k){
                int cur = pq.poll().time;
                all += (cur - bef) * length;
                length--;
                bef = cur;
            }
            ArrayList<Food> arr = new ArrayList<>();

            while(!pq.isEmpty()){
                arr.add(pq.poll());
            }

            Collections.sort(arr, new Comparator<Food>(){
                @Override
                public int compare(Food o1, Food o2){
                    return Integer.compare(o1.turn, o2.turn);
                }
            });

            answer = arr.get((int) ((k-all) % length)).turn;
            return answer;
        }
    }
}
