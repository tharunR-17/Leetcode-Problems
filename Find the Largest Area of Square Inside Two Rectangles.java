class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {

        long maxArea = 0;
        int len = bottomLeft.length;

        for (int p = 0; p < len; p++) {
            for (int q = p + 1; q < len; q++) {

                long leftX = Math.max(bottomLeft[p][0], bottomLeft[q][0]);
                long rightX = Math.min(topRight[p][0], topRight[q][0]);

                long lowerY = Math.max(bottomLeft[p][1], bottomLeft[q][1]);
                long upperY = Math.min(topRight[p][1], topRight[q][1]);

                if (leftX < rightX && lowerY < upperY) {

                    long side = Math.min(rightX - leftX, upperY - lowerY);
                    long area = side * side;

                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }

}
