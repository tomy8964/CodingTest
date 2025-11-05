class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i = 0; i < schedules.length; i++){
            int dueTime = schedules[i] + 10;
            if((dueTime % 100) / 60 >= 1) {
                dueTime = dueTime + 100 * (dueTime % 100 / 60) - (dueTime % 100 / 60) * 60;
            }
            int day = startday % 7;
            boolean gift = true;
            for(int time : timelogs[i]){
                if(day == 6 || day == 0 ) time = 0;
                
                if(dueTime < time) {
                    // System.out.println("schedules " + dueTime);
                    // System.out.println("day " + day);
                    // System.out.println("time " + time);
                    gift = false;
                    break;
                }
                
                day = (day + 1) % 7;
            }
            if(gift) answer++;
        }
        
        return answer;
    }
}