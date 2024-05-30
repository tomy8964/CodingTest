import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> edge = new ArrayList<>();
        
        for(int i = 0; i < n+1; i++){
            edge.add(new ArrayList<>());
        }
        
        for(int[] road : roads) {
            int from = road[0];
            int to = road[1];
            
            edge.get(from).add(to);
            edge.get(to).add(from);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        boolean[] visited = new boolean[n+1];
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dist[destination] = 0;
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            visited[current] = true;
            for(int to : edge.get(current)){
                if(visited[to]) continue;
                if(dist[to] > dist[current] + 1){
                    dist[to] = dist[current] + 1;
                    queue.add(to);
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0; i< sources.length; i++){
            if(dist[sources[i]] == Integer.MAX_VALUE){
                answer[i] = -1;
            } else answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}