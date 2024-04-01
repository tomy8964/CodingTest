import java.util.*;
class Solution {

    static Set<String> answerSet;
    static String[] answerArray;


    public int solution(String[] user_id, String[] banned_id) {

        // dfs로 탐색하며 벤 목록을 돌면서
        // 유저 중 벤할 수 있는 사람을 고른다.
        // 경우의 수를 구한다.
        boolean[] ban_used = new boolean[banned_id.length];
        boolean[] user_banned = new boolean[user_id.length];
        answerSet = new HashSet<>();
        answerArray = new String[banned_id.length];
        dfs(0, user_id, banned_id, 0, ban_used, user_banned);

        return answerSet.size();
    }

    public void dfs(int index, String[] user_id, String[] banned_id, int depth, boolean[] ban_used, boolean[] user_banned) {
        // 모든 제재 목록을 사용했다.
        if (depth == banned_id.length) {
            String[] tmp = answerArray.clone();
            Arrays.sort(tmp);
            StringBuilder sb = new StringBuilder();
            for (String s : tmp) {
                sb.append(s);
            }
            answerSet.add(sb.toString());
            return;
        }
        // 벤 목록을 돌면서
        for (int i = index; i < banned_id.length; i++) {
            // 아직 사용하지 않은 벤이면 사용하여 벤할 수 있는 유저를 탐색
            if (!ban_used[i]) {
                ban_used[i] = true;
                String ban = banned_id[i];
                int length = ban.length();
                for (int j = 0; j < user_id.length; j++) {
                    if (!user_banned[j] && length == user_id[j].length()) {
                        char[] banArray = ban.toCharArray();
                        char[] userArray = user_id[j].toCharArray();
                        boolean canBan = true;
                        for (int b = 0; b < banArray.length; b++) {
                            if (banArray[b] == '*') continue;
                            if (banArray[b] != userArray[b]) {
                                canBan = false;
                                break;
                            }
                        }
                        if (canBan) {
                            user_banned[j] = true;
                            answerArray[depth] = user_id[j];
                            dfs(i, user_id, banned_id, depth + 1, ban_used, user_banned);
                            user_banned[j] = false;
                        }
                    }
                }
                ban_used[i] = false;
            }
        }
    }
}