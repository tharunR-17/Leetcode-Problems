class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 2) return 0;
        
        int[] dp = new int[27];
        for (int i = 0; i < 27; i++) dp[i] = Integer.MAX_VALUE;
        
        char[] w = word.toCharArray();
        dp[26] = dist(w[1], w[0]);
        dp[w[0] - 'A'] = 0;
        
        for (int i = 2; i < n; i++) {
            int delta = dist(w[i], w[i - 1]);
            int best = dp[26];
            for (int j = 0; j < 27; j++) {
                if (dp[j] < best) {
                    int cost = (j == 26 ? 0 : dist(w[i], (char)(j + 'A')));
                    best = Math.min(best, dp[j] + cost);
                }
                if (dp[j] < Integer.MAX_VALUE) dp[j] += delta;
            }
            dp[w[i - 1] - 'A'] = Math.min(dp[w[i - 1] - 'A'], best);
        }
        
        int res = Integer.MAX_VALUE;
        for (int v : dp) res = Math.min(res, v);

        return res;
    }
    
    private int dist(char a, char b) {
        int x1 = (a - 'A') / 6, y1 = (a - 'A') % 6;
        int x2 = (b - 'A') / 6, y2 = (b - 'A') % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
