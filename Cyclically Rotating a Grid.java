class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;

        for (int l = 0; l < layers; l++) {
            List<Integer> vals = new ArrayList<>();
            int top = l, left = l;
            int bottom = m - l - 1, right = n - l - 1;

            // top row
            for (int j = left; j < right; j++)
                vals.add(grid[top][j]);
            // right col
            for (int i = top; i < bottom; i++)
                vals.add(grid[i][right]);
            // bottom row
            for (int j = right; j > left; j--)
                vals.add(grid[bottom][j]);
            // left col
            for (int i = bottom; i > top; i--)
                vals.add(grid[i][left]);

            int len = vals.size();
            int shift = k % len;
            Collections.rotate(vals, -shift);

            int idx = 0;
            // put back
            for (int j = left; j < right; j++)
                grid[top][j] = vals.get(idx++);
            for (int i = top; i < bottom; i++)
                grid[i][right] = vals.get(idx++);
            for (int j = right; j > left; j--)
                grid[bottom][j] = vals.get(idx++);
            for (int i = bottom; i > top; i--)
                grid[i][left] = vals.get(idx++);
        }
        return grid;
    }
}
