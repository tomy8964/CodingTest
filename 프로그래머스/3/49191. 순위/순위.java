import java.util.*;
class Solution {

    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];

            graph[win][lose] = 1;
            graph[lose][win] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // i가 k에게 이겼으면 k가 이긴 j에게도 이긴다.
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                    // i가 k에게 졌으면 k가 진 j에게도 진다.
                    if (graph[i][k] == -1 && graph[k][j] == -1) {
                        graph[i][j] = -1;
                    }
                }
            }
        }

        for (int[] player : graph) {
            int count = 0;
            for (int game : player) {
                if (game != 0) {
                    count++;
                }
            }
            // 자기를 제외한 나머지 경기 결과에 대해 안다. -> 순위를 정할 수 있다.
            if (count == n - 1) answer++;
        }

        return answer;
    }
}