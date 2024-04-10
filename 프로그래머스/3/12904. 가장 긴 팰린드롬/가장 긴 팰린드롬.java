class Solution
{

    public int solution(String s) {
        int answer = 1;

        // s가 팰린드롬인 경우 가장 길다.
        int length = s.length();

        // 윈도우를 하나씩 줄이면서 팰린드롬을 찾는다.
        for (int window = length; window > 0; window--) {
            // 왼쪽에서부터 윈도우를 움직이며 팰린드롬을 찾는다.
            for (int start = 0; start + window <= length; start++) {
                // 이번 윈도우의 위치가 팰린드롬인지 확인
                boolean palindrome = true;
                for (int j = 0; j < window / 2; j++) {
                    if (s.charAt(start + j) != s.charAt(start + window - j - 1)) {
                        palindrome = false;
                        break;
                    }
                }
                if (palindrome) return window;
            }
        }

        return answer;
    }
}