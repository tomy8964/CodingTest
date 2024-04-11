import java.util.*;
class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (String time : timetable) {
            String[] split = time.split(":");
            queue.add(Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]));
        }

        // 9:00 첫 차
        int currentTime = 9 * 60;
        // 앉을 수 있는 자리수
        int seat = m;
        // 버스에 마지막으로 탄 사람이 줄을 선 시간
        int lastTime = 0;
        for (int i = 0; i < n; i++) {
            seat = m;
            // 앉을 수 있는 자리가 있고
            // 아직 안탄 사람이 있으며
            // 가장 많이 대기한 사람이 현재 이 시간에 버스를 탈 수 있는지
            while (!queue.isEmpty() && seat > 0 && queue.peek() <= currentTime) {
                // 이 자리가 마지막 자리이다.
                if (seat == 1) {
                    lastTime = queue.peek();
                }
                // 한명 앉음
                seat--;
                queue.poll();
            }
            // 다음 버스 시간 조정
            currentTime += t;
        }
        // 마지막 버스 시간 재조정
        currentTime -= t;

        // 마지막 버스에 남은 좌석이 있다 -> 마지막 버스 도착 시간에 줄을 선다.
        if (seat > 0) return numToTime(currentTime);
            // 마지막 버스에 남은 좌석이 없다 -> 마지막 버스에 마지막으로 탄 사람보다 1 분 빨리 온다.
        else return numToTime(lastTime - 1);
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