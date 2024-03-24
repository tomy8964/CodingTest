class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int num = n;
        for (int i = 0; i < num; i++) {
            answer[i] = s / n;
            if(answer[i] == 0) return new int[]{-1};
            s -= answer[i];
            n--;
        }
        return answer;
    }
}