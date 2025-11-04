import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if(calc(mid, diffs, times) <= limit){
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        return answer;
    }

    private long calc(int level, int[] diffs, int[] times) {
        long time = times[0];
        for(int i = 1; i < diffs.length; i++) {
            if(level >= diffs[i]) time += times[i];
            else {
                time += (times[i] + times[i-1]) * (diffs[i] - level) + times[i];
            }
        }
        return time;
    }
}