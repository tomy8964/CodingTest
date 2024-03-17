import java.util.*;
class Solution {

    public int solution(String word) {
        List<String> list = new ArrayList<>();
        String aeiou = "AEIOU";
        dfs(list, "", aeiou, 0);
        return list.indexOf(word) + 1;
    }

    public void dfs(List<String> list, String current, String aeiou, int depth) {
        if (depth == 5) return;
        for (int i = 0; i < 5; i++) {
            list.add(current + aeiou.charAt(i));
            dfs(list, current + aeiou.charAt(i), aeiou, depth + 1);
        }
    }
}