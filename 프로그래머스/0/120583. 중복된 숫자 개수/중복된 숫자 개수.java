import java.util.*;
class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        Arrays.sort(array);
        int index = 0;
        while(index < array.length && array[index] <= n){
            if(array[index] == n) answer++;
            index++;
        }
        return answer;
    }
}