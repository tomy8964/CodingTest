import java.util.*;
class Solution {
    // 할인율은 10%, 20%, 30%, 40% 중 하나로 설정
    static int[] sales = {10,20,30,40};
    static int[][] prices;
    static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        int length = emoticons.length;
        answer = new int[2];
        prices = new int[length][2];
        boolean[] visited = new boolean[length];
        dfs(0, visited, users, emoticons);
        return answer;
    }
    
    public void dfs(int depth, boolean[] visited, int[][] users, int[] emoticons){
        int length = emoticons.length;
        if(depth == length){
            // 최대값 확인후 리턴
            int join = 0;
            int totalSum = 0;
            for(int[] user : users) {
                int sum = 0;
                for(int[] price : prices) {
                    if(user[0]<= price[0]) {
                        sum += price[1];
                    }
                }
                if (sum >= user[1]) join++;
                else totalSum += sum;
            }
            if (answer[0] < join){
                answer[0] = join;
                answer[1] = totalSum;
            } else if (answer[0] == join) {
                if (answer[1] < totalSum) answer[1] = totalSum;
            }
            return;
        }
        for (int j =0; j< 4; j++){
            int sale = sales[j];
            prices[depth][0] = sale;
            prices[depth][1] = emoticons[depth] * (100-sale)/100;
            dfs(depth+1, visited, users, emoticons);
        }
    }
}