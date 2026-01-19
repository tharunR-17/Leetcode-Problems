class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        
        // Build prefix sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] = mat[i][j] + prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j];
            }
        }
        
        int left = 0, right = Math.min(m, n), ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean found = false;
            for (int i = mid; i <= m && !found; i++) {
                for (int j = mid; j <= n && !found; j++) {
                    int sum = prefix[i][j] - prefix[i - mid][j] - prefix[i][j - mid] + prefix[i - mid][j - mid];
                    if (sum <= threshold) found = true;
                }
            }
            if (found) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
