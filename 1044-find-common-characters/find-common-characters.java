import java.util.*;

class Solution {
    public List<String> commonChars(String[] words) {
        int[] minFreq = new int[26];
        
        // Step 1: Initialize with first word
        for (int i = 0; i < words[0].length(); i++) {
            char c = words[0].charAt(i);
            minFreq[c - 'a']++;
        }

        // Step 2: Compare with remaining words
        for (int i = 1; i < words.length; i++) {
            int[] freq = new int[26];
            
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                freq[c - 'a']++;
            }

            // Take minimum frequency
            for (int k = 0; k < 26; k++) {
                minFreq[k] = Math.min(minFreq[k], freq[k]);
            }
        }

        // Step 3: Build result
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (minFreq[i] > 0) {
                result.add(String.valueOf((char)(i + 'a')));
                minFreq[i]--;
            }
        }

        return result;
    }
}