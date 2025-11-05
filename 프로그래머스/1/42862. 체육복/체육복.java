import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] students = new int[n + 1];
        Arrays.fill(students, 1);
        for(int re : reserve){
            students[re]++;
        }
        for(int loss : lost){
            students[loss]--;
        }
        
        for(int i = 1; i <= n; i++){
            int student = students[i];
            if(student == 0){
                if(students[i - 1] == 2 && i != 1){
                    students[i]++;
                    students[i - 1]--;
                } 
                else if(i == n) continue;
                else if(students[i + 1] == 2){
                    students[i]++;
                    students[i + 1]--;
                }
            }
        }
        
        for(int s : students){
            if(s > 0) answer++;
        }
        
        return answer - 1;
    }
}
