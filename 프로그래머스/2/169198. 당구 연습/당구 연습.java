class Solution {

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int b = 0; b < balls.length; b++) {
            double minDistance = Integer.MAX_VALUE;
            int ballX = balls[b][0];
            int ballY = balls[b][1];

            // x축, y축, x=n, y=m 대칭 이동
            // x축 대칭 이동 후 길이 반환
            // x 축 대칭 이동시 벽보다 공을 먼저 만날 경우 X
            if (!(startX == ballX && startY > ballY)) {
                minDistance = Math.min(minDistance, move(startX, -startY, ballX, ballY));
            }
            // y 축 대칭 이동시 벽보다 공을 먼저 만날 경우 X
            if (!(startY == ballY && startX > ballX)) {
                minDistance = Math.min(minDistance, move(-startX, startY, ballX, ballY));
            }
            // y = m 에 대해 대칭 이동시 벽보다 공을 먼저 만날 경우 X
            if (!(startX == ballX && startY < ballY)) {
                minDistance = Math.min(minDistance, move(startX, startY + (n - startY) * 2, ballX, ballY));
            }
            // x = n 애 대해 대칭 이동시 벽보다 공을 먼저 만날 경우 X
            if (!(startY == ballY && startX < ballX)) {
                minDistance = Math.min(minDistance, move(startX + (m - startX) * 2, startY, ballX, ballY));
            }
            answer[b] = (int) minDistance;
        }

        return answer;
    }

    private double move(int moveX, int moveY, int ballX, int ballY) {
        return Math.pow((ballX - moveX), 2) + Math.pow((ballY - moveY), 2);
    }
}