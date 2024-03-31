class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE; 
        int left = 0;
        int right = 0;
        int sum = sequence[right];
        while(true) {
            if(sum < k){
                if (right >= sequence.length - 1) break;
                right++;
                sum += sequence[right];
            } else {
                if(sum == k){
                    if (right - left < answer[1] - answer[0]) {
                        answer[1] = right;
                        answer[0] = left;
                    }
                }
                sum -= sequence[left];
                left++;
            }
        }
        return answer;
    }
}