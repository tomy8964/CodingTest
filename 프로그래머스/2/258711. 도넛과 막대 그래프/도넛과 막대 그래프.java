import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int newNode = 0;
        int max = -1;
        for (int[] edge : edges) {
            max = Math.max(max, edge[0]);
            max = Math.max(max, edge[1]);
        }
        // 생성된 정점은 간선이 2개 이상이며 자신으로 돌아오는 간선이 없다.
        List<List<Integer>> edgeList = new ArrayList<>();
        for(int i = 0; i<=max; i++){
            edgeList.add(new ArrayList<>());
        }
        boolean[] comeBack = new boolean[max + 1];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            comeBack[to] = true;
            edgeList.get(from).add(to);
        }
        
        for (int i =1; i< max + 1;i++){
            if (!comeBack[i] && edgeList.get(i).size() >= 2){
                newNode = i;
                break;
            }
        }
        answer[0] = newNode;
        // 끊기전 새로운 정점에서 뻗어나간 간선 수 == 총 그래프 갯수 구해놓기
        int totalNum = edgeList.get(newNode).size();
        
        for (int i =1; i< max + 1;i++) {
            if (i == newNode) continue;
            int out = edgeList.get(i).size();
            // 노드에서 나가는 간선의 갯수가 2개 이상일 경우 8 모양 그래프
            if (out >= 2) answer[3]++;
            // 노드에서 나가는 간선의 갯수가 0개인 경우 막대 그래프
            if (out == 0) {
                if (!comeBack[i]) {
                    // 존재하지 않는 정점
                } else answer[2]++;
            } 
        }
        
        // 도넛 모양 그래프는 총 그래프 갯수에서 8과 막대 그래프의 갯수를 뺀다.
        answer[1] = totalNum - answer[2] - answer[3];
        
        return answer;
    }
}