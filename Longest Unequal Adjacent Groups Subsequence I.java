import java.util.*;

public class Solution {
    private static int prevBit;

    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        prevBit = -1;  // Initialize with an invalid bit to allow first word
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < groups.length; i++) {
            if (prevBit == groups[i]) continue; // Skip if same as previous group
            prevBit = groups[i];                // Update previous bit
            result.add(words[i]);               // Add word to result
        }
        
        return result;
    }
}
