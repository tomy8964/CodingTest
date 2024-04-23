import java.util.*;
class Solution {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        // 테이블의 튜플을 col번째 컬럼의 값을 기준으로 오름차순 정렬을 하되,
        // 만약 그 값이 동일하면 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬합니다.
        Arrays.sort(data, Comparator.comparing((int[] o) -> o[0]).reversed());
        Arrays.sort(data, Comparator.comparing((int[] o) -> o[col - 1]));

        // 정렬된 데이터에서 S_i를 i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합으로 정의합니다.
        for (int i = row_begin - 1; i < row_end; i++) {
            int S_i = 0;
            for (int d : data[i]) {
                S_i += d % (i + 1);
            }
            if (i == row_begin - 1) answer = S_i;
            else answer ^= S_i;
        }
        // row_begin ≤ i ≤ row_end 인 모든 S_i를 누적하여 bitwise XOR 한 값을 해시 값으로서 반환합니다.

        return answer;
    }
}