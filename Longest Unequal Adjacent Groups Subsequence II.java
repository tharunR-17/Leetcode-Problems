class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int[] dp = new int[words.length];
        int[] next = new int[words.length];

        dp[words.length - 1] = 1;
        next[words.length - 1] = -1;

        int maxLen = 1;
        int maxIndex = 0;


        for (int i = words.length - 2; i >= 0; i--) {
            dp[i] = 1;
            next[i] = -1;
            for (int j = i + 1; j < words.length; j++) {
                if (dp[j] + 1 > dp[i] && isHummingDistanceOne(words[i], words[j], groups[i], groups[j])) {
                    dp[i] = dp[j] + 1;
                    next[i] = j;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        List<String> result = new ArrayList<>();

        while (maxIndex >= 0) {
            result.add(words[maxIndex]);
            maxIndex = next[maxIndex];
        }

        return result;
    }

    boolean isHummingDistanceOne(String word1, String word2, int g1, int g2) {
        if (g1 == g2) return false;
        if (word1.length() != word2.length()) return false;
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++count > 1) return false;
            }
        }

        return count == 1;
    }
}
