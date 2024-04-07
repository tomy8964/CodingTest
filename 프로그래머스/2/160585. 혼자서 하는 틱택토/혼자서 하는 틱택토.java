class Solution {

    public int solution(String[] board) {
        int answer = 1;
        // "0" 선공, "X" 후공
        // "O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
        // 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.

        // "0"가 이겼는지 "X"가 이겼는지 확인
        // 선공과 후공이 모두 빙고가 생길 수 없음
        // "0"가 이겼다면 "0"의 갯수가 "X"의 갯수보다 많아야 함(같거나 적으면 안됨)
        // "X"가 이겼다면 "0"의 갯수가 "X"의 갯수와 같아야 함

        // 승패와 상관없이 선공인 "0"의 갯수가 "X" 갯수보다 같거나 많아야함
        // "0"의 갯수가 "X"의 갯수보다 1개만 더 많아야 함
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') countO++;
                if (c == 'X') countX++;
            }
        }
        // 후공이 더 둔 경우
        if (countO < countX) return 0;

        // 선공이 연속으로 둔 경우
        if (countO - 1 > countX) return 0;

        int bingoO = 0;
        int bingoX = 0;
        // 대각선 빙고 체크
        if (board[1].charAt(1) == 'O') {
            if (board[0].charAt(0) == 'O' && board[2].charAt(2) == 'O') bingoO++;
            if (board[0].charAt(2) == 'O' && board[2].charAt(0) == 'O') bingoO++;
        }
        if (board[1].charAt(1) == 'X') {
            if (board[0].charAt(0) == 'X' && board[2].charAt(2) == 'X') bingoX++;
            if (board[0].charAt(2) == 'X' && board[2].charAt(0) == 'X') bingoX++;
        }
        // 가로 빙고 체크
        for (int i = 0; i < 3; i++) {
            if (board[i].equals("OOO")) bingoO++;
            if (board[i].equals("XXX")) bingoX++;
        }
        // 세로 빙고 체크
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == 'O' && board[1].charAt(i) == 'O' && board[2].charAt(i) == 'O') bingoO++;
            if (board[0].charAt(i) == 'X' && board[1].charAt(i) == 'X' && board[2].charAt(i) == 'X') bingoX++;
        }

        // 둘다 빙고가 있는 경우
        if (bingoO > 0 && bingoX > 0) return 0;
        // 선공이 이겼는데 후공이 더 둔 경우
        if (bingoO > 0 && countO <= countX) return 0;
        // 후공이 이겼는데 선공이 한번 더 둔 경우
        if (bingoX > 0 && countO > countX) return 0;
        return answer;
    }
}