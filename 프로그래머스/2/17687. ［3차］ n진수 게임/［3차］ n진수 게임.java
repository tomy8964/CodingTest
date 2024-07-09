import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        int[] sequence = new int[t];
        for(int i = 0; i < t; i++) {
            sequence[i] = p;
            p += m;
        }
        StringBuilder number = new StringBuilder();
        for(int i = 0; i < m * p; i++){
            number.append(Integer.toString(i, n));
        }
        String result = number.toString();
        StringBuilder answer = new StringBuilder();
        for(int s : sequence){
            answer.append(result.charAt(s - 1));
        }
        return answer.toString().toUpperCase();
    }
}