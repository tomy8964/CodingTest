class Solution {

    public int solution(int storey) {
        int answer = 0;
        
        while(storey>0){
            int current = storey % 10;
            storey /= 10;
            
            // 현재 자릿수가 5면 다음 자릿수가 5보다 큰 경우 올라간다.
            if(current == 5 && storey % 10 >= 5){
                answer += 5;
                storey++;
            } else {
                if (current > 5){
                    answer += 10-current;
                    storey++;
                } else {
                    answer += current;
                }
                
            }
        }
        
        return answer;
    }
}