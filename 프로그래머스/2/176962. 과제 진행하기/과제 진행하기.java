import java.util.*;
class Solution {

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Stack<Homework> stack = new Stack<>();
        // 과제를 시간이 빠른 순으로 큐에서 나오도록 우선 순위 큐
        Queue<Homework> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        for (String[] str : plans) {
            String[] split = str[1].split(":");
            queue.add(
                    new Homework(
                            str[0],
                            Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]),
                            Integer.parseInt(str[2])));
        }
        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                Homework poll = queue.poll();
                answer.add(poll.name);
                break;
            }
            // 큐에서 pop한 과제와
            Homework current = queue.poll();
            // peek한 다음 과제를 비교하여
            Homework next = queue.peek();
            // peek 과제를 시작하기 전에 pop한 과제를 얼마나 수행할 수 있을지 계산
            int remainTime = next.time - current.time;
            // 다 끝냈으면 answer에 추가
            if (remainTime >= current.playtime) {
                answer.add(current.name);
                int remainAfterFinish = remainTime - current.playtime;
                // 다 끝내고 peek 과제를 시작하기 전에 시간이 남았다면
                while (remainAfterFinish > 0 && !stack.isEmpty()) {
                    // 스택에서 pop을 하여 끝내지 못했던 과제를 수행
                    Homework remainHW = stack.pop();
                    if (remainAfterFinish >= remainHW.playtime) {
                        answer.add(remainHW.name);
                        remainAfterFinish -= remainHW.playtime;
                    } else {
                        remainHW.playtime -= remainAfterFinish;
                        stack.push(remainHW);
                        break;
                    }
                }
            }
            // 다 끝내지 못했다면 남은 시간을 계산하여 스택에 추가
            else {
                current.playtime -= remainTime;
                stack.push(current);
            }

        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer.toArray(new String[0]);
    }

    public class Homework {
        String name;
        int time;
        int playtime;

        public Homework(String name, int time, int playtime) {
            this.name = name;
            this.time = time;
            this.playtime = playtime;
        }
    }
}