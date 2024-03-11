import java.util.*;
class Solution {

    public int solution(int[][] sizes) {
        for (int[] wallet : sizes) {
            Arrays.sort(wallet);
        }
        int maxW = -1;
        int maxH = -1;
        for (int[] wallet : sizes) {
            maxW = Math.max(maxW, wallet[0]);
            maxH = Math.max(maxH, wallet[1]);
        }
        return maxW * maxH;
    }
}