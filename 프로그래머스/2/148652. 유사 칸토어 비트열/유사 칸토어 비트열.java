class Solution {
    public int solution(int n, long l, long r) {
        return countOnes(n, l - 1, r - 1);
    }
    
    private int countOnes(int n, long l, long r) {
        if (n == 0) {
            return 1;
        }
        
        long length = (long) Math.pow(5, n); // n번째 비트열의 길이
        long segmentLength = length / 5; // 각 세그먼트의 길이
        long count = 0;
        
        for (long i = 0; i < 5; i++) {
            long start = i * segmentLength;
            long end = start + segmentLength - 1;
            
            // 현재 세그먼트가 [l, r]과 겹치지 않으면 건너뛴다.
            if (end < l || r < start) continue;
            
            // 가운데 0으로만 이루어진 세그먼트는 건너뛴다.
            if (i == 2) continue;
            
            // 현재 세그먼트가 [l, r]에 완전히 포함되면, 해당 세그먼트의 '1'의 개수를 더한다.
            if (l <= start && end <= r) {
                count += Math.pow(4, n - 1);
                continue;
            }
            
            // [l, r]과 겹치는 부분이 있으면 재귀적으로 처리한다.
            count += countOnes(n - 1, Math.max(l, start) - start, Math.min(r, end) - start);
        }
        
        return (int) count;
    }
}