import java.util.*;

class Solution {
    
    // 맵 크기, 정답(최소 턴)
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    
    // 상하좌우 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = { 0, 0, -1, 1};
    
    // 각 수레의 도착 지점 좌표
    static int rEndX, rEndY, bEndX, bEndY;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        answer = Integer.MAX_VALUE; // 전역 변수 초기화
        
        int rStartX = 0, rStartY = 0, bStartX = 0, bStartY = 0;
        
        // 1. 시작점과 도착점 좌표 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) { rStartX = i; rStartY = j; }
                if (maze[i][j] == 2) { bStartX = i; bStartY = j; }
                if (maze[i][j] == 3) { rEndX = i; rEndY = j; }
                if (maze[i][j] == 4) { bEndX = i; bEndY = j; }
            }
        }
        
        // 2. 각 수레의 방문 기록 배열 생성
        boolean[][] redVisited = new boolean[n][m];
        boolean[][] blueVisited = new boolean[n][m];
        
        // 3. 시작점 방문 처리
        redVisited[rStartX][rStartY] = true;
        blueVisited[bStartX][bStartY] = true;
        
        // 4. DFS 탐색 시작
        dfs(rStartX, rStartY, bStartX, bStartY, redVisited, blueVisited, maze, 0);
        
        // 5. 결과 반환
        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }
    
    /**
     * @param rx 현재 빨간 수레의 x 좌표
     * @param ry 현재 빨간 수레의 y 좌표
     * @param bx 현재 파란 수레의 x 좌표
     * @param by 현재 파란 수레의 y 좌표
     * @param redVisited 빨간 수레의 방문 기록
     * @param blueVisited 파란 수레의 방문 기록
     * @param maze 맵 정보
     * @param count 현재까지의 턴 수
     */
    public static void dfs(int rx, int ry, int bx, int by, 
                           boolean[][] redVisited, boolean[][] blueVisited, 
                           int[][] maze, int count) {
        
        // [가지치기]
        // 현재 턴 수가 이미 찾은 최소 턴 수보다 크거나 같으면 더 볼 필요 없음
        if (count >= answer) {
            return;
        }
        
        // [도착 여부 확인]
        boolean redArrived = (rx == rEndX && ry == rEndY);
        boolean blueArrived = (bx == bEndX && by == bEndY);
        
        // [성공 조건]
        // 둘 다 도착했으면, 최소 턴 수를 갱신하고 종료
        if (redArrived && blueArrived) {
            answer = Math.min(answer, count);
            return;
        }
        
        // --- 상태 분기 ---
        
        // [Case 1] 둘 다 아직 도착하지 않았을 때 (중첩 for문으로 16가지 조합 탐색)
        if (!redArrived && !blueArrived) {
            for (int i = 0; i < 4; i++) { // 빨간 수레 4방향
                int nrx = rx + dx[i];
                int nry = ry + dy[i];
                
                // (빨강) 맵 밖, 벽, 방문했던 곳인지 검사
                if (isInvalidMove(nrx, nry, redVisited, maze)) {
                    continue;
                }
                
                for (int j = 0; j < 4; j++) { // 파란 수레 4방향
                    int nbx = bx + dx[j];
                    int nby = by + dy[j];
                    
                    // (파랑) 맵 밖, 벽, 방문했던 곳인지 검사
                    if (isInvalidMove(nbx, nby, blueVisited, maze)) {
                        continue;
                    }
                    
                    // [규칙 4] 동시에 같은 칸으로 이동 불가
                    if (nrx == nbx && nry == nby) {
                        continue;
                    }
                    
                    // [규칙 5] 수레끼리 자리 바꾸기 불가
                    if (nrx == bx && nry == by && nbx == rx && nby == ry) {
                        continue;
                    }
                    
                    // 방문 처리
                    redVisited[nrx][nry] = true;
                    blueVisited[nbx][nby] = true;
                    
                    // 재귀 호출
                    dfs(nrx, nry, nbx, nby, redVisited, blueVisited, maze, count + 1);
                    
                    // [백트래킹]
                    // 탐색이 끝났으면 방문 표시를 해제하여 다른 경로도 탐색 가능하게 함
                    blueVisited[nbx][nby] = false;
                    redVisited[nrx][nry] = false; 
                }
            }
        }
        // [Case 2] 빨간 수레만 도착했을 때 (파란 수레만 이동)
        else if (redArrived && !blueArrived) {
            for (int j = 0; j < 4; j++) { // 파란 수레 4방향
                int nbx = bx + dx[j];
                int nby = by + dy[j];
                
                // (파랑) 맵 밖, 벽, 방문했던 곳인지 검사
                if (isInvalidMove(nbx, nby, blueVisited, maze)) {
                    continue;
                }
                
                // [규칙] 도착한 빨간 수레의 칸으로 이동 불가
                if (nbx == rx && nby == ry) {
                    continue;
                }
                
                blueVisited[nbx][nby] = true;
                dfs(rx, ry, nbx, nby, redVisited, blueVisited, maze, count + 1);
                blueVisited[nbx][nby] = false; // 백트래킹
            }
        }
        // [Case 3] 파란 수레만 도착했을 때 (빨간 수레만 이동)
        else if (!redArrived && blueArrived) {
            for (int i = 0; i < 4; i++) { // 빨간 수레 4방향
                int nrx = rx + dx[i];
                int nry = ry + dy[i];
                
                // (빨강) 맵 밖, 벽, 방문했던 곳인지 검사
                if (isInvalidMove(nrx, nry, redVisited, maze)) {
                    continue;
                }
                
                // [규칙] 도착한 파란 수레의 칸으로 이동 불가
                if (nrx == bx && nry == by) {
                    continue;
                }
                
                redVisited[nrx][nry] = true;
                dfs(nrx, nry, bx, by, redVisited, blueVisited, maze, count + 1);
                redVisited[nrx][nry] = false; // 백트래킹
            }
        }
    }
    
    /**
     * 이동하려는 칸(x, y)이 유효한지 검사하는 헬퍼 함수
     * @return true: 이동 불가(맵 밖, 벽, 이미 방문), false: 이동 가능
     */
    public static boolean isInvalidMove(int x, int y, boolean[][] visited, int[][] maze) {
        // 1. 맵 밖으로 나갔는지?
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return true;
        }
        
        // 2. 벽(5)인지?
        if (maze[x][y] == 5) {
            return true;
        }
        
        // 3. 이미 방문했던 칸인지?
        if (visited[x][y]) {
            return true;
        }
        
        // 위 3가지에 해당하지 않으면 이동 가능
        return false;
    }
}