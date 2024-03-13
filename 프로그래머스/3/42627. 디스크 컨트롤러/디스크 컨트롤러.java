import java.util.*;
class Solution {

    public int solution(int[][] jobs) {
        int answer = 0;

        List<Integer> time = new ArrayList<>();
        Queue<Job> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.processTime));
        for (int[] job : jobs) {
            queue.add(new Job(job[0], job[1]));
        }

        int currentTime = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean canProcess = false;
            Queue<Job> tmpQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.processTime));
            while(!queue.isEmpty()) {
                Job poll = queue.poll();
                if (poll.startTime <= currentTime) {
                    currentTime += poll.processTime;
                    time.add(currentTime - poll.startTime);
                    canProcess = true;
                    while (!queue.isEmpty()) {
                        Job poll1 = queue.poll();
                        tmpQueue.add(poll1);
                    }
                    break;
                } else {
                    tmpQueue.add(poll);
                }
            }
            queue = tmpQueue;
            if (!canProcess) currentTime++;
        }

        int sum = 0;
        for (Integer num : time) {
            sum += num;
        }

        answer = sum / jobs.length;

        return answer;
    }

    public class Job {
        int startTime;
        int processTime;

        public Job(int startTime, int processTime) {
            this.startTime = startTime;
            this.processTime = processTime;
        }
    }
}