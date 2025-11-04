import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<String> known = new ArrayList<>();
        List<String> unknown = new ArrayList<>();
        int maxDigit = 0;

        // 수식 분류 및 최대 자리수 찾기
        for (String ex : expressions) {
            String[] arr = ex.split(" ");
            if (!arr[4].equals("X")) known.add(ex);
            else unknown.add(ex);
            for (String s : new String[]{arr[0], arr[2], arr[4].equals("X") ? "0" : arr[4]}) {
                for (char c : s.toCharArray()) {
                    int v = Character.getNumericValue(c);
                    maxDigit = Math.max(maxDigit, v);
                }
            }
        }

        // 가능한 진법 찾기
        Set<Integer> useRadixes = new HashSet<>();
        for (int radix = Math.max(2, maxDigit + 1); radix <= 9; radix++) {
            boolean valid = true;
            for (String ex : known) {
                String[] arr = ex.split(" ");
                String a = arr[0], op = arr[1], b = arr[2], c = arr[4];
                if (!isValid(a, radix) || !isValid(b, radix) || !isValid(c, radix)) {
                    valid = false;
                    break;
                }
                int num1 = Integer.parseInt(a, radix);
                int num2 = Integer.parseInt(b, radix);
                int ans = Integer.parseInt(c, radix);
                if (op.equals("+") && num1 + num2 != ans) valid = false;
                if (op.equals("-") && num1 - num2 != ans) valid = false;
                if (!valid) break;
            }
            if (valid) useRadixes.add(radix);
        }

        // X 채우기
        List<String> answerList = new ArrayList<>();
        for (String ex : unknown) {
            String[] arr = ex.split(" ");
            String a = arr[0], op = arr[1], b = arr[2];
            Set<String> results = new HashSet<>();
            for (int radix : useRadixes) {
                if (!isValid(a, radix) || !isValid(b, radix)) continue;
                int num1 = Integer.parseInt(a, radix);
                int num2 = Integer.parseInt(b, radix);
                int ans = op.equals("+") ? num1 + num2 : num1 - num2;
                results.add(Integer.toString(ans, radix));
            }
            String result = results.size() == 1 ? results.iterator().next() : "?";
            answerList.add(a + " " + op + " " + b + " = " + result);
        }

        return answerList.toArray(new String[0]);
    }

    static boolean isValid(String num, int radix) {
        for (char c : num.toCharArray()) {
            int v = Character.getNumericValue(c);
            if (v < 0 || v >= radix) return false;
        }
        return true;
    }
}
