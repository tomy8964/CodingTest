class Solution {

    boolean solution(String s) {
        boolean answer = false;

        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (left > right) return answer;
            if (c == ')') left++;
            if (c == '(') right++;
        }

        if (left == right) answer = true;

        return answer;
    }
}