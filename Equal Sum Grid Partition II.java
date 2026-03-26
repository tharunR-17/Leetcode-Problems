class Solution {
    static final int BIG_NUM = 100001;

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length; long total = 0;

        int[] bottom = new int[BIG_NUM], top = new int[BIG_NUM]; 
        for (int[] row : grid) {
            for (int x : row) {
                total += x;
                bottom[x]++;
            }
        }

        long top_sum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                top_sum += val;
                top[val]++;
                bottom[val]--;
            }


            long bottom_sum = total - top_sum;
            if (top_sum == bottom_sum) return true;

            long diff = Math.abs(top_sum - bottom_sum);
            if (diff < BIG_NUM) {
                if (top_sum > bottom_sum) {
                    if (check(top, grid, 0, i, 0, n - 1, diff)) {
                        return true;
                    }
                } else {
                    if (check(bottom, grid, i + 1, m - 1, 0, n - 1, diff)) {
                        return true;
                    }
                }
            }
        }

        int[] left = new int[BIG_NUM], right = new int[BIG_NUM];
        for (int[] row : grid) {
            for (int x : row) {
                right[x]++;
            }
        }

        long left_sum = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                left_sum += val;
                left[val]++;
                right[val]--;
            }

            long right_sum = total - left_sum;
            if (left_sum == right_sum) {
                return true;
            }

            long diff = Math.abs(left_sum - right_sum);
            if (diff < BIG_NUM) {
                if (left_sum > right_sum) {
                    if (check(left, grid, 0, m - 1, 0, j, diff)) {
                        return true;
                    }
                } else {
                    if (check(right, grid, 0, m - 1, j + 1, n - 1, diff)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean check(int[] sum, int[][] grid, int r1, int r2, int c1, int c2, long diff) {
        int rows = r2 - r1 + 1, cols = c2 - c1 + 1;

        if (rows * cols == 1) return false;

        if (rows > 1 && cols > 1) {
            return sum[(int)diff] > 0;
        }

        if (rows == 1) {
            return grid[r1][c1] == diff || grid[r2][c2] == diff;
        } else {
            return grid[r1][c1] == diff || grid[r2][c1] == diff;
        }
    }
}
