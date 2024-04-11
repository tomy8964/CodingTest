import java.util.*;
class Solution {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        // 맨 뒤에 있는 수 add
        stack.add(numbers[numbers.length - 1]);
        answer[numbers.length - 1] = -1;
        // 뒤에서 한칸 앞에서부터 앞으로 가면서 탐색
        for (int i = numbers.length - 2; i > -1; i--) {
            while (!stack.isEmpty() && numbers[i] >= stack.peek()) {
                stack.pop();
            }
            // stack을 다 탐색했는데도 뒷 큰수 발견 X
            if (stack.isEmpty()) {
                stack.add(numbers[i]);
                answer[i] = -1;
            } else {
                answer[i] = stack.peek();
                stack.add(numbers[i]);
            }
        }
        return answer;
    }
}