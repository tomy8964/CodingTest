class Solution {

    public int solution(String name) {
        int answer = 0;
        // 오른쪽으로만 가면서 바꿀 경우
        int right = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            answer += Math.min((26 - (name.charAt(i) - 'A')), (name.charAt(i) - 'A'));


            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }
            right = Math.min(right, i * 2 + (name.length() - next));
            right = Math.min(right, (name.length() - next) * 2 + i);
        }

        answer += right;

        return answer;
    }
}