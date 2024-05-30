import java.util.*;
class Solution {
    static int N, M;
    static int[] xMove = {1, -1 , 0, 0};
    static int[] yMove = {0, 0 , 1, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        N = n;
        M = m;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && picture[i][j] != 0){
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(new Point(i, j), visited, picture));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(Point start, boolean[][] visited, int[][] picture){
        int count = 1;
        Queue<Point> queue = new LinkedList<>();
        visited[start.x][start.y] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            Point current = queue.poll();
            for(int i = 0; i < 4; i++) {
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];
                
                if (newX < 0 || newY < 0 || newX >= M || newY >= N || visited[newX][newY] || picture[newX][newY] == 0) continue;
                
                if(picture[newX][newY] != picture[current.x][current.y]) continue;
                
                visited[newX][newY] = true;
                queue.add(new Point(newX, newY));
                count++;
            }
        }
        return count;
    }
    
    public class Point {
        int x;
        int y;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}