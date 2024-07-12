import java.util.*;
class Solution {
    public int solution(int[] array, int height) {
        int answer = 0;
        Arrays.sort(array);
        for(int a : array) {
            if(a > height) answer++;
        }
        return answer;
    }
}