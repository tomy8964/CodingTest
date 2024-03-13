import java.util.Arrays;
class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;

        Arrays.sort(people);

        int index = 0;
        for (int i = n - 1; i >= index; i--) {
            if (people[i] + people[index] <= limit) {
                index++;
                answer++;
            } else answer++;
        }

        return answer;
    }
}