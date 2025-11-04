import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        int video = cal(video_len);
        int position = cal(pos);
        int op_start_s = cal(op_start);
        int op_end_s = cal(op_end);

        for(String com :commands) {
            if(com.equals("next")) {
                if(op_start_s <= position && position <= op_end_s) {
                    position = op_end_s;
                    position += 10;
                } else {
                    position += 10;
                }
                if(video - position < 10 || position > video) position = video;
                else if(op_start_s <= position && position <= op_end_s) position = op_end_s;
            }
            else {
                position -= 10;
                if(position < 0) position = 0;
                else if(op_start_s <= position && position <= op_end_s) position = op_end_s;
            } 
        }

        return cal2(position);
    }

    private String cal2(Integer time) {
        Integer m = time / 60;
        Integer s = time % 60;
        String M = "";
        String S = "";
        if(m < 10) M = "0" + String.valueOf(m);
        else M = String.valueOf(m);
        if(s < 10) S = "0" + String.valueOf(s);
        else S = String.valueOf(s);
        return M + ":" + S;
    }

    private int cal(String time) {
        String[] t = time.split(":");
        String strM = t[0];
        String strS = t[1];
        return Integer.parseInt(strM) * 60 + Integer.parseInt(strS);
    }
}