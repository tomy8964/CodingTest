import java.util.*;
import java.util.regex.*;
class Solution {
    // 파일명을 HEAD, NUMBER, TAIL로 분리하는 정규식
    private static final Pattern pattern = Pattern.compile("^([a-zA-Z\\s.-]+)(\\d{1,5})(.*)$");
    
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        for(String file : files) {
            Matcher matcher = pattern.matcher(file);
            if (matcher.matches()) {
                list.add(new File(file, matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(3)));
            }
        }
        
        Collections.sort(list);
        
        String[] answer = new String[list.size()];
        
        for(int i = 0; i < answer.length; i++){
            answer[i] = list.get(i).original;
        }
        
        return answer;
    }
    
    public class File implements Comparable<File> {
        String original;
        String head;
        int number;
        String tail;

        public File(String original, String head, int number, String tail) {
            this.original = original;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        String getOriginal() {
            return original;
        }
        
        @Override
        public int compareTo(File f){
            int head = this.head.toLowerCase().compareTo(f.head.toLowerCase());
            if(head != 0) return head;
            
            int number = Integer.compare(this.number, f.number);
            if(number != 0) return number;
            
            // 원래 순서 유지
            return 0;
        }
    }
}