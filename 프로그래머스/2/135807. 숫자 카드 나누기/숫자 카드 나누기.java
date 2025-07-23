class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        // arrayA의 최대 공약수 숫자 a & arrayB 못나눔
        // arrayB의 최대 공약수 숫자 b & arrayA 못나눔
        int numA = arrayA[0];
        int numB = arrayB[0];
        for(int i = 1; i < arrayA.length; i++){
            numA = gcd(numA, arrayA[i]);
            numB = gcd(numB, arrayB[i]);
        }
        numA = cantDivided(numA, arrayB) ? numA : 0;
        numB = cantDivided(numB, arrayA) ? numB : 0;
        return Math.max(numA, numB);
    }
    
    public boolean cantDivided(int num, int[] array){
        for(int a : array){
            if(a % num == 0) return false;
        }
        return true;
    }
    
    public int gcd(int a, int b){
        int tmp;
        while(b > 0) {
            tmp = a;
            a = b;
            b = tmp % a;
        }
        return a;
    }
}