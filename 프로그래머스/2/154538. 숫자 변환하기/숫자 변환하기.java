import java.util.*;
class Solution {
    static int answer;
    public int solution(int x, int y, int n) {
        if(x==y) return 0;
        answer = -1;
        boolean[] visited = new boolean[1_000_001];
        bfs(new Number(x, 0), y, n, visited);
        return answer;
    }
    
    public void bfs(Number x, int y, int n, boolean[] visited){
        Queue<Number> queue = new LinkedList<>();
        queue.add(x);
        visited[x.num] = true;
        
        while(!queue.isEmpty()){
            Number current = queue.poll();
            
            for(int i = 0; i<3; i++){
                int newX = 0;
                if(i==0){
                    newX = current.num + n;
                } else if (i==1){
                    newX = current.num * 2;
                } else {
                    newX = current.num * 3;
                }
                
                if(newX < 0 || newX > 1_000_000 || visited[newX]) continue;
                
                if(newX == y){
                    answer = current.count + 1;
                    return;
                }
                visited[newX] = true;
                queue.add(new Number(newX, current.count + 1));
            }
        }
    }
    
    public class Number{
        int num;
        int count;
        
        public Number(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
}