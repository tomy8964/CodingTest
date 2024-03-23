import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        // 우선 순위 큐 log n
        for(int work : works){
            queue.add(work);
        }
        
        while (n > 0) {
            int max = queue.poll();
            if (max == 0) return 0;
            queue.add(max-1);
            n--;
        }
        
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            answer += Math.pow(poll,2);
        }
        
        return answer;
    }
}