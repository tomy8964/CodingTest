class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int currentHP = 0;

        int time = bandage[0];
        int heal = bandage[1];
        int plus = bandage[2];

        int previous = 0;
        currentHP = health;
        for(int[] attack : attacks) {
            //시전시간 계산
            int spentTime = attack[0] - previous -1;
            //현재 피 = 시전시간 * 초당 회복량
            currentHP += spentTime * heal;
            // 현재 피 += 연속 힐링 성공 횟수 * 추가 회복
            if(spentTime >= time) currentHP += (spentTime / time) * plus;
            // 최대 피 수치 수정
            if(currentHP > health) currentHP = health;
            // 공격 받은 현재 피
            currentHP -= attack[1];
            // 현재 시간 수정
            previous = attack[0];

            if(currentHP <= 0) return -1;
        }

        return currentHP;
    }
}