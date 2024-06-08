import java.util.*;


class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = people.length - 1;
        
        while (start <= end) {
            int sum = people[start] + people[end];
            
            if (sum <= limit) {
                start++;
                end--;
                answer++;
            } else {
                end--;
                answer++;
            }
        }
        
        return answer;
    }
}
