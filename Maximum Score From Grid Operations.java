class Solution {
    public long maximumScore(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        if (m == 1) return 0;

        long[][] col = new long[m][n + 1];

        // prefix sum per column
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                col[j][i + 1] = col[j][i] + grid[i][j];
            }
        }

        long[][] dp = new long[n + 1][n + 1];
        long[][] prefMax = new long[n + 1][n + 1];
        long[][] suffMax = new long[n + 1][n + 1];

        for (int c = 1; c < m; c++) {

            long[][] newdp = new long[n + 1][n + 1];

            for (int curr = 0; curr <= n; curr++) {
                for (int prev = 0; prev <= n; prev++) {

                    if (curr <= prev) {
                        long gain = col[c][prev] - col[c][curr];

                        newdp[curr][prev] = Math.max(
                                newdp[curr][prev],
                                suffMax[prev][0] + gain
                        );
                    } else {
                        long gain = col[c - 1][curr] - col[c - 1][prev];

                        newdp[curr][prev] = Math.max(
                                newdp[curr][prev],
                                Math.max(
                                        suffMax[prev][curr],
                                        prefMax[prev][curr] + gain
                                )
                        );
                    }
                }
            }

            // build prefix & suffix
            for (int curr = 0; curr <= n; curr++) {

                prefMax[curr][0] = newdp[curr][0];

                for (int prev = 1; prev <= n; prev++) {

                    long penalty = 0;
                    if (prev > curr)
                        penalty = col[c][prev] - col[c][curr];

                    prefMax[curr][prev] = Math.max(
                            prefMax[curr][prev - 1],
                            newdp[curr][prev] - penalty
                    );
                }

                suffMax[curr][n] = newdp[curr][n];

                for (int prev = n - 1; prev >= 0; prev--) {
                    suffMax[curr][prev] = Math.max(
                            suffMax[curr][prev + 1],
                            newdp[curr][prev]
                    );
                }
            }

            dp = newdp;
        }

        long ans = 0;
        for (int k = 0; k <= n; k++) {
            ans = Math.max(ans, Math.max(dp[0][k], dp[n][k]));
        }

        return ans;
    }
}
