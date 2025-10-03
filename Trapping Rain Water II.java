class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int [][] volume = new int[m][n];
        for (int i = 0; i < m; i++){
            System.arraycopy(heightMap[i], 0, volume[i], 0, n);
        }
        boolean upt = true, init = true;
        while (upt){
            upt = false;
            for (int i = 1; i < m - 1; i++){
                for (int j = 1; j < n - 1; j++){
                    int val = Math.max(heightMap[i][j], Math.min(volume[i-1][j], volume[i][j-1]));
                    if (init || volume[i][j] > val){
                        volume[i][j] = val;
                        upt = true;
                    }
                }
            }
            init = false;
            for (int i = m - 2; i >= 1; i--){
                for (int j = n - 2; j >= 1; j--){
                    int val = Math.max(heightMap[i][j], Math.min(volume[i+1][j], volume[i][j+1]));
                    if (volume[i][j] > val){
                        volume[i][j] = val;
                        upt = true;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i < m - 1; i++){
            for (int j = 1; j < n - 1; j++){
                if (volume[i][j] > heightMap[i][j]){
                    res += volume[i][j] - heightMap[i][j];
                }
            }
        }
        return res;
    }
}
