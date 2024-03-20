class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int n = sticker.length;

        if(n==1) return sticker[0];

        // 처음 스티커를 사용한 dp
        int[] dp1 = new int[n];
        // 처음 스티커를 사용하지 않은 dp
        int[] dp2 = new int[n];


        // 처음 뜯을 때 -> 마지막 뜯을 수 없음
        dp1[0] = sticker[0];
        dp1[1] = sticker[0]; // 세,네 번째 이후에 처음의 값이 포함되게 하기 위함임
        for(int i=2; i<n-1; i++){
            dp1[i] = Math.max(dp1[i-2]+sticker[i], Math.max(dp1[i-1], dp1[i-2]));
        }

        // 처음 뜯지 않을 때 -> 마지막 뜯을 수 있음
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for(int i=2; i<n; i++){
            dp2[i] = Math.max(dp2[i-2]+sticker[i], Math.max(dp2[i-1], dp2[i-2]));
        }

        answer = Math.max(dp1[n - 2], dp2[n - 1]);

        return answer;
    }
}