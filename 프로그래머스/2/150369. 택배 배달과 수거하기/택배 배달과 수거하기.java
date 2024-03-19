class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0;
        int pickup = 0;
        
        // 가장 먼 마지막 집부터 시작
        for (int i = n-1; i>-1; i--) {
            // 수거할거와 배달할거 갯수 확인
            deliver += deliveries[i];
            pickup += pickups[i];
            
            // 수거하거나 배달해야할게 남아 있다면
            while (deliver > 0 || pickup > 0) {
                // 한번 이동하여 cap 만큼 수거하고 배달할 수 있다.
                deliver -= cap;
                pickup -= cap;
                answer += (i+1) * 2;
            }
        }
        
        return answer;
    }
}