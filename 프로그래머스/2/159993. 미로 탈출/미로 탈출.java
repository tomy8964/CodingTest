import java.util.*;
class Solution {
    static int answer = -1;
    static int[] xMove = {-1, 1, 0, 0};
    static int[] yMove = {0, 0, -1, 1};

    public int solution(String[] maps) {
        answer = -1;

        int[][] map = new int[maps.length][maps[0].length()];
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        Point start = null;
        Point end = null;
        Point lever = null;

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    map[i][j] = 0;
                    start = new Point(i, j, 0);
                }
                if (maps[i].charAt(j) == 'L') {
                    map[i][j] = 0;
                    lever = new Point(i, j, 0);
                }
                if (maps[i].charAt(j) == 'E') {
                    map[i][j] = 0;
                    end = new Point(i, j, 0);
                }
                if (maps[i].charAt(j) == 'X') {
                    map[i][j] = 1;
                } else map[i][j] = 0;
            }
        }
        // 출발지에서 레버까지의 최단 거리
        dfs(start, lever, map, visited);
        if (answer == -1) return -1;
        // 레버에서 도착지까지의 최단 거리
        visited = new boolean[maps.length][maps[0].length()];
        dfs(lever, end, map, visited);
        if (answer == -1) return -1;

        return answer + 1;
    }

    public void dfs(Point start, Point end, int[][] map, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newY < 0 || newX >= map.length || newY >= map[0].length) continue;

                // 벽이다
                if (map[newX][newY] == 1) continue;

                // 다시 같은 곳을 갈 필요가 없다.
                if (visited[newX][newY]) continue;

                // 도착지에 도착
                if (newX == end.x && newY == end.y) {
                    answer += current.time + 1;
                    return;
                }
                // 그냥 이동
                else {
                    visited[newX][newY] = true;
                    queue.add(new Point(newX, newY, current.time + 1));
                }
            }
        }

        answer = -1;
    }

    public class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}