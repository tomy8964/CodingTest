import java.util.*;
class Solution {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int indexA = 0;
        int indexB = 0;

        while (indexB < A.length) {
            if (A[indexA] < B[indexB]) {
                indexB++;
                indexA++;
                answer++;
            } else indexB++;
        }

        return answer;
    }
}