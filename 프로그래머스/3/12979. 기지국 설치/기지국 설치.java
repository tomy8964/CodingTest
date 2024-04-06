class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 전파가 안닿는 구간 길이 확인
        int left = 0;
        int right;
        // 구간에 기지국을 최소로 설치하는 갯수
        // 구간 / (2w+1) 개
        for (int s : stations) {
            right = (s - 1) - w;
            answer += count(right - left, w);
            left = s + w;
        }

        // 마지막으로 설치된 기지국 오른쪽에 전파가 닿지 않는 구간이 있을 때
        if (left < n) {
            right = n;
            answer += count(right - left, w);
        }

        return answer;
    }

    private int count(int length, int w) {
        return (int) Math.ceil((double) length / (2 * w + 1));
    }
}