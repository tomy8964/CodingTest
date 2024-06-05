import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        char lastChar = words[0].charAt(0);
        for(int i = 0; i < words.length; i++) {
            if (set.contains(words[i]) || lastChar != words[i].charAt(0)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            } else {
                lastChar = words[i].charAt(words[i].length() - 1);
                set.add(words[i]);
            }
        }
        
        return answer;
    }
}