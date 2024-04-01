class Solution {

    static int answer, num;

    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        num = 0;
        for (int p : picks) {
            num += p;
        }
        // 곡괭이 dfs로 랜덤 선택
        // 광물 순서에 따라 광물 캐기
        boolean[] crafted = new boolean[minerals.length];
        // 광산에 있는 모든 광물을 캐거나
        // 더 사용할 곡괭이가 없을 때까지 진행하여 피로도 계산
        // 피로도 최소값 확인
        dfs(0, picks, crafted, minerals, 0);
        /** 피로도
         *       다이아 철 돌
         * 다이아   1   1  1
         * 철      5   1  1
         * 돌      25  5  1
         */
        return answer;
    }

    public void dfs(int used, int[] picks, boolean[] crafted, String[] minerals, int fatigue) {
        // 광산에 있는 모든 광물을 캐거나
        // 더 사용할 곡괭이가 없을 때까지 진행하여 피로도 계산
        if (used == num || craftAll(crafted)) {
            answer = Math.min(answer, fatigue);
            return;
        }
        // 곡괭이 dfs로 랜덤 선택
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                // 광물 순서에 따라 광물 캐기
                for (int m = 0; m < crafted.length; m++) {
                    if (!crafted[m]) {
                        int sum = 0;
                        for (int j = m; j < m + 5 && j < crafted.length; j++) {
                            crafted[j] = true;
                            // 광물의 종류에 따라 피로도 축적
                            if (minerals[j].equals("diamond")) {
                                // 곡괭이 종류에 따라 피로도 축적
                                if (i == 0) {
                                    sum += 1;
                                } else if (i == 1) {
                                    sum += 5;
                                } else sum += 25;
                            } else if (minerals[j].equals("iron")) {
                                // 곡괭이 종류에 따라 피로도 축적
                                if (i == 0) {
                                    sum += 1;
                                } else if (i == 1) {
                                    sum += 1;
                                } else sum += 5;
                            } else sum += 1;
                        }
                        dfs(used + 1, picks, crafted, minerals, fatigue + sum);
                        for (int j = m; j < m + 5 && j < crafted.length; j++) {
                            crafted[j] = false;
                        }
                        break;
                    }
                }
                picks[i]++;
            }
        }
    }

    private boolean craftAll(boolean[] crafted) {
        for (boolean b : crafted) {
            if (!b) return false;
        }
        return true;
    }
}