import java.util.*;
class Solution {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int num = brown + yellow;
        int sqrt = (int) Math.sqrt(num);

        // 약수를 구한다
        // 노란색이 가운데 존재하기 위해서는 
        // 가로든 세로든 3 이상이어야 하므로 3부터 시작
        for (int i = 3; i <= sqrt; i++) {
            int j = num / i;
            // 약수이다.
            if (num % i == 0 && j >= 3) {
                int x = Math.max(i, j);
                int y = Math.min(i, j);

                // 계산한 가로 세로의 카펫에서
                // 노란색이 가운데 존재할 수 있는지 확인
                // 노란색이 가운데 있을려면
                // 가로 - 2 * 세로 - 2 == yellow
                int center = (x - 2) * (y - 2);

                if (center == yellow) {
                    answer[0] = x;
                    answer[1] = y;
                    return answer;
                }
            }
        }

        return answer;
    }
}