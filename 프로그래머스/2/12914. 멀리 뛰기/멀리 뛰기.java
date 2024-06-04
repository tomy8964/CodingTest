class Solution {
    
    public long solution(int n) {
        // n 번째 칸은 n-2 칸에서 2칸 뛴 경우, n-1에서 1칸 뛴 경우
        int[] dp = new int[n+1];
        dp[1] = 1;
        if (n==1) return 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
        }
        return dp[n] % 1234567;
    }
}