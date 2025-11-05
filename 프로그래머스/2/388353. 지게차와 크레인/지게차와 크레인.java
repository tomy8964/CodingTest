import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        String[][] map = new String[storage.length][storage[0].length()];
        for(int i = 0; i < storage.length; i++){
            char[] charArray = storage[i].toCharArray();
            for(int j = 0; j < charArray.length; j++) {
                map[i][j] = String.valueOf(charArray[j]);
            }
        }
        int n = storage.length;
        int m = storage[0].length();
        
        for(String req : requests) {
            if(req.length() == 1){
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        if(map[i][j].equals(req)){
                            if(i == 0 || i == n -1 || j == 0 || j == m -1){
                                map[i][j] = "LASTOUT";
                            } else if(bfs(i, j, map)) map[i][j] = "LASTOUT";
                        } 
                    }
                }
            } else {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        if(map[i][j].equals(req.substring(1))){
                            map[i][j] = "LASTOUT";
                        }
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j].equals("LASTOUT")){
                        map[i][j] = "OUT";
                    }
                }
            }
        }
        
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!map[i][j].equals("OUT")){
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public boolean bfs(int x, int y, String[][] map){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[x][y] = true;
        queue.add(new int[]{x, y});
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            
            for(int i = 0; i < 4; i++){
                int newX = currentX + dx[i];
                int newY = currentY + dy[i];
                
                if(newX < 0 || newX >= map.length || newY < 0 || newY >= map[0].length) return true;
                
                if(visited[newX][newY]) continue;
                
                if(!map[newX][newY].equals("OUT")) continue;
                
                queue.add(new int[]{newX, newY});
                visited[newX][newY] = true;
            }
        }
        
        return false;
    }
}