import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < elements.length; i++){
            int element = elements[i];
            for(int j = 1; j <= elements.length; j++){
                set.add(element);
                element += elements[(j + i) % elements.length];
            }
        }
        return set.size();
    }
}