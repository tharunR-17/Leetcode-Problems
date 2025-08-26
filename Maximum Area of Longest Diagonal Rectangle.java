class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        long maxDiagonalSquared = 0;
        long maxSquare = 0;
        for (int i = 0; i < dimensions.length; i++) {
            int length = dimensions[i][0];
            int width = dimensions[i][1];

            long diagonalSquared = (long) length * length + (long) width * width;
            long square = (long) length * width;

            if (maxDiagonalSquared < diagonalSquared) {
                maxDiagonalSquared = diagonalSquared;
                maxSquare = square;
            } else if (maxDiagonalSquared == diagonalSquared) {
                maxSquare = Math.max(maxSquare, square);
            }
        }

        return (int) maxSquare;
    }
}
