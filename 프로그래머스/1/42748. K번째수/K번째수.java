import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
                int[] answer = new int[commands.length];

        for (int a = 0; a < commands.length; a++) {
            int i = commands[a][0];
            int j = commands[a][1];
            int k = commands[a][2];

            int[] newArray = new int[j - i + 1];
            for (int q = 0; q <= j - i; q++) {
                newArray[q] = array[i + q - 1];
            }
            Arrays.sort(newArray);
            answer[a] = newArray[k - 1];
        }

        return answer;
    }
}