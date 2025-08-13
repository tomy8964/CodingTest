import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        int[] answer = new int[n];
        List<Integer> people = new ArrayList<>();
        
        // 0! == 1
        factorial[0] = 1;
        for(int i = 1; i <= n; i++){
            people.add(i);
            factorial[i] = factorial[i - 1] * i;
        }
        
        k--;
        
        for(int i = 0; i < n; i++){
            // 남아있는 수들로 만들 수 있는 경우의 수
            long cases = factorial[n - 1 - i];
            // 그 중 어떤 숫자를 사용해야할지
            int index = (int) (k / cases);
            answer[i] = people.get(index);
            people.remove(index);
            k %= cases;
        }
        
        return answer;
    }
}