import java.util.*;
class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuilder sb = new StringBuilder();
        
        while(st.hasMoreTokens()){
            String str = st.nextToken();
            
            if(str.length() == 0) {
                sb.append(str);
                continue;
            }
            
            sb
                .append(str.substring(0,1).toUpperCase())
                .append(str.substring(1).toLowerCase());
        }
        return sb.toString();
    }
}