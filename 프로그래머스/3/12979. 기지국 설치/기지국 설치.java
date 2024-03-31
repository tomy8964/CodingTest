class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int left = 0;
        int right = n;
        for (int s : stations) {
            right = s - 1 - w - 1;
            answer += num(right - left + 1, w);
            left = s - 1 + w + 1;
            right = s - 1 + w + 1;
        }
        if (n > right) {
            answer += num(n - right, w);
        }
        return answer;
    }

    private int num(int length, int w) {
        return (int) Math.ceil((double) length / (2 * w + 1));
    }
}