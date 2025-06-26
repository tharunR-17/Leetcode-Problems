class Solution {
    public int longestSubsequence(String s, int k) {
        int sum = 0;
        int length = 0;
        int maxBits = (int) (Math.log(k) / Math.log(2)) + 1;

        for (int i = 0; i < s.length(); i++) {
            char bit = s.charAt(s.length() - 1 - i);
            if (bit == '1') {
                if (i < maxBits && sum + (1 << i) <= k) {
                    sum += 1 << i;
                    length++;
                }
            } else {
                length++;
            }
        }

        return length;
    }
}
