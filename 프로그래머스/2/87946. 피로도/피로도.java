class Solution {

    static boolean[] visited;
    static int answer;

    public int solution(int k, int[][] dungeons) {
        answer = -1;
        visited = new boolean[dungeons.length];
        dfs(dungeons, k, 0);
        return answer;
    }

    public void dfs(int[][] dungeons, int k, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            // 현재 남은 피로도가 던전의 최소 필요도보다 크거나 같은지
            if (!visited[i]) {
                if (k >= dungeons[i][0]) {
                    visited[i] = true;
                    dfs(dungeons, k - dungeons[i][1], count + 1);
                    visited[i] = false;
                }
            }
        }
        answer = Math.max(answer, count);
    }
}