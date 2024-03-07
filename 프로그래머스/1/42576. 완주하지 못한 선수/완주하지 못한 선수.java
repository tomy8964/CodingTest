import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for (String str : participant) {
            if (map.get(str) == null) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        for (String name : completion) {
            if (map.get(name) > 1) {
                map.put(name, map.get(name) - 1);
            } else if (map.get(name) == 1) {
                map.remove(name);
            }
        }
        // get all values from map
        for (String key : map.keySet()) {
            answer += key;
        }
        return answer;
    }
}