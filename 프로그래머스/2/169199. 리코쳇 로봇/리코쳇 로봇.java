import java.util.*;
class Solution {

    static int[] xMove = {1, -1, 0, 0};
    static int[] yMove = {0, 0, 1, -1};
    static int answer;

    public int solution(String[] board) {
        answer = Integer.MAX_VALUE;

        String[][] map = new String[board.length][board[0].length()];
        boolean[][] visited = new boolean[board.length][board[0].length()];
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < board.length; i++) {
            char[] charArray = board[i].toCharArray();
            for (int j = 0; j < board[0].length(); j++) {
                if (charArray[j] == 'R') {
                    startX = i;
                    startY = j;
                    map[i][j] = ".";
                } else {
                    map[i][j] = String.valueOf(charArray[j]);
                }
            }
        }


        if (bfs(new Point(startX, startY, 0), visited, map)) {
            return answer;
        } else return -1;
    }

    public boolean bfs(Point start, boolean[][] visited, String[][] map) {
        boolean canG = false;
        Queue<Point> queue = new LinkedList<>();
        visited[start.x][start.y] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + xMove[i];
                int nextY = current.y + yMove[i];

                // 굴리기
                while (nextX >= 0 && nextX < visited.length && nextY >= 0 && nextY < visited[0].length && !map[nextX][nextY].equals("D")) {
                    nextX += xMove[i];
                    nextY += yMove[i];
                }
                nextX -= xMove[i];
                nextY -= yMove[i];
                if (visited[nextX][nextY]) continue;
                if (map[nextX][nextY].equals("G")) {
                    answer = Math.min(answer, current.count + 1);
                    canG = true;
                } else {
                    queue.add(new Point(nextX, nextY, current.count + 1));
                    visited[nextX][nextY] = true;
                }
            }
        }
        return canG;
    }

    public class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}