import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets, Comparator.comparingInt(o1->o1[0]));
        int s = 0, e = Integer.MAX_VALUE;
        for(int i = 0; i<targets.length; i++){
            // 범위 밖의 타겟
            if(e <= targets[i][0]) {
                answer++;
                s = 0;
                e = Integer.MAX_VALUE;
            }
            if(s < targets[i][0]) s = targets[i][0];
            if(e > targets[i][1]) e = targets[i][1];
        }
        return answer;
    }
}