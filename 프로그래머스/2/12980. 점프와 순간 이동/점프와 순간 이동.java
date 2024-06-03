public class Solution {
    public int solution(int n) {
        int answer = 0;
        while(n > 0) {
            // 순간이동하여 n으로 간것이다
            if(n % 2 == 0) n /= 2;
            // 점프
            else {
                n--;
                answer++;
            }
        }
        return answer;
    }
}