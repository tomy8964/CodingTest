import java.util.*;
class Solution {

    static int[] xMove = {-1, 1, 0, 0};
    static int[] yMove = {0, 0, -1, 1};

    public int solution(int[][] board) {
        // 0 : 상하, 1: 좌우 로 왔을 때의 비용
        int[][][] cost = new int[board.length][board.length][2];
        for (int[][] co : cost) {
            for (int[] c : co) {
                c[0] = Integer.MAX_VALUE - 1000;
                c[1] = Integer.MAX_VALUE - 1000;
            }
        }

        // 출발할때의 비용은 0으로 초기화
        cost[0][0][0] = 0;
        cost[0][0][1] = 0;

        bfs(new Point(0, 0), board, cost);

        return Math.min(cost[board.length - 1][board.length - 1][0], cost[board.length - 1][board.length - 1][1]);
    }

    public void bfs(Point start, int[][] board, int[][][] cost) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            // 현재 도로에 상하로 온 경우의 비용
            int currentPrice1 = cost[current.x][current.y][0];
            // 현재 도로에 좌우로 온 경우의 비용
            int currentPrice2 = cost[current.x][current.y][1];

            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newY < 0 || newX >= board.length || newY >= board.length) continue;
                if (board[newX][newY] == 1) continue;

                // 비용 비교
                // 상하 방향에서 온 경우
                if (i < 2) {
                    // 현재 위치로 상하로 와서 상하로 새로운 위치로 가는 비용과
                    // 현재 위치로 좌우로 와서 상하로 새로운 위치로 가는 비용 중
                    // 적은 비용을 가지고 새로운 위치로 가는 비용을 비교
                    int newPrice = Math.min(currentPrice1 + 100, currentPrice2 + 600);
                    if (cost[newX][newY][0] > newPrice) {
                        cost[newX][newY][0] = newPrice;
                        queue.add(new Point(newX, newY));
                    }
                } // 좌우 방향에서 온 경우
                else {
                    // 현재 위치로 상하로 와서 좌우로 새로운 위치로 가는 비용과
                    // 현재 위치로 좌우로 와서 좌우로 새로운 위치로 가는 비용 중
                    // 적은 비용을 가지고 새로운 위치로 가는 비용을 비교
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