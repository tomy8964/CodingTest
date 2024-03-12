import java.util.*;
class Solution {

    static boolean[] visited;
    static List<List<Integer>> list;
    static int answer;

    public static int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;

        list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int j = 0; j < wires.length; j++) {
            int from = wires[j][0];
            int to = wires[j][1];
            list.get(from).add(to);
            list.get(to).add(from);
        }

        for (int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];

            list.get(from).remove(list.get(from).indexOf(to));
            list.get(to).remove(list.get(to).indexOf(from));

            visited = new boolean[n + 1];
            List<Integer> tmp = new ArrayList<>();
            for (int v = 1; v <= n; v++) {
                if (!visited[v]) {
                    tmp.add(bfs(v));
                }
            }

            answer = Math.min(answer, Math.abs(tmp.get(0) - tmp.get(1)));

            list.get(from).add(to);
            list.get(to).add(from);
        }

        return answer;
    }

    private static int bfs(int start) {
        int count = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (Integer to : list.get(current)) {
                if (!visited[to]) {
                    visited[to] = true;
                    queue.add(to);
                    count++;
                }
            }
        }
        return count;
    }
}