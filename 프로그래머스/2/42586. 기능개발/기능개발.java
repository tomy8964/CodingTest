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
            // 인덱스 앞 순서 부터 queue에서 poll 하므로
            // 제일 처음 poll 하는 job은 무조건 출시한다.
            int count = 1;
            int currentJob = queue.poll();
            // 이번 일이 끝났을때 먼저 끝난 작업들 중
            // 출시할 수 있는 작업들을 확인한다.
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer job = queue.peek();
                if (job <= currentJob) {
                    queue.poll();
                    count++;
                }
                // 큐에 있는 작업들 중 제일 앞에 있는 작업이 출시되지 못한다면
                // 그 뒤에 있는 작업들은 출시하지 못한다.
                else break;
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}