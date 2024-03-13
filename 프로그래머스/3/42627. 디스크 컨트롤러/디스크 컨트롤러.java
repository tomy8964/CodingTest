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
            // 먼저 요청이 들어온 job 부터 돌면서
            for (int i = 0; i < jobs.length; i++) {
                // 현재 요청을 처리할 수 있는 job이면 queue에 add
                if (jobs[i][0] <= currentTime && !finished[i]) {
                    queue.add(new Job(jobs[i][0], jobs[i][1]));
                    finished[i] = true;
                }
            }
            // 현재 처리할 수 있는 목록들이 들어있는 큐 확인
            if (!queue.isEmpty()) {
                // 가장 처리시간이 적은 job 부터 poll 되므로
                Job poll = queue.poll();
                // 처리 후 현재 시간 업데이트
                currentTime += poll.processTime;
                // 이 job의 요청부터 종료까지의 시간 저장
                time.add(currentTime - poll.startTime);
                // 완료한 작업 수 증가
                finishedJobs++;
            } // 현재 처리할 수 있는 job이 없으면 1초 증가
            else currentTime++;
        }

        // 평균 구함
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