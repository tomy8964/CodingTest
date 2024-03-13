import java.util.*;
class Solution {

    public int solution(int[][] jobs) {
        List<Integer> time = new ArrayList<>();
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));
        Queue<Job> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.processTime));

        int currentTime = 0;
        int finishedJobs = 0;
        boolean[] finished = new boolean[jobs.length];
        while (finishedJobs < jobs.length) {
            for (int i = 0; i < jobs.length; i++) {
                if (jobs[i][0] <= currentTime && !finished[i]) {
                    queue.add(new Job(jobs[i][0], jobs[i][1]));
                    finished[i] = true;
                }
            }
            if (!queue.isEmpty()) {
                Job poll = queue.poll();
                currentTime += poll.processTime;
                time.add(currentTime - poll.startTime);
                finishedJobs++;
            } else currentTime++;
        }

        int sum = 0;
        for (Integer num : time) {
            sum += num;
        }

        return sum / jobs.length;
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