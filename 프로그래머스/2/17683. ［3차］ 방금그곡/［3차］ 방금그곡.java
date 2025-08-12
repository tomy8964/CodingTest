import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        long maxTime = -1;
        
        for(String musicInfo : musicinfos){
            String[] parts = musicInfo.split(",");

            String startTimeStr = parts[0];
            String endTimeStr = parts[1]; 
            LocalTime startTime = LocalTime.parse(startTimeStr);
            LocalTime endTime = LocalTime.parse(endTimeStr);

            long time = ChronoUnit.MINUTES.between(startTime, endTime);
            
            String title = parts[2];
            String sheet = replaceSharps(parts[3]);
            StringBuilder sb = new StringBuilder();
            if((int) time < sheet.length()){
                String tmp = sheet.substring(0, (int) time);
                sb.append(tmp);
            } else {
                sb.append(sheet);
                for(int i = 0; i < time; i++){
                    sb.append(sheet.charAt(i % sheet.length()));
                }
            }
            
            
            m = replaceSharps(m);
            
            if(sb.toString().contains(m)){
                if(time > maxTime) {
                    answer = title;
                    maxTime = time;
                }
            }
        }
        
        return answer;
    }

    private String replaceSharps(String melody) {
        return melody.replace("C#", "c")
                     .replace("D#", "d")
                     .replace("F#", "f")
                     .replace("G#", "g")
                     .replace("A#", "a");
    }
}