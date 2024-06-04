class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int num1, num2;
        if (a < b) {
            num2 = b;
            num1 = a;
        } else {
            num2 = a;
            num1 = b;
        }
        while(true){
            if(num2 - num1 == 1) {
                if(num2 % 2 == 0 && num1 % 2 != 0) break;
            }
            if(num1 % 2 != 0) num1++;
            if(num2 % 2 != 0) num2++;
            
            answer++;
            num1 /= 2;
            num2 /= 2;
        }
        
        return answer;
    }
}