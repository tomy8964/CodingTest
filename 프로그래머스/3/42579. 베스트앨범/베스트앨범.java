import java.util.*;
class Solution {

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        Map<String, HashMap<Integer, Integer>> map = new HashMap<>();
        Map<String, Integer> genrePlayMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (!genrePlayMap.containsKey(genres[i])) {
                HashMap<Integer, Integer> tmp = new HashMap<>();
                tmp.put(i, plays[i]);
                map.put(genres[i], tmp);
            } else {
                map.get(genres[i]).put(i, plays[i]);
            }
            genrePlayMap.put(genres[i], genrePlayMap.getOrDefault(genres[i], 0) + plays[i]);
        }


        List<String> sortedGenrePlayList = new ArrayList(genrePlayMap.keySet());
        Collections.sort(sortedGenrePlayList, (s1, s2) -> genrePlayMap.get(s2) - (genrePlayMap.get(s1)));

        for (String genre : sortedGenrePlayList) {
            HashMap<Integer, Integer> unsortedPlayNum = map.get(genre);
            List<Integer> sortedPlayNum = new ArrayList(unsortedPlayNum.keySet());
            Collections.sort(sortedPlayNum, (s1, s2) -> unsortedPlayNum.get(s2) - (unsortedPlayNum.get(s1)));

            if (sortedPlayNum.size() >= 2) {
                answer.add(sortedPlayNum.get(0));
                answer.add(sortedPlayNum.get(1));
            } else answer.add(sortedPlayNum.get(0));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}