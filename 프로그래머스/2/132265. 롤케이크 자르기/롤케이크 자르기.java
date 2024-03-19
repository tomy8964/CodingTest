import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        // 각 조각에 동일한 가짓수의 토핑이 올라가면 
        // 공평하게 롤케이크가 나누어진 것으로 생각합니다.
        
        // 오른쪽 큐
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;
        for(int i =0; i<topping.length; i++){
            map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);
            queue.add(topping[i]);
            if (max < topping[i]) max = topping[i];
        }
        // 왼쪽이 포함하는 수
        boolean[] left = new boolean[max+1];
        int rightKind = map.size();
        int leftKind = 0;
        while(!queue.isEmpty()){
            // 오른쪽으로 한칸 이동
            int poll = queue.poll();
            map.put(poll, map.get(poll)-1);
            // 오른쪽에 포함된 종류가 이제 없다
            if (map.get(poll) == 0) rightKind--;
            // 왼쪽에 새롭게 포함되는 종류이다.
            if (!left[poll]){
                // 포함 표시 해주고
                left[poll] = true;
                leftKind++;
            }
            if(leftKind == rightKind) answer++;
        }
        
        return answer;
    }
}