class Solution {
    static int answer;

    public int solution(int k, int[][] dungeons) {
        answer = -1;
        boolean[] visited = new boolean[dungeons.length];
        dfs(visited, dungeons, k, 0);
        return answer;
    }

    public void dfs(boolean[] visited, int[][] dungeons, int k, int count) {
        answer = Math.max(answer, count);
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                if (dungeons[i][0] <= k) {
                    visited[i] = true;
                    dfs(visited, dungeons, k - dungeons[i][1], count + 1);
                    visited[i] = false;
                }
            }
        }
    }
}