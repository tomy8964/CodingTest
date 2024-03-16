import java.util.*;
class Solution {

    static boolean[] visited;
    static List<List<Integer>> edgeList;
    static List<Node> nodeList;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        edgeList = new ArrayList<>();
        nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            int from = e[0];
            int to = e[1];
            edgeList.get(from).add(to);
            edgeList.get(to).add(from);
        }
        visited = new boolean[n + 1];
        bfs(new Node(1, 0));
        int max = -1;
        for (Node node : nodeList) {
            if (node.distance > max) max = node.distance;
        }

        for (Node node : nodeList) {
            if (node.distance == max) answer++;
        }

        return answer;
    }

    public void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.val] = true;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int from = current.val;
            for (int to : edgeList.get(from)) {
                if (!visited[to]) {
                    visited[to] = true;
                    queue.add(new Node(to, current.distance + 1));
                    nodeList.add(new Node(to, current.distance + 1));
                }
            }
        }
    }

    public class Node {
        int val;
        int distance;

        public Node(int val, int distance) {
            this.val = val;
            this.distance = distance;
        }
    }
}