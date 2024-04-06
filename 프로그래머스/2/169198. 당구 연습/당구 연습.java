class Solution {

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int b = 0; b < balls.length; b++) {
            int ballX = balls[b][0];
            int ballY = balls[b][1];

            // 축에 대해 대칭하여 목표 공과의 거리를 구한다.
            int distance = Integer.MAX_VALUE;
            // x축에 대해 대칭
            // 시작공이 x축에 원쿠션하기 전에 목표 공에 맞으면 안된다.
            // 같은 x좌표에 목표 공이 시작 공보다 아래쪽에 있으면 안된다.
            if (!(ballX == startX && ballY < startY)) {
                distance = Math.min(distance, getDistance(startX, -startY, ballX, ballY));
            }
            // y축에 대해 대칭
            // 시작공이 y축에 원쿠션하기 전에 목표 공에 맞으면 안된다.
            // 같은 y좌표에 목표 공이 시작 공보다 왼쪽에 있으면 안된다.
            if (!(ballY == startY && ballX < startX)) {
                distance = Math.min(distance, getDistance(-startX, startY, ballX, ballY));
            }
            // x = m 축에 대칭
            // 시작공이 x = m 축에 원쿠션하기 전에 목표 공에 맞으면 안된다.
            // 같은 y좌표에 목표 공이 시작 공보다 오른쪽에 있으면 안된다.
            if (!(ballY == startY && ballX > startX)) {
                distance = Math.min(distance, getDistance((m - startX) * 2 + startX, startY, ballX, ballY));
            }
            // y = n 축에 대해 대칭
            // 시작공이 y = n 축에 원쿠션하기 전에 목표 공에 맞으면 안된다.
            // 같은 x좌표에 목표 공이 시작 공보다 위에 있으면 안된다.
            if (!(ballX == startX && ballY > startY)) {
                distance = Math.min(distance, getDistance(startX, (n - startY) * 2 + startY, ballX, ballY));
            }
            answer[b] = distance;
        }

        return answer;
    }

    // (x-x1)^2 + (y-y1)^2
    private int getDistance(int startX, int startY, int ballX, int ballY) {
        return (int) Math.pow(startX - ballX, 2) + (int) Math.pow(startY - ballY, 2);
    }
}