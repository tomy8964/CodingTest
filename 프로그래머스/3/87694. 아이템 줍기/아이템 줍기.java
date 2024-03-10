import java.util.*;
class Solution {
    static int answer = 0;
    static boolean[][] visited;
    static int[][] map;
    static int[] xMove = {1, -1, 0, 0};
    static int[] yMove = {0, 0, 1, -1};


    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;
        map = new int[101][101];
        visited = new boolean[101][101];
        // [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]
        for (int r = 0; r < rectangle.length; r++) {
            int x = rectangle[r][1] * 2;
            int y = rectangle[r][0] * 2;
            int N = rectangle[r][3] * 2;
            int M = rectangle[r][2] * 2;
            for (int i = x; i <= N; i++) {
                for (int j = y; j <= M; j++) {
                    map[i][j] = r + 1;
                }
            }
        }

        for (int[] rec : rectangle) {
            int x = rec[1] * 2;
            int y = rec[0] * 2;
            int N = rec[3] * 2;
            int M = rec[2] * 2;
            for (int i = x + 1; i < N; i++) {
                for (int j = y + 1; j < M; j++) {
                    map[i][j] = 0;
                }
            }
        }

        bfs(new Player(characterY * 2, characterX * 2, 0), new Player(itemY * 2, itemX * 2, 0));

        return answer / 2;
    }

    public static void bfs(Player start, Player item) {
        Queue<Player> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Player current = queue.poll();
            if (current.x == item.x && current.y == item.y) {
                answer = current.count;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newX > 100 || newY < 0 || newY > 100 || visited[newX][newY] || map[newX][newY] == 0)
                    continue;

                visited[newX][newY] = true;
                queue.add(new Player(newX, newY, current.count + 1));
            }
        }
    }

    public static class Player {
        int x;
        int y;
        int count;

        public Player(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}