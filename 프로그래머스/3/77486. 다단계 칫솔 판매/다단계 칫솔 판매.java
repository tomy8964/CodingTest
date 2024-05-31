import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> referralMap = new HashMap<>();
        for(int e = 0; e<enroll.length; e++){
            map.put(enroll[e], 0);
            referralMap.put(enroll[e], referral[e]);
        }
        for(int s = 0; s < seller.length; s++){
            String sell = seller[s];
            String recommender = referralMap.get(sell);
            int money = amount[s] * 100;
            double ten = money*0.1;
            int nine = money - (int)ten;
            boolean pass = false;
            while(!recommender.equals("-")){
                map.put(sell, map.get(sell) + nine);
                money = (int) ten;
                if(ten < 1) {
                    map.put(sell, map.get(sell) + (int) ten);
                    pass = true;
                    break;
                }
                sell = recommender;
                recommender = referralMap.get(sell);
                ten = (int)(money*0.1);
                nine = money - (int)ten;
            }
            if(pass) continue;
            map.put(sell, map.get(sell) + nine);
        }
        for(int i = 0; i < answer.length; i++){
            answer[i] = map.get(enroll[i]);
        }
        return answer;
    }
}