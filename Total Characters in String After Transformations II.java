class Solution {
    static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Build transition matrix M
        int[][] M = new int[26][26];
        for (int from = 0; from < 26; from++) {
            for (int j = 1; j <= nums.get(from); j++) {
                int to = (from + j) % 26;
                M[to][from] = (M[to][from] + 1) % MOD;
            }
        }

        // Raise matrix to the power t
        int[][] M_pow_t = matrixPower(M, t);

        // Apply matrix to freq vector
        int[] finalFreq = multiplyVector(M_pow_t, freq);

        // Compute total length
        int total = 0;
        for (int count : finalFreq) {
            total = (total + count) % MOD;
        }

        return total;
    }

    // Matrix exponentiation
    private int[][] matrixPower(int[][] matrix, int power) {
        int size = matrix.length;
        int[][] result = new int[size][size];

        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) result[i][i] = 1;

        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            power >>= 1;
        }

        return result;
    }

    // Matrix × Matrix
    private int[][] multiply(int[][] A, int[][] B) {
        int size = A.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    result[i][j] = (int)((result[i][j] + 1L * A[i][k] * B[k][j]) % MOD);
                }
            }
        }

        return result;
    }

    // Matrix × Vector
    private int[] multiplyVector(int[][] matrix, int[] vector) {
        int size = vector.length;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            long sum = 0;
            for (int j = 0; j < size; j++) {
                sum = (sum + 1L * matrix[i][j] * vector[j]) % MOD;
            }
            result[i] = (int) sum;
        }

        return result;
    }
}
