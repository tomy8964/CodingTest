import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        for(String r : record){
            String[] split = r.split(" ");
            if(split[0].equals("Enter")){
                map.put(split[1], split[2]);
            }
            if(split[0].equals("Change")){
                map.put(split[1], split[2]);
            }
        }
        List<String> list = new ArrayList<>();
        for(String r : record){
            StringBuilder sb = new StringBuilder();
            String[] split = r.split(" ");
            
            if(split[0].equals("Change")) continue;
            
            sb.append(map.get(split[1]));
            
            if(split[0].equals("Enter")){
                sb.append("님이 들어왔습니다.");
            } else {
                sb.append("님이 나갔습니다.");
            }
            list.add(sb.toString());
        }
        return list.toArray(new String[list.size()]);
    }
}