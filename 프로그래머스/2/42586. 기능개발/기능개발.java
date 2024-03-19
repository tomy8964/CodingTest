import java.util.*;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            queue.add(day);
        }

        while (!queue.isEmpty()) {
            int count = 1;
            Integer finishJob = queue.poll();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer job = queue.peek();
                if (job <= finishJob) {
                    queue.poll();
                    count++;
                } else break;
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}