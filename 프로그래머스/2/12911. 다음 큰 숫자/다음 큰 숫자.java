import java.util.*;
class Solution {
    public int solution(int n) {
        int num1 = Integer.bitCount(n++);
        while(true) {
            if (num1 == Integer.bitCount(n++)) return n-1;
        }
    }
}