import java.util.*;
class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuilder sb = new StringBuilder();
        
        while(st.hasMoreTokens()){
            String tk = st.nextToken();
            
            // 공백인 경우
            if(tk.length() == 0){
                sb.append(" ");
                continue;
            }
            
            sb
                .append(tk.substring(0,1).toUpperCase())
                .append(tk.substring(1).toLowerCase());
        }
        
        return sb.toString();
    }
}