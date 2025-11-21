class Solution {
    static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        dfs(0, 1, n, q, new int[5], ans);
        return answer;
    }
    
    public void dfs(int depth, int index, int n, int[][] q, int[] con, int[] ans) {
        if(depth == 5){
            for(int i = 0; i < q.length; i++){
                int match = ans[i];
                int count = 0;
                for(int j = 0; j < q[i].length; j++){
                    for(int c = 0; c < con.length; c++){
                        if(q[i][j] == con[c]) count++;
                    }
                }
                if(match != count) return;
            }
            answer++;
            return;
        }
        for(int i = index; i <= n; i++){
            con[depth] = i;
            dfs(depth + 1, i + 1, n, q, con, ans);
        }
    }
}