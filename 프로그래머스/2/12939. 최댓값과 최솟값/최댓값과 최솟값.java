class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] split = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(String num : split) {
            if(min>Integer.parseInt(num)) min = Integer.parseInt(num);
            if(max < Integer.parseInt(num)) max = Integer.parseInt(num);
        }
        sb.append(min).append(" ").append(max);
        return sb.toString();
    }
}