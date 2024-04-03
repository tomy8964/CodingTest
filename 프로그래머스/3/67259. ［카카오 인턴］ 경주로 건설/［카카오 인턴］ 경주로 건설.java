import java.util.*;
class Solution {

    static int[] xMove = {-1, 1, 0, 0};
    static int[] yMove = {0, 0, -1, 1};

    public int solution(int[][] board) {
        // 0 : 상하, 1 : 좌우
        int[][][] cost = new int[board.length][board.length][2];
        for (int[][] c : cost) {
            for (int[] cc : c) {
                Arrays.fill(cc, Integer.MAX_VALUE - 1000);
            }
        }
        bfs(new Point(0, 0), board, cost);
        return Math.min(cost[board.length - 1][board.length - 1][0], cost[board.length - 1][board.length - 1][1]);
    }

    public void bfs(Point start, int[][] board, int[][][] cost) {
        Queue<Point> queue = new LinkedList<>();
        cost[start.x][start.y][0] = 0;
        cost[start.x][start.y][1] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            // 상하 방향에서 온 경우의 가격
            int currentPrice1 = cost[current.x][current.y][0];
            // 좌우 방향에서 온 경우의 가격
            int currentPrice2 = cost[current.x][current.y][1];
            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newY < 0 || newX >= board.length || newY >= board.length || board[newX][newY] == 1)
                    continue;

                // 상하 방향에서 옴
                if (i < 2) {
                    int newPrice = Math.min(currentPrice1 + 100, currentPrice2 + 600);
                    if (cost[newX][newY][0] > newPrice) {
                        cost[newX][newY][0] = newPrice;
                        queue.add(new Point(newX, newY));
                    }
                } else {
                    int newPrice = Math.min(currentPrice1 + 600, currentPrice2 + 100);
                    if (cost[newX][newY][1] > newPrice) {
                        cost[newX][newY][1] = newPrice;
                        queue.add(new Point(newX, newY));
                    }
                }
            }
        }
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