import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long q1_sum = 0L;
        
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        Long sum = 0L;
        for(int i = 0; i < queue1.length; i++){
            q1.add(Long.valueOf(queue1[i]));
            q2.add(Long.valueOf(queue2[i]));
            q1_sum += Long.valueOf(queue1[i]);
            sum += Long.valueOf(queue1[i]);
            sum += Long.valueOf(queue2[i]);
        }
        if(sum % 2 != 0) return -1;
        sum /= 2L;
        int limit = (queue1.length + queue2.length) * 2;
        
        while(sum != q1_sum && answer <= limit){
            if(q1_sum < sum) {
                Long poll = q2.poll();
                q1.add(poll);
                q1_sum += poll;
            }
            else if (q1_sum > sum) {
                Long poll = q1.poll();
                q2.add(poll);
                q1_sum -= poll;
            }
            answer++;
        }
        
        if(q1_sum != sum) return -1;
        
        return answer;
    }
}