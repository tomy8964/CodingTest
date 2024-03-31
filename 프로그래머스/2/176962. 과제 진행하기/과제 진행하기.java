import java.util.*;
class Solution {

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Queue<HomeWork> queue = new LinkedList<>();
        Queue<HomeWork> remainQueue = new PriorityQueue<>((o1, o2) -> {
            return o2.m - o1.m;
        });

        Arrays.sort(plans, (o1, o2) -> {
            String[] split1 = o1[1].split(":");
            String[] split2 = o2[1].split(":");
            return (Integer.parseInt(split1[0]) * 60 + Integer.parseInt(split1[1])) - (Integer.parseInt(split2[0]) * 60 + Integer.parseInt(split2[1]));
        });

        for (String[] s : plans) {
            String[] split = s[1].split(":");
            queue.add(new HomeWork(
                    s[0],
                    Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]),
                    Integer.parseInt(s[2])));
        }

        int currentTime = queue.peek().m;

        while (!queue.isEmpty()) {
            // 아직 다음 숙제를 시작할 시간이 안되었으면
            // 남아있던 숙제를 한다.
            while (!remainQueue.isEmpty()) {
                if (queue.peek().m > currentTime) {
                    HomeWork remainHW = remainQueue.poll();
                    HomeWork next = queue.peek();
                    // 다음 숙제 시작 전까지 남아 있던 숙제를 한다.
                    int remainTime = next.m - currentTime;
                    if (remainTime >= remainHW.time) {
                        currentTime += remainHW.time;
                        answer.add(remainHW.name);
                    } else {
                        currentTime += remainTime;
                        remainHW.time -= remainTime;
                        remainQueue.add(remainHW);
                    }
                } else break;
            }
            // 마지막 남은 숙제라면 정답에 추가하고 종료
            if (queue.size() == 1) {
                answer.add(queue.poll().name);
                break;
            }
            currentTime = queue.peek().m;
            // 현재 숙제를 얼만큼 할 수 있는지 체크
            HomeWork current = queue.poll();
            HomeWork next = queue.peek();
            int remainTime = next.m - current.m;
            // 현재 일을 다음 숙제 시작전까지 끝낼 수 있음
            if (remainTime >= current.time) {
                currentTime += current.time;
                answer.add(current.name);
            } else {
                currentTime += remainTime;
                current.time -= remainTime;
                remainQueue.add(current);
            }

        }

        while (!remainQueue.isEmpty()) {
            answer.add(remainQueue.poll().name);
        }

        return answer.toArray(new String[0]);
    }

    public static class HomeWork {
        String name;
        int m;
        int time;

        public HomeWork(String name, int m, int time) {
            this.name = name;
            this.m = m;
            this.time = time;
        }
    }
}