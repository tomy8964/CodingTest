import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }
        for (String key : map.keySet()) {
            Integer nums = map.get(key);
            answer *= nums + 1;
        }

        return answer - 1;
    }
}