class Solution {

    public int solution(int[] stones, int k) {
        // 최소 한명 건널 수 있따.
        int start = 1;
        // 돌에 적힌 가장 큰 숫자만큼 친구들이 건널 수 있다
        int end = -1;
        for (int s : stones) {
            if (s > end) end = s;
        }

        while (start <= end) {
            int mid = (start + end) / 2;

            if (isCanPass(stones, k, mid)) {
                start = mid + 1;
            } else end = mid - 1;
        }

        return start;
    }

    private boolean isCanPass(int[] stones, int k, int mid) {
        int pass = 0;
        for (int stone : stones) {
            // mid 명이 건너기 전에 숫자가 0이 되어 건너뛰어야 한다.
            if (stone <= mid) {
                pass++;
            } // 다시 mid 명이 건널 수 있는 숫자의 디딤돌이 나와서 건너뛴 숫자를 0으로 초기화해준다.
            else pass = 0;

            // 건너뛴 돌의 숫자가 k보다 많을 경우
            if (pass >= k) {
                return false;
            }
        }
        return true;
    }
}