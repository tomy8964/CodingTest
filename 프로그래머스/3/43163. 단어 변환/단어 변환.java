import java.util.*;
class Solution {
    static int answer = 0;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        
        answer = 0;
        visited = new boolean[words.length];
        dfs(target, new Str(begin, 0), words);
        return answer;
    }

    public static void dfs(String target, Str start, String[] words) {
        if (start.word.equals(target)) {
            answer = start.count;
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {
                char[] charArray1 = start.word.toCharArray();
                char[] charArray2 = words[i].toCharArray();
                int different = 0;
                for (int j = 0; j < charArray1.length; j++) {
                    if (charArray1[j] != charArray2[j]) {
                        different++;
                    }
                }
                if (different == 1) {
                    visited[i] = true;
                    dfs(target, new Str(words[i], start.count + 1), words);
                    visited[i] = false;
                }
            }
        }
    }

    public static class Str {
        String word;
        int count;

        public Str(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}