import java.util.*;
class Solution {
    public int solution(String s) {
        //  "{(})"
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++){
            s = s.substring(1) + s.substring(0, 1);
            int num1 = 0, num2 = 0, num3 = 0;
            Stack<Character> stack = new Stack<>();
            stack.add(s.charAt(0));
            for(int j = 1; j < s.length(); j++){
                // "[](){}"
                char c = s.charAt(j);
                if(stack.isEmpty()) {
                    stack.add(c);
                    continue;
                }
                if(c=='}' && stack.peek() == '{') {
                    stack.pop(); 
                    continue;
                }
                if(c==')' && stack.peek() == '(') {
                    stack.pop(); 
                    continue;
                }
                if(c==']' && stack.peek() == '[') {
                    stack.pop(); 
                    continue;
                }
                stack.add(c);
            }
            if(stack.isEmpty()) answer++;
        }
        
        return answer;
    }
}