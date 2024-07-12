import java.util.*;
class Solution {
    public int[] solution(String msg) {
        List<String> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for(int i = 65; i < 91; i++){
            list.add(String.valueOf((char) i));
        }
        
        for(int i = 0; i < msg.length(); i++){
            String s = String.valueOf(msg.charAt(i));
            System.out.print(s + " ");
            int index = list.indexOf(s) + 1;
            while(list.contains(s) && i < msg.length() - 1){
                index = list.indexOf(s) + 1;
                s += String.valueOf(msg.charAt(++i));
            }
            if(!list.contains(s)){
                list.add(s);
                i--;
            }
            if(i == msg.length() - 1) index = list.indexOf(s) + 1;
            result.add(index);
            System.out.println(s + " " + index + " " + (list.indexOf(s) + 1));
        }

        int[] answer = new int[result.size()];

        for(int i = 0 ; i < result.size() ; i++) 
            answer[i] = result.get(i);

        return answer;  
    }
}