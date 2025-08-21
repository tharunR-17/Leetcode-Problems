class Solution {
    public int numSubmat(int[][] matrix) {
        // Get the dimensions of the input matrix.
        int rows = matrix.length;
        int cols = matrix[0].length;
        // This variable will store our final count of all-ones submatrices.
        int count = 0;

        // We'll use an array to store the "height" of consecutive ones in each column.
        // For each row, we process it and update this height array.
        // Think of it as tracking how tall a stack of '1's is in each column as we go down the rows.
        int[] height = new int[cols];

        // Iterate through each row of the matrix.
        for (int i = 0; i < rows; i++) {
            // For each row, iterate through each column.
            for (int j = 0; j < cols; j++) {
                // Check the current cell in the matrix.
                if (matrix[i][j] == 1) {
                    // If it's a '1', increment the height for this column.
                    // This means the stack of '1's in this column is now one taller.
                    height[j]++;
                } else {
                    // If it's a '0', the stack of '1's is broken, so reset height for this column to 0.
                    height[j] = 0;
                }

                // Now, for the current cell (i, j), we want to count how many submatrices
                // (rectangles) have this cell as their bottom-right corner.
                // We'll do this by looking left from the current cell (j).

                // 'minHeight' will track the minimum height encountered as we look left.
                // Initially, it's just the height at the current column 'j'.
                int minHeight = height[j];

                // Iterate backwards from the current column 'j' towards the left (column 0).
                for (int k = j; k >= 0; k--) {
                    // If we encounter a '0' while looking left, it means any rectangle
                    // starting further left and ending at (i, j) cannot include this '0' column.
                    // So, we stop looking left for this (i, j) cell.
                    if (matrix[i][k] == 0) {
                        break; // Stop the inner loop (k)
                    }

                    // Update 'minHeight'. We want the shortest pillar encountered so far,
                    // from column 'k' to 'j'. This shortest pillar limits the height of any rectangle
                    // we can form with width from 'k' to 'j'.
                    minHeight = Math.min(minHeight, height[k]);

                    // 'minHeight' tells us how many valid rectangles we can form ending at (i, j)
                    // with a width from column 'k' to 'j'.
                    // For example, if minHeight is 3, it means we can form 3 rectangles:
                    // one of height 1, one of height 2, and one of height 3, all with the same width.
                    // So, we add this 'minHeight' to our total count.
                    count += minHeight;
                }
            }
        }

        // After processing all rows and columns, 'count' will hold the total number of submatrices with all ones.
        return count;
    }
}
