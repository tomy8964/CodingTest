import java.util.*;
class Solution {

    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Process(i, priorities[i]));
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Process current = queue.poll();
            boolean canProcess = true;
            for (Iterator<Process> iterator = queue.iterator(); iterator.hasNext(); ) {
                Process process = iterator.next();
                if (current.priority < process.priority) {
                    queue.add(current);
                    canProcess = false;
                    break;
                }
            }
            if (canProcess) {
                count++;
                if (current.order == location) {
                    return count;
                }
            }
        }


        return answer;
    }

    public class Process {
        int order;
        int priority;

        public Process(int order, int priority) {
            this.order = order;
            this.priority = priority;
        }
    }
}