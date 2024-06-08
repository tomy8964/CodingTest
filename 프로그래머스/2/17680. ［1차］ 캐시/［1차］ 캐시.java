import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> queue = new LinkedList<>();
        for(String c : cities) {
            String city = c.toLowerCase();
            if(!queue.contains(city)){
                answer += 5;
                queue.add(city);
                if(queue.size() > cacheSize) queue.poll();
            } else {
                int size = queue.size();
                for(int i = 0; i < size; i++){
                    String poll = queue.poll();
                    if (poll.equals(city)) continue;
                    queue.add(poll);
                }
                queue.add(city);
                answer++;
            }
        }
        return answer;
    }
}