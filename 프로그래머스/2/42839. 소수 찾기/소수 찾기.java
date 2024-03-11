import java.util.*;
class Solution {

    static int answer, n;
    static boolean[] visited;
    static Set<Integer> set;

    public int solution(String numbers) {
        answer = 0;
        n = numbers.length();
        visited = new boolean[n + 1];
        set = new HashSet<>();
        char[] charArray = numbers.toCharArray();
        dfs("0", charArray, 0);

        int max = Collections.max(set);
        boolean[] prime = new boolean[max + 1];
        // 소수는 false
        // 0 과 1은 소수가 아니므로 제외
        prime[0] = prime[1] = true; // 소수 아님

        for (int i = 2; i * i < prime.length; i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
        for (Integer num : set) {
            if (!prime[num]) answer++;
        }
        return answer;
    }

    private void dfs(String lastChoiceNum, char[] charArray, int depth) {
        if (depth == n) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String currentNum = lastChoiceNum + charArray[i];
                set.add(Integer.parseInt(currentNum));
                dfs(currentNum, charArray, depth + 1);
                visited[i] = false;
            }
        }
    }
}