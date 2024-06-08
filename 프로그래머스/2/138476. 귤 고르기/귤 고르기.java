import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for(int t : tangerine){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Integer> list = new LinkedList<>();
        for(int m : map.values()){
            list.add(m);
        }
        Collections.sort(list, Collections.reverseOrder());
        for(int l : list){
            if(k <= 0) break;
            k -= l;
            answer++;
        }
        
        return answer;
    }
}