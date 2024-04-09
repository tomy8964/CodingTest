class Solution
{

    public int solution(String s) {
        int answer = 1;

        // String s가 팰린드롬인 경우 가장 길다.
        int length = s.length();

        // 길이를 1씩 줄이면서 팰린드롬을 찾는다.
        for (int i = length; i > 1; i--) {
            // 윈도우를 앞에서부터 한칸씩 오른쪽으로 움직이면서 팰린드롬인 경우를 찾는다.
            for (int start = 0; start + i <= length; start++) {
                // 이번 윈도우가 팰린드롬인지 확인
                boolean palindrome = true;
                for (int j = 0; j < i / 2; j++) {
                    if (s.charAt(start + j) != s.charAt(start + i - j - 1)) {
                        palindrome = false;
                        break;
                    }
                }
                if (palindrome) return i;
            }
        }

        return answer;
    }
}