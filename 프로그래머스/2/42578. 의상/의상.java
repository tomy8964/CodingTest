import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        int con = 1;
        for (String key : map.keySet()) {
            Integer nums = map.get(key);
            int tmp = 0;
            for (int i = 0; i <= 1; i++) {
                tmp += combi(nums, i);
            }
            con *= tmp;
        }

        return con - 1;
    }

    public static int combi(int n, int r) {
        if (r == 0 || r == n)
            return 1;
        else
            return combi(n - 1, r - 1) + combi(n - 1, r);
    }
}