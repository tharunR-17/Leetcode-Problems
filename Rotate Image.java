class Solution {
    public void rotate(int[][] matrix) {
        // Step 1: Transpose the Array
        // Convert rows to columns
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Swap first column with last column and so on (Reverse Rows)
        for (int i = 0; i < matrix.length / 2; i++) {           // column iterator (0 to mid)
            for (int j = 0; j < matrix[0].length; j++) {        // row iterator (all rows)
                int temp = matrix[j][i];
                matrix[j][i] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = temp;
            }
        }
    }
}
