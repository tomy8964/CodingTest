import java.util.*;
class Solution {

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        Queue<Integer> queue = new PriorityQueue<>();

        for (String str : operations) {
            String[] split = str.split(" ");
            if (split[0].equals("I")) {
                queue.add(Integer.parseInt(split[1]));
            } else if (split[1].equals("1")) {
                queue.removeIf(i -> i == queue.stream().max(Integer::compareTo).get());
            } else queue.poll();
        }

        if (!queue.isEmpty()) {
            answer[1] = queue.poll();
            answer[0] = queue.stream().max(Integer::compareTo).get();
        }

        return answer;
    }
}