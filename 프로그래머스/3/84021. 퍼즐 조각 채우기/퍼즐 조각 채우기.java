import java.util.*;
class Solution {
    static int[] xMove = {1, -1, 0, 0};
    static int[] yMove = {0, 0, 1, -1};
    static int[][] table, game_board;
    static boolean[][] tableVisited, boardVisited, visited;
    static List<List<Point>> tableList;
    static List<List<Point>> boardList;
    static int len, answer;

    public int solution(int[][] game_board, int[][] table) {
        this.table = table;
        this.game_board = game_board;
        answer = 0;
        len = game_board.length;

        tableList = new ArrayList<>();
        boardList = new ArrayList<>();
        tableVisited = new boolean[len][len];
        boardVisited = new boolean[len][len];


        //game_board 0, 1 바꿔주기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (game_board[i][j] == 1) {
                    game_board[i][j] = 0;
                } else game_board[i][j] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 블록 좌표 추출
                if (table[i][j] == 1 && !tableVisited[i][j]) {
                    bfs(new Point(i, j), table, tableVisited, tableList);
                }
                // 보드 빈공간 좌표 추출
                if (game_board[i][j] == 1 && !boardVisited[i][j]) {
                    bfs(new Point(i, j), game_board, boardVisited, boardList);
                }
            }
        }

        int table_size = tableList.size();
        int board_size = boardList.size();
        boolean[] visited = new boolean[table_size];

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < table_size; j++) {
                if (boardList.get(i).size() != tableList.get(j).size() || visited[j]) continue;
                if (checkCanFill(tableList.get(j), boardList.get(i))) {
                    visited[j] = true; //블록을 사용함
                    answer += tableList.get(j).size();
                    break;
                }
            }
        }
        return answer;
    }

    public boolean checkCanFill(List<Point> block, List<Point> board) {
        // 같은 위치를 비교하기 위해 정렬
        Collections.sort(board);

        for (int i = 0; i < 4; i++) {
            // 회전할 때마다 재정렬
            Collections.sort(block);

            int startX = block.get(0).x;
            int startY = block.get(0).y;

            //회전하면서 좌표가 바뀌기 때문에, 다시 (0,0) 기준으로 세팅
            for (int j = 0; j < block.size(); j++) {
                block.get(j).x -= startX;
                block.get(j).y -= startY;
            }

            boolean result = true;
            // 좌표 비교
            for (int p = 0; p < board.size(); p++) {
                // 좌표가 다르다면 채울 수 없으므로
                if (board.get(p).x != block.get(p).x || board.get(p).y != block.get(p).y) {
                    result = false;
                    break;
                }
            }

            // 채웠다면
            if (result) return result;
                // 못채웠다면 회전해서 다시
            else {
                // 90도 회전 (x, y) -> (y, -x)
                for (int z = 0; z < block.size(); z++) {
                    int temp = block.get(z).x;
                    block.get(z).x = block.get(z).y;
                    block.get(z).y = -temp;
                }
            }
        }
        return false;
    }

    private void bfs(Point start, int[][] board, boolean[][] visited, List<List<Point>> list) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> subList = new ArrayList<>();
        visited[start.x][start.y] = true;

        queue.add(start);
        subList.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];

                if (newX < 0 || newY < 0 || newX >= len || newY >= len) continue;

                // 이어진 블록
                if (!visited[newX][newY] && board[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                    subList.add(new Point((newX - start.x), (newY - start.y)));
                }
            }
        }
        list.add(subList);
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o) {
            int res = Integer.compare(this.x, o.x);
            if (res == 0) {
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }
}