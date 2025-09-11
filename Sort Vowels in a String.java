import java.util.*;

class Solution {
    public String sortVowels(String s) {
        Set<Character> vowels = new HashSet<>(
            Arrays.asList('a','e','i','o','u','A','E','I','O','U')
        );

        List<Character> extracted = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                extracted.add(c);
            }
        }

        // Sort vowels
        Collections.sort(extracted);

        // Rebuild string
        StringBuilder result = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                result.append(extracted.get(idx++));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
