import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        long count = 0L;
        int num = 0;
        for(int i = 0; i < n; i++){
            if (count + n <= left) {
                count += n;
                continue;
            }
            if (count > right + 1) break;
            for(int j = 0; j < n; j++) {
                count++;
                if (count <= left) continue;
                if (count > right + 1) break;
                answer[num] = (Math.max(i + 1, j + 1));
                num++;
            }
        }
        return answer;
    }
}