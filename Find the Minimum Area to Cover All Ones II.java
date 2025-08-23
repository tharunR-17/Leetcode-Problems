class Solution {

    private int boundingBoxArea(int[][] grid, int u, int d, int l, int r){
        int minRow = grid.length, maxRow = -1;
        int minCol = grid[0].length, maxCol = -1;

        for(int i=u;i<=d;i++){
            for(int j=l;j<=r; j++){
                if(grid[i][j] == 1){
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        // If no 1's is found, return "infinite" cost so this partition is invalid
        if(maxRow == -1){
            return Integer.MAX_VALUE/3;
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

    private int solve(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int best = n * m;
        //Case 1: L- shaped partitions
        for (int row = 0; row + 1 < n; row++) {
            for (int col = 0; col + 1 < m; col++) {
                //Top + Bottom - left + Bottom - right
                int area1 = boundingBoxArea(grid, 0, row, 0, m - 1) + boundingBoxArea(grid, row + 1, n - 1, 0, col)
                        + boundingBoxArea(grid, row + 1, n - 1, col + 1, m - 1);

                //top-left + top-right + bottom
                int area2 = boundingBoxArea(grid, 0, row, 0, col) + boundingBoxArea(grid, 0, row, col + 1, m - 1)
                        + boundingBoxArea(grid, row + 1, n - 1, 0, m - 1);

                best = Math.min(best, Math.min(area1, area2));
            }
        }

        //Case 2 : Three horizontal strips

        for (int r1 = 0; r1 + 2 < n; r1++) {
            for (int r2 = r1 + 1; r2 + 1 < n; r2++) {
                int area = boundingBoxArea(grid, 0, r1, 0, m - 1) + boundingBoxArea(grid, r1 + 1, r2, 0, m - 1)
                        + boundingBoxArea(grid, r2 + 1, n - 1, 0, m - 1);
                best = Math.min(best, area);
            }
        }
        return best;
    }

    private int[][] rotate90(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] rotated = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][n - 1 - i] = grid[i][j];
            }
        }
        return rotated;
    }

    public int minimumSum(int[][] grid) {
        int[][] rotated = rotate90(grid);
        return Math.min(solve(grid), solve(rotated));
    }
}
