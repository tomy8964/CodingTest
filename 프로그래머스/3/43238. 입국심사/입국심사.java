import java.util.*;
class Solution {

    public long solution(int n, int[] times) {
        Arrays.sort(times);

        // 제일 오래 걸리는 사람에게 n명 전부 심사를 받는 최악의 상황에 걸릴 시간
        long end = (long) times[times.length - 1] * n + 1;
        long start = 0;

        while (start < end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            // mid 시간 동안 처리한 사람의 명 수(sum)이 부족할때
            if (sum < n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
}