import java.util.*;
class Solution {
 
    static int[] xMove = {1,-1,0,0};
    static int[] yMove = {0,0,1,-1};
    static boolean[][] visited;
    static int n, m;
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        int landNum = 2;
        Map<Integer, Integer> oilMap = new HashMap<>();
        visited = new boolean[n][m];
        for(int i = 0; i<n; i++){
            for (int j = 0; j<m; j++){
                if(!visited[i][j] && land[i][j] != 0){
                    int count = bfs(landNum, new Point(i, j), visited, land);
                    oilMap.put(landNum, count);
                    landNum++;
                }
            }
        }
        
        for(int i = 0; i<m; i++){
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j<n; j++){
                if(land[j][i] == 1 || land[j][i]==0) continue;
                set.add(land[j][i]);
            }
            int sum = 0;
            for(Integer s : set){
                sum += oilMap.get(s);
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    public int bfs(int landN, Point start, boolean[][] visited, int[][] land){
        Queue<Point> q = new LinkedList<>();
        visited[start.x][start.y] = true;
        land[start.x][start.y] = landN;
        int count = 1;
        q.add(start);
        
        while(!q.isEmpty()){
            Point current = q.poll();
            for(int i = 0; i<4; i++){
                int newX = current.x + xMove[i];
                int newY = current.y + yMove[i];
                if(newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY]) continue;
                if(land[newX][newY]==0) continue;
                q.add(new Point(newX, newY));
                land[newX][newY] = landN;
                count++;
                visited[newX][newY] = true;
            }
        }
        return count;
    }
    
    public class Point{
        int x;
        int y;
        
        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}