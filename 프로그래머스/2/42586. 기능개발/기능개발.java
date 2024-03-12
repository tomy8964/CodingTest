import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            int day = (int) Math.ceil((100 - progress) / (double) speed);
            queue.add(day);
        }

        while (!queue.isEmpty()) {
            int count = 1;
            Integer current = queue.poll();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer job = queue.peek();
                // 현재 일보다 먼저 끝난 일이다.
                if (current >= job) {
                    queue.poll();
                    count++;
                } else {
                    break;
                }
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}