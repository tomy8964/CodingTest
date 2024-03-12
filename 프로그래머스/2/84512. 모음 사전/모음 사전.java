class Solution {
    static int answer;
    static boolean find;

    public int solution(String word) {
        answer = 0;
        find = false;
        String[] arr = {"A", "E", "I", "O", "U"};
        dfs("", word, arr, 0);
        return answer;
    }

    public void dfs(String current, String word, String[] arr, int depth) {
        if (word.equals(current)) {
            find = true;
            return;
        }
        if (depth == 5) return;
        for (int i = 0; i < 5; i++) {
            if (find) return;
            answer++;
            dfs(current + arr[i], word, arr, depth + 1);
        }
    }
}