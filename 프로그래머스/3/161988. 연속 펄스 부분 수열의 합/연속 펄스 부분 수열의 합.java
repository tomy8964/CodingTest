import java.util.*;
class Solution {

    public long solution(int[] sequence) {
        // 펄스 수열 : 1 또는 -1로 시작하면서 1과 -1이 번갈아 나오는 수열
        // 나올 수 있는 연속 펄스 수열은 1. 짝수에 -1을 곱한 수열 2. 홀수에 -1을 곱한 수열
        int[] sequence1 = new int[sequence.length];
        int[] sequence2 = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 0) {
                sequence1[i] = sequence[i] * -1;
                sequence2[i] = sequence[i];
            } else {
                sequence1[i] = sequence[i];
                sequence2[i] = sequence[i] * -1;
            }
        }
        // 연속 펄스 수열에서 가장 합이 큰 부분 수열을 찾는다.
        return Math.max(find(sequence1), find(sequence2));
    }

    public long find(int[] array) {
        // dp 사용
        long[] dp = new long[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + array[i];
        }
        return Arrays.stream(dp).max().getAsLong();
    }
}