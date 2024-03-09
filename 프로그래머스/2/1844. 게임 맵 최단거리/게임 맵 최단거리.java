import java.util.*;
class Solution {
    static boolean[][] visited;
    static int answer = -1, N, M;
    static int[] xMove = {1, -1, 0, 0};
    static int[] yMove = {0, 0, 1, -1};

    public static int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N + 1][M + 1];
        bfs(new Player(0, 0, 1), maps);
        return answer;
    }

    public static void bfs(Player start, int[][] maps) {
        Queue<Player> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Player current = queue.poll();
            visited[current.x][current.y] = true;
            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX >= N || newX < 0 || newY >= M || newY < 0 || visited[newX][newY]) continue;

                if (newX == N - 1 && newY == M - 1) {
                    answer = current.count + 1;
                    return;
                }
                if (maps[newX][newY] != 0) {
                    visited[newX][newY] = true;
                    queue.add(new Player(newX, newY, current.count + 1));
                }

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