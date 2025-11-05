import java.util.*;

class Solution {
    public String solution(String s) {
        char[] charArr = s.toCharArray();
        boolean isWord = false;
        int idx = 0;
        for(int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            System.out.println(c);            
            if(c == ' ') {
                idx = 0;
            } else {
                if(idx % 2 == 0) charArr[i] = Character.toUpperCase(charArr[i]);
                else charArr[i] = Character.toLowerCase(charArr[i]);
                idx++;
            }
            
        }
        return String.valueOf(charArr);
    }
}