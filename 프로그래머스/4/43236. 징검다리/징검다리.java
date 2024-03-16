import java.util.*;

class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        // 각 바위 사이의 거리의 최소값을 mid로 설정한 뒤 제거되는 바위 갯수로 판단하여 최대의 최소값을 구한다.
        int start = 0;
        int end = distance;

        while (start <= end) {
            // 설정할 바위 간의 거리의 최소값
            int mid = (start + end) / 2;
            // 제거해야할 돌의 갯수
            int sum = 0;
            int prev = 0;

            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    sum++;
                } else prev = rocks[i];
                if (sum > n) break;
            }

            // 마지막 돌도 제거해야 하는지 확인
            if (distance - prev < mid) {
                sum++;
            }

            // 제거한 돌의 갯수가 더 많으면 거리를 줄여야 한다.
            if (sum > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }
}