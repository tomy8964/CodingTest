import java.util.*;
class Solution {

    public long solution(int n, int[] times) {
        // 가장 오래 걸리는 사람에게 n명 전부 심사 받을 경우
        long end = (long) times[times.length - 1] * n + 1;
        long start = 0;

        while (start < end) {
            long mid = (start + end) / 2;
            long sum = 0;

            // mid 시간이 걸릴때 처리할 수 있는 사람 수 sum
            for (int time : times) {
                sum += mid / time;
            }

            // 시간을 더 늘려서 더 많은 사람을 처리해야 한다.
            if (sum < n) {
                start = mid + 1;
            } else end = mid;
        }

        return end;
    }
}