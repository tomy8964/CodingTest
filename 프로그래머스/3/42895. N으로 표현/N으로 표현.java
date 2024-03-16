import java.util.*;
class Solution {

    public int solution(int N, int number) {
        int answer = -1;

        // N을 몇개 사용해서 만들 수 있는 숫자들로 분리
        // dp[1] == N을 1개 사용해서 만들 수 있는 숫자
        // dp[2] == dp[1] ( +, -, *, /) dp[1] + NN
        // dp[3] == dp[1] ( +, -, *, /) dp[2] + dp[2] ( +, -, *, /) dp[1] + NNN
        // dp[4] == dp[1] ( +, -, *, /) dp[3] + dp[3] ( +, -, *, /) dp[1] + dp[2] ( +, -, *, /) dp[2] + NNNN

        List<Set<Integer>> setList = new ArrayList<>();

        // 9 개 이상의 숫자를 사용하면 -1를 리턴 : 문제 조건
        for (int i = 0; i < 9; i++) {
            setList.add(new HashSet<>());
        }

        // N을 1개를 사용해서 만들 수 있는 숫자는 N 하나뿐
        setList.get(1).add(N);

        if (N == number) return 1;

        for (int i = 2; i < 9; i++) {
            Set<Integer> numbers = setList.get(i);
            for (int j = 1; j <= i; j++) {
                Set<Integer> firstSet = setList.get(j);
                Set<Integer> secondSet = setList.get(i - j);
                for (Integer first : firstSet) {
                    for (Integer second : secondSet) {
                        numbers.add(first + second);
                        numbers.add(first - second);
                        numbers.add(first * second);
                        if (first != 0 && second != 0) {
                            numbers.add(first / second);
                        }
                    }
                }
            }
            numbers.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }

        for (Set s : setList) {
            if (s.contains(number)) return setList.indexOf(s);
        }

        return answer;
    }
}