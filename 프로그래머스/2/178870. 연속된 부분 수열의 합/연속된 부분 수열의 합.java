class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        int head = 0;
        int tail = 0;
        int sum = sequence[tail];
        while(tail < sequence.length) {
            if(sum < k) {
                tail++;
                if(tail == sequence.length) break;
                sum += sequence[tail];
            } else {
                if (sum == k) {
                    if((tail - head) < (answer[1] - answer[0])) {
                        answer[0] = head;
                        answer[1] = tail;
                    }
                }
                sum -= sequence[head];
                head++;
            }
        }
        
        return answer;
    }
}