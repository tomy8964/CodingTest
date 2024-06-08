import java.util.*;

class Solution {

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

        int[] sDist = new int[n + 1];
        Arrays.fill(sDist, Integer.MAX_VALUE);
        sDist[s] = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && sDist[j] < min) {
                    min = sDist[j];
                    minIndex = j;
                }
            }
            if(minIndex == -1) break;
            visited[minIndex] = true;
            for (int[] e : edge.get(minIndex)) {
                if (!visited[e[0]] && sDist[e[0]] > sDist[minIndex] + e[1]) {
                    sDist[e[0]] = sDist[minIndex] + e[1];
                }
            }
        }
        // 도착한 정점으로 부터 B로 가는 거리 + A로 가는 거리
        int[] bDist = new int[n + 1];
        Arrays.fill(bDist, Integer.MAX_VALUE);
        bDist[b] = 0;
        visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && bDist[j] < min) {
                    min = bDist[j];
                    minIndex = j;
                }
            }
            if(minIndex == -1) break;
            visited[minIndex] = true;
            for (int[] e : edge.get(minIndex)) {
                if (!visited[e[0]] && bDist[e[0]] > bDist[minIndex] + e[1]) {
                    bDist[e[0]] = bDist[minIndex] + e[1];
                }
            }
        }
        int[] aDist = new int[n + 1];
        Arrays.fill(aDist, Integer.MAX_VALUE);
        aDist[a] = 0;
        visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && aDist[j] < min) {
                    min = aDist[j];
                    minIndex = j;
                }
            }
            if(minIndex == -1) break;
            visited[minIndex] = true;
            for (int[] e : edge.get(minIndex)) {
                if (!visited[e[0]] && aDist[e[0]] > aDist[minIndex] + e[1]) {
                    aDist[e[0]] = aDist[minIndex] + e[1];
                }
            }
        }

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = sDist[i] + aDist[i] + bDist[i];
        }
        Arrays.sort(dist);

        return dist[0];
    }
}