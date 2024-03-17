import java.util.*;
class Solution {

    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < costs.length; i++) {
            int parent1 = findParent(costs[i][0], parent);
            int parent2 = findParent(costs[i][1], parent);
            if (parent1 != parent2) {
                answer += costs[i][2];
                union(parent1, parent2, parent);
            }
        }
        return answer;
    }

    private void union(int node1, int node2, int[] parent) {
        if (parent[node1] < parent[node2]) {
            parent[node2] = parent[node1];
        } else parent[node1] = parent[node2];
    }

    private int findParent(int node, int[] parent) {
        if (parent[node] == node) {
            return parent[node];
        } else return findParent(parent[node], parent);
    }
}