import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length() - 1);
        String[] arr = s.split("\\}");
        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        for(String a : arr){
            a = a.replaceAll("\\{","");
            if(a.charAt(0) == ',') a = a.replaceFirst(",","");
            String[] arr2 = a.split(",");
            for(String a2 : arr2){
                if(!set.contains(a2)){
                    set.add(a2);
                    list.add(a2);
                }
            }
        }
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = Integer.parseInt(list.get(i));
        }
        return answer;
    }
}