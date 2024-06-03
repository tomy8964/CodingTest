import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();
        for(char c : array){
            if(!stack.isEmpty()){
                char peek = stack.peek();
                if (peek == c){
                    stack.pop();
                } else {
                    stack.add(c);
                }
            } else {
                stack.add(c);
            }
        }
        return (stack.isEmpty()) ? 1 : 0;
    }
}