import java.util.*;
class Solution {

    public int solution(int N, int number) {
        // 최솟값이 8보다 크면 -1을 return 합니다.
        int answer = -1;

        // dp[1] = N 1개로 만들 수 있는 수
        // dp[2] = N 2개로 만들 수 있는 수 = dp[1] (+,-,*,/) dp[1] + NN
        // dp[3] = N 3개로 만들 수 있는 수 = dp[2] (+,-,*,/) dp[1] + dp[1] (+,-,*,/) dp[2] + NNN
        List<Set<Integer>> setList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            setList.add(new HashSet<>());
        }

        // N 개로 만들 수 있는 수는 N 하나 뿐이다.
        setList.get(1).add(N);

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (Integer first : setList.get(i - j)) {
                    for (Integer second : setList.get(j)) {
                        setList.get(i).add(first + second);
                        setList.get(i).add(first - second);
                        setList.get(i).add(first * second);
                        if (first != 0 && second != 0) {
                            setList.get(i).add(first / second);
                        }
                    }
                }
                setList.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
        }

        for (Set s : setList) {
            if (s.contains(number)) {
                answer = setList.indexOf(s);
                break;
            }
        }

        return answer;
    }
}