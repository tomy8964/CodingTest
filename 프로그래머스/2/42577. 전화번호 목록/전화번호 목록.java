import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        Set<String> set = new HashSet<>();
        for (String phone : phone_book) {
            int lenght = phone.length();
            String s = String.valueOf(phone.charAt(0));
            for (int i = 1; i < lenght; i++) {
                if (set.contains(s)) return false;
                else s += phone.charAt(i);
            }
            set.add(s);
        }
        return answer;
    }
}