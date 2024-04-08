import java.util.*;
class Solution {

    public int solution(String[][] book_time) {
        Queue<Time> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        for (String[] book : book_time) {
            String[] startStr = book[0].split(":");
            int start = Integer.parseInt(startStr[0]) * 60 + Integer.parseInt(startStr[1]);
            String[] endStr = book[1].split(":");
            // 청소 시간 10분 추가
            int end = Integer.parseInt(endStr[0]) * 60 + Integer.parseInt(endStr[1]) + 10;
            queue.add(new Time(start, end));
        }

        List<Time> room = new ArrayList<>();
        room.add(queue.poll());
        while (!queue.isEmpty()) {
            Time current = queue.poll();
            boolean sameRoom = false;
            int indexRoom = -1;
            for (Time t : room) {
                if (t.end <= current.start) {
                    indexRoom = room.indexOf(t);
                    sameRoom = true;
                    break;
                }
            }
            if (sameRoom) {
                room.remove(indexRoom);
                room.add(current);
            } else room.add(current);
        }

        return room.size();
    }

    public class Time {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}