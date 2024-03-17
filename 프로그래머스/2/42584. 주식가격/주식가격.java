import java.util.*;

class Solution {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            int currentPrice = prices[i];
            // 가격이 떨어졌다.
            while (!stack.isEmpty() && prices[stack.peek()] > currentPrice) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.empty()) {
            answer[stack.peek()] = prices.length - 1 - stack.peek();
            stack.pop();
        }

        return answer;
    }
}