import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        boolean[] prime = new boolean[1_000_001];
        
        prime[0] = prime[1] = true; //소수 아님
        
        for (int i = 2; i * i < prime.length; i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
        
        String change = Integer.toString(n, k);
        char[] arr = change.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
            if(arr[i] == '0'){
                sb.deleteCharAt(sb.length() - 1);
                if(sb.length() == 0) continue;
                if(isPrime(sb.toString(), prime)) answer++;
                sb = new StringBuilder();
            }
        }
        
        if(sb.length() != 0 && isPrime(sb.toString(), prime)) answer++;
        
        return answer;
    }
    
    // 문자열로 표현된 숫자가 소수인지 확인하는 메서드
    private boolean isPrime(String numStr, boolean[] prime) {
        if (numStr.length() == 0) return false;
        long num = Long.parseLong(numStr);
        if (num >= prime.length) {
            return isLargePrime(num);
        }
        return !prime[(int) num];
    }
    
    // Int 범위를 넘는 숫자에 대한 소수 판별
    private boolean isLargePrime(long num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}