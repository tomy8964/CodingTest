import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int box = 1;
        Stack<Integer> stack = new Stack<>();
        for(int o : order){
            boolean delivery = false;
            if(!stack.isEmpty() && stack.peek() == o) {
                stack.pop();
                answer++;
                delivery = true;
                continue;
            }
            while(box <= order.length){
                if(box == o){
                    answer++;
                    box++;
                    delivery = true;
                    break;
                }
                stack.add(box++);
            }
            if(!delivery) return answer;
        }
        return answer;
    }
}