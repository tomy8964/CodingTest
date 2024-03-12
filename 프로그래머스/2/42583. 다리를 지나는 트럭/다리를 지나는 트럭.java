import java.util.*;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> watingQueue = new LinkedList<>();
        Queue<Truck> processQueue = new LinkedList<>();
        for (int truck : truck_weights) {
            watingQueue.add(truck);
        }


        while (!watingQueue.isEmpty()) {
            int size = processQueue.size();
            for (int i = 0; i < size; i++) {
                Truck poll = processQueue.poll();
                if (poll.time != bridge_length - 1) {
                    processQueue.add(new Truck(poll.weight, poll.time + 1));
                } else {
                    weight += poll.weight;
                }
            }
            Integer peek = watingQueue.peek();
            if (peek <= weight && processQueue.size() + 1 <= bridge_length) {
                Integer current = watingQueue.poll();
                processQueue.add(new Truck(current, 0));
                weight -= current;
            }
            answer++;
        }



        return answer + bridge_length;
    }

    public class Truck {
        int weight;
        int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
}