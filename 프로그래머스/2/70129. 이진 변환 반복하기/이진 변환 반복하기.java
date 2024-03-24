class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        while(!s.equals("1")){
            char[] cA = s.toCharArray();
            int c = 0;
            for(char ch : cA) {
                if(ch == '0'){
                    answer[1]++;
                } else c++;
            }
            s = Integer.toBinaryString(c);
            answer[0]++;
        }
        return answer;
    }
}