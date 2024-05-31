class Solution {
    public int solution(int n) {
        if (n==1) return 1;
        if (n==2) return 1;
        int answer = 0;
        int left = 1;
        int right = 2;
        int sum = left + right;
        while(right <= n){
            if(sum <= n) {
                if(sum == n) answer++;
                right++;
                sum += right;
            }
            else {
                sum -= left;
                left++;
            }
        }
        return answer;
    }
}