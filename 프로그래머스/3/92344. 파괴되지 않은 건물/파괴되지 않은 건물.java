class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for (int i = 0; i < skill.length; i++) {
            int degree = (skill[i][0] == 1) ? -1 * skill[i][5] : skill[i][5];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            
            sum[x1][y1] += degree;
            sum[x1][y2 + 1] -= degree;
            sum[x2 + 1][y1] -= degree;
            sum[x2 + 1][y2 + 1] += degree;
        }
        
        for (int i = 1; i < sum.length; i++) {
            for(int j = 0; j < sum[0].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
        
        for(int j = 0; j < sum.length; j++) {
            for (int i = 1; i < sum[0].length; i++) {
                sum[j][i] += sum[j][i - 1];
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] + sum[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
}