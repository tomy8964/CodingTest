import java.util.*;

class Solution {

    private static int[] getsDist(int n, int start, List<List<int[]>> edge) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) break;
            visited[minIndex] = true;
            for (int[] e : edge.get(minIndex)) {
                if (!visited[e[0]] && dist[e[0]] > dist[minIndex] + e[1]) {
                    dist[e[0]] = dist[minIndex] + e[1];
                }
            }
        }
        return dist;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        // S에서 모든 정점 가는 최단 거리 구하기
        List<List<int[]>> edge = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edge.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            edge.get(from).add(new int[]{to, cost});
            edge.get(to).add(new int[]{from, cost});
        }

        int[] sDist = getsDist(n, s, edge);
        int[] bDist = getsDist(n, a, edge);
        int[] aDist = getsDist(n, b, edge);

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = sDist[i] + aDist[i] + bDist[i];
        }
        Arrays.sort(dist);

        return dist[0];
    }
}