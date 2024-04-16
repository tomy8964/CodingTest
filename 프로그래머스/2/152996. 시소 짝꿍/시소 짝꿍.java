import java.util.*;
class Solution {

    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        // 몸무게, 사람 수 map 을 만든다.
        Map<Double, Integer> map = new HashMap<>();
        for (int weight : weights) {
            double a = weight * 1.0;            // 무게가 같은 경우
            double b = (weight * 2.0) / 3.0;    // 2/3인 경우
            double c = (weight * 1.0) / 2.0;    // 2/4인 경우
            double d = (weight * 3.0) / 4.0;    // 3/4인 경우

            if (map.containsKey(a)) answer += map.get(a);
            if (map.containsKey(b)) answer += map.get(b);
            if (map.containsKey(c)) answer += map.get(c);
            if (map.containsKey(d)) answer += map.get(d);

            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        return answer;
    }
}