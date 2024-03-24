import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works){
            queue.add(work);
        }
        
        while(n > 0){
            int poll = queue.poll();
            if (poll == 0) return 0;
            queue.add(poll-1);
            n--;
        }
        
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            answer += poll * poll;
        }
        
        return answer;
    }
}