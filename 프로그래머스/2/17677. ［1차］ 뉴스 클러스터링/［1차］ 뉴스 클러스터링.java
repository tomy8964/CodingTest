import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        char[] char1 = str1.toCharArray();
        char[] char2 = str1.toCharArray();
        
        List<String> arr1 = new ArrayList<>();
        List<String> arr2 = new ArrayList<>();
        
        for(int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            
            if (c1 < 'a' || c1 > 'z' || c2 < 'a' || c2 > 'z') continue;
            
            arr1.add(String.valueOf(c1) + String.valueOf(c2));
        }
        
        for(int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            
            if (c1 < 'a' || c1 > 'z' || c2 < 'a' || c2 > 'z') continue;
            
            arr2.add(String.valueOf(c1) + String.valueOf(c2));
        }
        
        String[] array1 = arr1.toArray(new String[arr1.size()]);
        String[] array2 = arr2.toArray(new String[arr2.size()]);
        boolean[] visited = new boolean[array2.length];
        
        int answer = 0;
        for(int i = 0; i < array1.length; i++) {
            for(int j = 0; j < array2.length; j++){
                if(visited[j]) continue;
                if(array1[i].equals(array2[j])){
                    visited[j] = true;
                    answer++;
                    break;
                }
            }
        }
        
        System.out.println(answer);
        
        boolean[] visited1 = new boolean[array1.length];
        boolean[] visited2 = new boolean[array2.length];
        
        int count = 0;
        for(int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if(visited2[j]) continue;
                if(array1[i].equals(array2[j])){
                    visited1[i] = true;
                    visited2[j] = true;
                    count++;
                    break;
                }
            }
            if(visited1[i]) continue;
            visited1[i] = true;
            count++;
        }
        
        for(int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if(visited1[j]) continue;
                if(array1[i].equals(array2[j])){
                    visited1[j] = true;
                    visited2[i] = true;
                    count++;
                    break;
                }
            }
            if(visited2[i]) continue;
            visited2[i] = true;
            count++;
        }
        
        if(answer == 0 && count == 0) return 65536;
        
        return (int) ((answer / (double) count) * 65536);
    }
}