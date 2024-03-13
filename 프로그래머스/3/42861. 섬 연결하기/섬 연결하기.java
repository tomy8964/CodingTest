import java.util.*;
class Solution {

    public int solution(int n, int[][] costs) {
        int answer = 0;

        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        Arrays.sort(costs, (Comparator.comparingInt(o -> o[2])));

        for (int i = 0; i < costs.length; i++) {
            int node1 = costs[i][0];
            int node2 = costs[i][1];
            int parent1 = findParent(node1, parent);
            int parent2 = findParent(node2, parent);

            if (parent1 != parent2) {
                answer += costs[i][2];
                union(parent, node1, node2);
            }
        }

        return answer;
    }

    private void union(int[] parent, int node1, int node2) {
        int parent1 = findParent(node1, parent);
        int parent2 = findParent(node2, parent);
        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else parent[parent1] = parent2;
    }

    private int findParent(int node, int[] parent) {
        if (parent[node] == node) {
            return node;
        }
        return findParent(parent[node], parent);
    }
}