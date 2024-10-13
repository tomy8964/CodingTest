import java.util.*;
class Solution {
    public int solution(int m, int n, String[] board) {
        String[][] map = new String[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].split("");
        }

        int count = 0;

        boolean[][] erase = new boolean[m][n];

        while (true) {
            int answer = 0;
            // 4칸 지우기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j].equals(" ")) continue;
                    if (map[i][j].equals(map[i][j + 1])
                            && map[i][j].equals(map[i + 1][j + 1])
                            && map[i][j].equals(map[i + 1][j])) {
                        if (!erase[i][j]) {
                            answer++;
                            erase[i][j] = true;
                        }
                        if (!erase[i][j + 1]) {
                            answer++;
                            erase[i][j + 1] = true;
                        }
                        if (!erase[i + 1][j]) {
                            answer++;
                            erase[i + 1][j] = true;
                        }
                        if (!erase[i + 1][j + 1]) {
                            answer++;
                            erase[i + 1][j + 1] = true;
                        }
                    }
                }
            }

            for (int a = 0; a < m; a++) {
                for (int b = 0; b < n; b++) {
                    if (erase[a][b]) map[a][b] = " ";
                }
            }

            //지워진 칸 위에서부터 내리기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n; j++) {
                    // 지워진 칸이면 패스
                    if (erase[i][j]) continue;

                    // 밑의 칸이 지워져있다면
                    int index = 1;
                    if (!erase[index + i][j]) continue;
                    while ((index + i) < m && erase[index + i][j]) {
                        index++;
                    }
                    index--;
                    // i + index 칸부터 비워져 있음
                    // 현재칸부터 위에 쌓아져 있음 블록 저장하면서 지우기
                    Queue<String> queue = new LinkedList<>();
                    for (int c = i; c >= 0; c--) {
                        if (erase[c][j]) break;
                        queue.add(map[c][j]);
                        erase[c][j] = true;
                        map[c][j] = " ";
                    }
                    while (!queue.isEmpty()) {
                        map[i + index][j] = queue.poll();
                        erase[i + index][j] = false;
                        index--;
                    }
                }
            }
            if (answer == 0) break;
            count += answer;
        }
        return count;
    }
}