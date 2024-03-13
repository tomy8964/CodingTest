class Solution {

    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int answerLen = number.length() - k;
        char[] charArray = number.toCharArray();

        int startIndex = 0;
        for (int i = 0; i < answerLen; i++) {
            char max = '0';
            for (int j = startIndex; j <= i + k; j++) {
                if (max < charArray[j]) {
                    max = charArray[j];
                    startIndex = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}