import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < skill.length(); i++){
            set.add(skill.charAt(i));
        }
        
        for(int s = 0; s < skill_trees.length; s++){
            boolean[] visited = new boolean[skill.length()];
            char[] arr = skill_trees[s].toCharArray();
            boolean no = false;
            for(int i = 0; i < arr.length; i++){
                if(set.contains(arr[i])){
                    for(int v = 0; v < visited.length; v++){
                        if(!visited[v]){
                            if(skill.charAt(v) != arr[i]){
                                no = true;
                                break;
                            }
                            visited[v] = true;
                            break;
                        }
                        if(no) break;
                    }
                }
                if(no) {
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
}