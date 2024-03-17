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
                    // i가 k를 이기고 K는 j를 이기면
                    // i는 j를 이긴다
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                    // i가 k한테 지고 k는 j에게 지면
                    // i는 j에게 진다
                    if (graph[i][k] == -1 && graph[k][j] == -1) {
                        graph[i][j] = -1;
                        graph[j][i] = 1;
                    }
                }
            }
        }

        for (int[] player : graph) {
            int count = 0;
            for (int game : player) {
                if (game == -1 || game == 1) count++;
            }
            if (count == n-1) answer++;
        }

        return answer;
    }
}