import java.util.*;

public class Solution {

    public int[] solution(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        int prev = -1;
        for (int num : arr) {
            if (prev != num) {
                queue.add(num);
                prev = num;
            }
        }
        return queue.stream().mapToInt(i -> i).toArray();
    }
}