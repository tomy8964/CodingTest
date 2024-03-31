import java.util.*;
class Solution {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        // 오름차순으로 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        int indexA = 0;
        int indexB = 0;
        
        while(indexB < B.length) {
            if(B[indexB] > A[indexA]){
                indexB++;
                indexA++;
                answer++;
            } else {
                indexB++;
            }
        }
        
        return answer;
    }
}