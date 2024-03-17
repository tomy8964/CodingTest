import java.util.*;

class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        long end = distance;
        long start = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            int removeRocks = 0;
            int prevRock = 0;
            for (int rock : rocks) {
                if (rock - prevRock < mid) {
                    removeRocks++;
                } else prevRock = rock;
            }

            // 제거하지 않은 돌들 중 마지막 돌과 distance 사이의 거리도 확인
            if (distance - prevRock < mid) {
                removeRocks++;
            }

            // 제거한 돌이 많으면 최소 거리를 줄인다.
            if (removeRocks > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = (int) mid;
            }
        }

        return answer;
    }
}