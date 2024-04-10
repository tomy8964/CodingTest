import java.util.*;
class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Queue<Integer> queue = new PriorityQueue<>();
        for (String time : timetable) {
            String[] split = time.split(":");
            queue.add(Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]));
        }

        int currentTime = 9 * 60; // 9:00 시 시작
        int seat = m;
        int lastMan = 0;
        for (int i = 0; i < n; i++) {
            // 탈 수 있는 자리 개수
            seat = m;
            while (!queue.isEmpty() && currentTime >= queue.peek() && seat > 0) {
                // 마지막 버스에 모든 자리가 찰 경우
                if (seat == 1) {
                    lastMan = queue.peek();
                }
                queue.poll();
                seat--;
            }
            currentTime += t;
        }
        // 마지막 버스 도착 시간
        currentTime -= t;
        // 마지막 버스까지 와서 다 태우고 자리가 남았을 경우 마지막 버스 도착시간에 오면 된다.
        if (seat > 0) return numToTime(currentTime);
        else return numToTime(lastMan - 1);
    }

    public String numToTime(int num) {
        StringBuilder sb = new StringBuilder();
        int hour = num / 60;
        int min = num % 60;
        if (hour < 10) sb.append(0).append(hour);
        else sb.append(hour);
        sb.append(":");
        if (min < 10) sb.append(0).append(min);
        else sb.append(min);

        return sb.toString();
    }
}