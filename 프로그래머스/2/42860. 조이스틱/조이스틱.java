class Solution {

    public int solution(String name) {
        int answer = 0;
        // 1. 계속 오른쪽으로 가면서 바꾸기
        int move = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            // 현재 위치의 A를 주어진 알파벳으로 바꾸기 위한 최소 횟수
            int up = name.charAt(i) - 'A';
            int down = 26 - (name.charAt(i) - 'A');
            answer += Math.min(up, down);

            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }

            // 2. 오른쪽으로 가다가 연속된 A를 만나면 왼쪽으로 돌아가면서 바꾸기
            move = Math.min(move, i * 2 + name.length() - next);
            // 3. 왼쪽으로 가다가 연속된 A를 만나면 오른쪽으로 돌아가면서 바꾸기
            move = Math.min(move, (name.length() - next) * 2 + i);
        }

        return answer + move;
    }
}