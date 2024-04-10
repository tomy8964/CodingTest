import java.util.*;
class Solution {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        // 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수
        answer[answer.length - 1] = -1;
        stack.add(numbers[numbers.length - 1]);
        for (int i = answer.length - 2; i > -1; i--) {
            // 가장 가까운 숫자들을 보면서 나보다 큰 수를 찾는다.
            while (!stack.isEmpty()) {
                if (stack.peek() > numbers[i]) {
                    answer[i] = stack.peek();
                    stack.add(numbers[i]);
                    break;
                } else stack.pop();
            }
            if (stack.isEmpty()) {
                answer[i] = -1;
                stack.add(numbers[i]);
            }
        }

        return answer;
    }
}