class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int row = boxGrid.length;
        int col = boxGrid[0].length;
        char[][] rotate = new char[col][row];
        for (int i = 0; i < col; i++) {
            Arrays.fill(rotate[i], '.');
        }

        for (int k = 0; k < row; k++) {
            int bottom = col - 1;
            for (int j = col - 1; j >= 0; j--) {
                if (boxGrid[k][j] == '#') {
                    rotate[bottom][row - 1 - k] = '#';
                    bottom--;

                } 
                else if (boxGrid[k][j] == '*') {
                    rotate[j][row - 1 - k] = '*';
                    bottom = j - 1;
                }
            }
        }
        return rotate;
    }
}
