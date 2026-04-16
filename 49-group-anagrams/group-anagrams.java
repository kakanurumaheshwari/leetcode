import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            // Convert string to char array and sort
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Add to map
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }

        // Return all grouped values
        return new ArrayList<>(map.values());
    }
}