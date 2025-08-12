import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for(int c : course){
            Map<String, Integer> map = new HashMap<>();
            for(String o : orders){
                Set<String> set = new HashSet<>();
                char[] ca = o.toCharArray();
                if(ca.length == 1) continue;
                boolean[] visited = new boolean[ca.length];
                dfs(0, c, ca, visited, "", map, set);
            }
            if (map.isEmpty()) {
                continue;
            }       
            int max = Collections.max(map.values());
            if(max == 1) continue;
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                if(entry.getValue() == max) answer.add(entry.getKey());
            }
            // System.out.println(map);
            // System.out.println(answer);
            // System.out.println("++++++++++");
        }
        Collections.sort(answer);
        return answer.toArray(String[]::new);
    }
     public void dfs(int index, int depth, char[] ca, boolean[] visited, String str, Map<String, Integer> map, Set<String> set){
         if(str.length() == depth) {
             char[] tmp = str.toCharArray();
             Arrays.sort(tmp);
             str = new String(tmp);
             if(set.contains(str)) return;
             map.merge(str, 1, Integer::sum);
             set.add(str);
             // System.out.println("merge" + map);
             return;
         }
         
        for(int i = index; i < ca.length; i++){
            if(visited[i]) continue;
            String tmpStr = str + String.valueOf(ca[i]);
            visited[i] = true;
            dfs(index + 1, depth, ca, visited, tmpStr, map, set);
            visited[i] = false;
        }         
     }
}