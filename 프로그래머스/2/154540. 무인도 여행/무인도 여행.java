import java.util.*;
class Solution {

    static int[] xMove = {-1, 1, 0, 0};
    static int[] yMove = {0, 0, -1, 1};

    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        String[][] map = new String[maps.length][maps[0].length()];

        for (int j = 0; j < maps.length; j++) {
            for (int i = 0; i < maps[j].length(); i++) {
                map[j][i] = String.valueOf(maps[j].charAt(i));
            }
        }

        boolean[][] visited = new boolean[map.length][map[0].length];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (!visited[i][j]) {
                    if (map[i][j].equals("X")) continue;
                    answer.add(bfs(new Point(i, j), map, visited));
                }
            }
        }

        Collections.sort(answer);

        if (answer.isEmpty()) return new int[]{-1};
        return answer.stream().mapToInt(o -> o).toArray();
    }

    public int bfs(Point start, String[][] map, boolean[][] visited) {
        int sum = Integer.parseInt(map[start.x][start.y]);
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newY < 0 || newX >= map.length || newY >= map[0].length) continue;

                if (map[newX][newY].equals("X")) continue;

                if (visited[newX][newY]) continue;

                sum += Integer.parseInt(map[newX][newY]);
                visited[newX][newY] = true;
                queue.add(new Point(newX, newY));
            }
        }
        return sum;
    }

    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}