class Solution {
    public int solution(int n) {
        int[] dp = new int[60001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 5;
        
        for(int i = 5; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000_007;
        }
        return dp[n] % 1_000_000_007;
    }
}