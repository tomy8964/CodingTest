class Solution {

    public int solution(int[][] triangle) {
        int answer = 0;

        // 7
        // 3 8
        // 8 1 0
        // 2 7 4 4

        // dp[0][0] == triangle[0][0]
        //
        // dp[1][0] == dp[0][0] + triangle[1][0]
        // dp[1][1] == dp[0][0] + triangle[1][1]
        //
        // dp[2][0] == dp[1][0] + triangle[2][0]
        // dp[2][1] == Math.max(dp[1][0], dp[1][1]) + triangle[2][1]
        // dp[2][2] == dp[1][1] + triangle[2][2]

        int N = triangle.length;
        int[][] dp = new int[N][N];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }

        for (int cost : dp[N - 1]) {
            answer = Math.max(answer, cost);
        }

        return answer;
    }
}