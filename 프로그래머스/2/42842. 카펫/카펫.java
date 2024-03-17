import java.util.*;
class Solution {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        for (int i = sum; i >= 0; i--) {
            if (sum % i == 0) {
                int x = i;
                int y = sum / i;

                // x, y에 대해 yellow 갯수 확인
                if (yellow == (x - 2) * (y - 2)) {
                    answer[0] = x;
                    answer[1] = y;
                    break;
                }
            }
        }
        return answer;
    }
}