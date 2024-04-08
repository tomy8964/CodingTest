class Solution {

    public int solution(String[] board) {
        int answer = 1;
        // 선공이 "O", 후공이 "X"를 번갈아가면서
        // 가로, 세로, 대각선으로 3개가 같은 표시가 만들어지면 같은 표시를 만든 사람이 승리 (빙고를 만들면 승리) 후 게임 종료
        // 9칸이 모두 차서 더 이상 표시를 할 수 없는 경우에는 무승부로 게임이 종료

        // "O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
        // "0"의 갯수가 "X"보다 1 많거나 같아야 한다.
        // 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.
        // "0"이 이긴 경우 "X"의 갯수는 "0"의 갯수보다 작아야 한다.
        // "X"가 이긴 경우 "0"의 갯수는 "x"와 같아야 한다.

        int countO = 0;
        int countX = 0;
        for (String b : board) {
            for (int i = 0; i < 3; i++) {
                char c = b.charAt(i);
                if (c == 'O') countO++;
                if (c == 'X') countX++;
            }
        }
        // "O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
        // "0"의 갯수가 "X"보다 1 많거나 같아야 한다.
        if (countO < countX || countO > countX + 1) return 0;

        int bingoO = 0;
        int bingoX = 0;
        // 대각선 빙고 확인
        if (board[1].charAt(1) == 'O') {
            if (board[0].charAt(0) == 'O' && board[2].charAt(2) == 'O') bingoO++;
            if (board[0].charAt(2) == 'O' && board[2].charAt(0) == 'O') bingoO++;
        }
        if (board[1].charAt(1) == 'X') {
            if (board[0].charAt(0) == 'X' && board[2].charAt(2) == 'X') bingoX++;
            if (board[0].charAt(2) == 'X' && board[2].charAt(0) == 'X') bingoX++;
        }
        // 가로 빙고 확인
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == 'O') {
                if (board[i].charAt(1) == 'O' && board[i].charAt(2) == 'O') bingoO++;
            }
            if (board[i].charAt(0) == 'X') {
                if (board[i].charAt(1) == 'X' && board[i].charAt(2) == 'X') bingoX++;
            }
        }
        // 세로 빙고 확인
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == 'O') {
                if (board[1].charAt(i) == 'O' && board[2].charAt(i) == 'O') bingoO++;
            }
            if (board[0].charAt(i) == 'X') {
                if (board[1].charAt(i) == 'X' && board[2].charAt(i) == 'X') bingoX++;
            }
        }
        // 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.
        if (bingoO > 0 && bingoX > 0) return 0;
        // "0"이 이긴 경우 "X"의 갯수는 "0"의 갯수보다 작아야 한다.
        if (bingoO > 0 && countX >= countO) return 0;
        // "X"가 이긴 경우 "0"의 갯수는 "x"와 같아야 한다.
        if (bingoX > 0 && countO > countX) return 0;
        return answer;
    }
}