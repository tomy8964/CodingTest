class Solution {
    public int solution(int[] a) {
        int answer = 0;

        // 인접한 두 풍선 중에서 번호가 더 작은 풍선을 터트리는 행위는 최대 1번만 할 수 있습니다.
        // 즉, 내 양쪽의 값이 둘 다 나보다 작다면 절대 끝까지 남을 수 없음 (작은건 1번만 터트릴 수 있으므로)

        // 제일 왼쪽부터 나까지의 번호의 최소값이 나일 경우
        // 혹은, 나부터 제일 오른쪽 까지의 번호의 최소값이 나일 경우
        // 둘 중 하나의 경우이면 나는 끝까지 남을 수 있다.

        // 양쪽 끝의 풍선은 무조건 끝까지 남을 수 있다.
        answer += 2;

        // 제일 왼쪽부터 i까지의 번호의 최소값
        int[] left = new int[a.length];
        left[0] = a[0];
        // 나부터 제일 오른쪽 까지의 번호의 최소값
        int[] right = new int[a.length];
        right[right.length - 1] = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            // 제일 왼쪽부터 i까지의 번호의 최소값
            left[i] = Math.min(left[i - 1], a[i]);
        }
        for (int i = a.length - 2; i > -1; i--) {
            // 나부터 제일 오른쪽 까지의 번호의 최소값
            right[i] = Math.min(right[i + 1], a[i]);
        }

        for (int i = 1; i < a.length - 1; i++) {
            // 제일 왼쪽부터 나까지의 번호의 최소값이 나일 경우
            // 혹은, 나부터 제일 오른쪽 까지의 번호의 최소값이 나일 경우
            // 둘 중 하나의 경우이면 나는 끝까지 남을 수 있다.
            if (left[i] == a[i] || right[i] == a[i]) {
                answer++;
            }
        }
        return answer;
    }
}