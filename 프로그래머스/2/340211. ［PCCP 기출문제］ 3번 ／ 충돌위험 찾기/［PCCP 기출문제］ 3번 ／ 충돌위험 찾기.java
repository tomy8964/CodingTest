import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<List<int[]>> robotPaths = new ArrayList<>();
        for(int i = 0; i < routes.length; i++){
            List<int[]> paths = new ArrayList<>();
            int startX = points[routes[i][0] - 1][0];
            int startY = points[routes[i][0] - 1][1];
            paths.add(new int[]{startX, startY});
            
            for (int j = 0; j < routes[i].length - 1; j++) {
                int endX   = points[routes[i][j + 1] - 1][0];
                int endY   = points[routes[i][j + 1] - 1][1];

                // r좌표 먼저 이동
                while (startX != endX) {
                    startX += (startX > endX) ? -1 : 1;
                    paths.add(new int[]{startX, startY});
                }

                // c좌표 이동
                while (startY != endY) {
                    startY += (startY > endY) ? -1 : 1;
                    paths.add(new int[]{startX, startY});
                }
            }
            robotPaths.add(paths);
        }
        
        // 시간별 좌표를 비교해 같은 좌표에 있는 로봇이 2대 이상이면 answer++
        int maxTime = 0;
        for (List<int[]> path : robotPaths)
            maxTime = Math.max(maxTime, path.size());
        
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> map = new HashMap<>();

            for (List<int[]> path : robotPaths) {
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    String key = pos[0] + " " + pos[1];
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            for (int count : map.values()) {
                if (count >= 2) answer++;
            }
        }
        
        return answer;
    }
}