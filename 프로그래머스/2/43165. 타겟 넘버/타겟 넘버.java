class Solution {

    static int length = 0;
    static int answer = 0;
    static int targetNum = 0;
    static int[] number;

    public static int solution(int[] numbers, int target) {
        answer = 0;
        length = numbers.length;
        number = numbers;
        targetNum = target;
        dfs(0, 0);
        return answer;
    }

    public static void dfs(int depth, int num) {
        if (depth == length) {
            if (num == targetNum) answer++;
            return;
        }
        int choiceNum = number[depth] * -1;
        dfs(depth + 1, num + choiceNum);
        choiceNum = number[depth];
        dfs(depth + 1, num + choiceNum);
    }
}