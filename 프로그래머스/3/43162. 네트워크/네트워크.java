import java.util.*;
class Solution {
    static boolean[] visited;

    public static void main(String[] args) {

        // n	computers	                        return
        // 3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                // connected
                if (computers[i][j] == 1) {
                    if (list.get(i).contains(j)) continue;
                    if (list.get(j).contains(i)) continue;
                    list.get(i).add(j);
                    list.get(j).add(i);
                }
            }
        }
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, list);
                answer++;
            }
        }


        return answer;
    }

    public static void bfs(int start, List<List<Integer>> list) {
        visited[start] = true;
        for (int to : list.get(start)) {
            if (!visited[to]) {
                visited[to] = true;
                bfs(to, list);
            }
        }
    }
}