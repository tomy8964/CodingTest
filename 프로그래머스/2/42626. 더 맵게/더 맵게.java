import java.util.*;
class Solution {

    public int solution(int[] scoville, int K) {
        // 섞은 음식의 스코빌 지수
        // = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
        int answer = 0;

        Queue<Integer> queue = new PriorityQueue<>();
        for (int s : scoville) {
            queue.add(s);
        }

        // 최대한 섞어서 하나 남을때 까지 반복
        while (queue.size() != 1) {
            Integer peek = queue.peek();
            if (peek < K) {
                Integer firstScovilleFood = queue.poll();
                Integer secondScovilleFood = queue.poll();
                Integer newFood = firstScovilleFood + (secondScovilleFood * 2);
                queue.add(newFood);
                answer++;
            } else break;
        }

        if (queue.peek() < K) answer = -1;

        return answer;
    }
}