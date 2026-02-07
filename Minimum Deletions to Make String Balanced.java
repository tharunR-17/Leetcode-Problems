class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] cnta = new int[n + 1];
        int[] cntb = new int[n + 1];

        // Prefix sum for 'a's
        for (int i = 0; i < n; i++) {
            cnta[i + 1] = cnta[i] + (s.charAt(i) == 'a' ? 1 : 0);
        }

        // Suffix sum for 'b's
        for (int i = n - 1; i >= 0; i--) {
            cntb[i] = cntb[i + 1] + (s.charAt(i) == 'b' ? 1 : 0);
        }

        int minDelete = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            // Total deletions = n - (total 'a's kept + total 'b's kept)
            minDelete = Math.min(minDelete, n - cnta[i] - cntb[i]);
        }
        
        return minDelete;
    }
}
