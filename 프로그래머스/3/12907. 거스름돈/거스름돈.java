import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        // 거스름돈을 거슬러주는 경우의 수 저장 배열
        int[] dp = new int[n + 1];
        // 오름차순 정렬
        Arrays.sort(money);
        dp[0] = 1;
        // 거슬러줄 수 있는 돈 + 1 원부터 거슬러줘야할 돈 n 까지 거슬러주는 경우의 수를 구한다.
        for (int m : money) {
            for (int i = m; i < n + 1; i++) {
                dp[i] += dp[i - m];
            }
        }
        return dp[n];
    }
}