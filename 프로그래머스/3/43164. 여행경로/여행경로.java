import java.util.*;

class Solution {
    static boolean[] used;
    static boolean find;
    static String[] answer;

    public static String[] solution(String[][] tickets) {
        used = new boolean[tickets.length];
        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[1].equals(o2[1])) {
                return o1[0].compareTo(o2[0]);
            }
            return o1[1].compareTo(o2[1]);
        });

        for (int i = 0; i < used.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                find = false;
                answer = new String[tickets.length + 1];
                used[i] = true;
                dfs(tickets[i], 1, tickets);
                if (find) break;
                used[i] = false;
            }
        }
        return answer;
    }

    private static void dfs(String[] current, int depth, String[][] tickets) {
        if (depth == used.length) {
            answer[depth - 1] = current[0];
            answer[depth] = current[1];
            find = true;
            return;
        }
        String from = current[0];
        String to = current[1];
        answer[depth - 1] = from;
        for (int i = 0; i < used.length; i++) {
            if (!used[i] && to.equals(tickets[i][0])) {
                used[i] = true;
                dfs(tickets[i], depth + 1, tickets);
                if (find) break;
                used[i] = false;
            }
        }
    }
}