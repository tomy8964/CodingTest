class Solution {

    public int solution(int[] a) {
        int answer = 0;
        // 번호가 더 작은 풍선을 터트리는 행위는 최대 1번만 할 수 있습니다.
        // 내 양쪽의 값이 둘다 나보다 작다면 절대 끝까지 남을 수 없음
        // 양쪽 끝의 풍선은 마지막까지 무조건 남을 수 있음
        answer += 2;

        // i 번째 풍선의 왼쪽에 올 수 있는 최소값을 구함
        int[] leftMin = new int[a.length];
        leftMin[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }
        // i 번째 풍선의 오른쪽에 올 수 있는 최소값을 구함
        int[] rightMin = new int[a.length];
        rightMin[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i > -1; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        // -> 0부터 i까지의 풍선 배열 중 최소값과 i 번째 풍선의 번호를 비교
        // -> i 부터 끝까지의 풍선 배열 중 최소값과 i 번째 풍선의 번호를 비교
        for (int i = 1; i < a.length - 1; i++) {
            // 둘 중 하나만 나와 같다면
            // i 번쨰 풍선은 마지막까지 남을 수 있음
            if (a[i] == leftMin[i] || a[i] == rightMin[i]) answer++;
        }
        return answer;
    }
}