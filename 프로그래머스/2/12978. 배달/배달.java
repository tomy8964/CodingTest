import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        List<List<Node>> edge = new ArrayList<>();
    
        for(int i = 0; i < N + 1; i++){
            edge.add(new ArrayList<>());
        }
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int[] r:road){
            int from = r[0];
            int to = r[1];
            int cost = r[2];
            
            edge.get(from).add(new Node(to, cost));
            edge.get(to).add(new Node(from, cost));
        }
        
        dijkstra(1, dist, edge);
        
        for(int i = 1; i < N + 1; i++){
            if(dist[i] <= K) answer++;
        }
        
        return answer;
    }
    
    public void dijkstra(int start, int[] dist, List<List<Node>> edge) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist[start] = 0;
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            int current = currentNode.index;
            int cost = currentNode.cost;
            
            if(cost > dist[current]) continue;
            
            for(Node next : edge.get(current)){
                int nextCost = next.cost + dist[current];
                int nextIndex = next.index;
                
                if(nextCost < dist[nextIndex]){
                    dist[nextIndex] = nextCost;
                    pq.add(new Node(nextIndex, nextCost));
                }
            }
        }
    }
    
    public class Node implements Comparable<Node>{
        int index, cost;
        
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node other){
            return this.cost - other.cost;
        }
    }
}