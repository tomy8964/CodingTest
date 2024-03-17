import java.util.*;
class Solution {
    static int[] xMove = {1, -1, 0, 0};
    static int[] yMove = {0, 0, 1, -1};
    static int length, answer;

    public int solution(int[][] game_board, int[][] table) {
        answer = 0;
        length = game_board.length;

        // 보드의 빈공간 좌표 리스트
        List<List<Point>> boardList = new ArrayList<>();
        // 테이블에 있는 블록의 좌표 리스트
        List<List<Point>> blockList = new ArrayList<>();

        // board 1 <-> 0
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                game_board[i][j] = (game_board[i][j] == 0) ? 1 : 0;
            }
        }

        boolean[][] boardVisited = new boolean[length][length];
        boolean[][] tableVisited = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                // 보드 빈공간 좌표 리스트 채우기
                if (game_board[i][j] == 1 && !boardVisited[i][j]) {
                    bfs(game_board, boardVisited, new Point(i, j), boardList);
                }
                // 블록 좌표 리스트 채우기
                if (table[i][j] == 1 && !tableVisited[i][j]) {
                    bfs(table, tableVisited, new Point(i, j), blockList);
                }
            }
        }

        boolean[] blockUsed = new boolean[blockList.size()];
        // 블록의 빈좌표와 블록의 좌표를 비교하여 넣을 수 있는지 확인
        for (List<Point> board : boardList) {
            for (List<Point> block : blockList) {
                if (!blockUsed[blockList.indexOf(block)]) {
                    // 칸의 크기가 같은지 비교
                    if (board.size() == block.size()) {
                        if (checkFill(board, block)){
                            blockUsed[blockList.indexOf(block)] = true;
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkFill(List<Point> board, List<Point> block) {
        // 비교하기 위한 좌표 정렬
        Collections.sort(board);

        // 90도 씩 4번 회전하며 확인
        for (int i = 0; i < 4; i++) {
            // 회전했으므로 다시 정렬
            Collections.sort(block);

            // 회전했으므로 다시 (0,0)으로 이동
            int startX = block.get(0).x;
            int startY = block.get(0).y;

            for (Point blockPoint : block) {
                blockPoint.x -= startX;
                blockPoint.y -= startY;
            }

            int count = 0;
            for (int j = 0; j < board.size(); j++) {
                Point point1 = board.get(j);
                Point point2 = block.get(j);
                if (point1.x == point2.x && point1.y == point2.y) count++;
                else break;
            }
            if (count == board.size()) {
                answer += count;
                return true;
            } else {
                // x,y -> y, -x
                for (Point blockPoint : block) {
                    int tmp = 0;
                    tmp = blockPoint.x;
                    blockPoint.x = blockPoint.y;
                    blockPoint.y = -tmp;
                }
            }
        }
        return false;
    }

    private void bfs(int[][] map, boolean[][] visited, Point start, List<List<Point>> list) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> tmpList = new ArrayList<>();
        // (0, 0) 좌표 기준으로 넣기 위해
        tmpList.add(new Point(0, 0));
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newX >= length || newY < 0 || newY >= length || map[newX][newY] == 0) continue;

                if (!visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                    // (0, 0) 좌표 기준으로 넣기 위해
                    tmpList.add(new Point(newX - start.x, newY - start.y));
                }
            }
        }
        list.add(tmpList);
    }

    public class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            int res = Integer.compare(this.x, o.x);
            if (res == 0) {
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }
}