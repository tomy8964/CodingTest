import java.util.*;
class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        
        int[][] map = new int[n][n];
        
        if(n == 1) return new int[]{1};
        int maxValue = n*(n+1)/2;
        
        
        // 값을 넣으면 안되는 위치를 -1 로 표시
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(j > i) map[i][j] = -1;
            }
        }
        
        int i = 0;
        int j = 0;
        // 0, 1, 2, 3
        int dir = 0;
        int num = 1;
        
        while(true) {
            if(map[i][j] != -1 && map[i][j] == 0) {
                map[i][j] = num++;
            }
            if(num > maxValue) break;
            
            // 아래
            if(dir == 0) i++;
            // 오른쪽
            else if(dir == 1) j++;
            // 위왼
            else if(dir == 2) {
                i--;
                j--;
            }
            
            // 방향을 바꿔야 할 때
            if( i >= n || i < 0 || j >= n || j < 0 || map[i][j] != 0) {
                // 원복
                // 아래
                if(dir == 0) {
                    i--;
                    j++;
                }
                // 오른쪽
                else if(dir == 1) {
                    j--;
                    i--;
                    j--;
                }
                // 위왼
                else if(dir == 2) {
                    i++;
                    j++;
                    i++;
                }
                dir = (dir + 1) % 3;
            }
        }
        
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                if(map[x][y] > 0) {
                    list.add(map[x][y]);
                }
            }
        }
        
        
        return list.stream()
           .mapToInt(Integer::intValue)
           .toArray();
    }
}