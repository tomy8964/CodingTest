class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int[][] dp = new int[land.length][4];
        for(int i = 0; i<4; i++){
            dp[0][i] = land[0][i];
        }
        for(int i = 1; i < land.length; i++){
            for(int j = 0; j < 4; j++){
                int max = 0;
                for(int s = 0; s < 4; s++){
                    // 같은 열
                    if(s==j) continue;
                    max = Math.max(max, dp[i - 1][s]);
                }
                dp[i][j] += land[i][j] + max;
            }
        }
        
        for(int s = 0; s < 4; s++){
            answer = Math.max(answer, dp[dp.length - 1][s]);
        }
        
        return answer;
    }
}