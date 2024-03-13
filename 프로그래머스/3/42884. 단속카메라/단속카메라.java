import java.util.*;
class Solution {

    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[routes.length];

        int index = 0;
        for (int i = index; i < routes.length; i++) {
            if (visited[i]) continue;
            int end = routes[i][1];
            visited[i] = true;
            for (int j = i + 1; j < routes.length; j++) {
                if (routes[j][0] <= end) {
                    visited[j] = true;
                }
            }
            answer++;
        }

        return answer;
    }
}