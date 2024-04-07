import java.util.*;
class Solution {

    public long solution(int[] sequence) {
        long answer = 0;
        // 펄스 수열은 짝수 인덱스에 -1을 곱하거나 홀수 인덱스에 -1을 곱하는 것이다.
        int[] sequence1 = new int[sequence.length];
        int[] sequence2 = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            // 짝수 인덱스인 경우
            if (i % 2 == 0) {
                sequence1[i] = sequence[i];
                sequence2[i] = -sequence[i];
            } else {
                sequence1[i] = -sequence[i];
                sequence2[i] = sequence[i];
            }
        }

        // dp로 각 배열의 연속 부분 수열의 최대 합을 찾는다.
        return Math.max(findMaxSum(sequence1), findMaxSum(sequence2));
    }

    public long findMaxSum(int[] array) {
        long[] dp = new long[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + array[i];
        }
        return Arrays.stream(dp).max().getAsLong();
    }
}