class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 두 사람이 선물을 주고받은 기록이 있다면, 
        // 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
        int[][] giftMap = new int[friends.length][friends.length];
        for(String gift : gifts) {
            String[] str = gift.split(" ");
            String from = str[0];
            String to = str[1];
            int fromIndex = 0;
            int toIndex = 0;
            boolean findFrom = false;
            boolean findTo = false;
            for (int i =0; i<friends.length; i++) {
                if(friends[i].equals(from)){
                    fromIndex = i;
                    findFrom = true;
                }
                if(friends[i].equals(to)){
                    toIndex = i;
                    findTo = true;
                }
                if(findFrom&&findTo) break;
            }
            giftMap[fromIndex][toIndex]++;
        }
        
        int[][] giftPoint = new int[friends.length][3];
        for(int i =0; i < friends.length; i++){
            int give = 0;
            for(int j =0;j < friends.length; j++) {
                give += giftMap[i][j];
            }
            giftPoint[i][0] = give;
        }
        for(int i =0; i < friends.length; i++){
            int give = 0;
            for(int j =0;j < friends.length; j++) {
                give += giftMap[j][i];
            }
            giftPoint[i][1] = give;
        }
        
        for(int[] point : giftPoint) {
            point[2] = point[0] - point[1];
        }
        
        // 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 
        // 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
        // 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
        int[] giftNum = new int[friends.length];
        for(int i =0; i<friends.length; i++) {
            for (int j =0; j<friends.length; j++){
                if(i==j) continue;
                int give = giftMap[i][j];
                int take = giftMap[j][i];
                if (give > take) {
                    giftNum[i]++;
                } else if (give == take) {
                    if(giftPoint[i][2] > giftPoint[j][2]) {
                        giftNum[i]++;
                    }
                }
            }
        }
        
        int max = -1;
        
        for(int i =0; i<giftNum.length;i++){
            if (max < giftNum[i]) {
                max = giftNum[i];
            }
        }
        
        return max;
    }
}