import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        // 모든 달은 28일까지 있다고 가정합니다.
        String[] todaySplit = today.split("\\.");
        int tod = 0;
        tod += Integer.parseInt(todaySplit[0])*12*28;
        tod += Integer.parseInt(todaySplit[1])*28;
        tod += Integer.parseInt(todaySplit[2]);
        Map<String, Integer> map = new HashMap<>();
        for(String term : terms) {
            String[] str = term.split(" ");
            map.put(str[0], Integer.parseInt(str[1])*28);
        }
        for (int i = 0; i<privacies.length; i++)
        {
            String[] privac = privacies[i].split(" ");
            int mouth = map.get(privac[1]);
            String[] date = privac[0].split("\\.");
            int day = 0;
            day += Integer.parseInt(date[0])*12*28;
            day += Integer.parseInt(date[1])*28;
            day += Integer.parseInt(date[2]);
            day += mouth;
            
            System.out.println(day);
            System.out.println(tod);
            if (day <= tod) answer.add(i+1);
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
}