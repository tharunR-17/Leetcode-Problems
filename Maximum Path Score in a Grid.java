class Solution {
    int limit;
    public int maxPathScore(int[][] grid, int k) {
        limit=-2;
        int m=grid.length,n=grid[0].length;
        int[][][] dp=new int[m+1][n+1][Math.min(k+1,m+n+1)];
        for(int[][] i:dp) for(int j[]:i) Arrays.fill(j,-1);
        int ans=compute(0,0,0,grid,k,dp);
        return ans==limit?-1:ans;
    }
    public int compute(int i,int j,int cost,int[][] grid,int k,int[][][] dp){
        if(cost>k) return limit;
        if(dp[i][j][cost]!=-1) return dp[i][j][cost];
        if(i==grid.length-1&&j==grid[0].length-1){
            if(grid[i][j]!=0) if(cost+1>k) return limit;
            return grid[i][j];
        }
        if(i==grid.length||j==grid[0].length) return limit;
        int ans=limit;
        int toadd=(grid[i][j]!=0)?1:0;
        ans=Math.max(ans,compute(i+1,j,cost+toadd,grid,k,dp));
        ans=Math.max(ans,compute(i,j+1,cost+toadd,grid,k,dp));
        return dp[i][j][cost]=(ans==limit?ans:ans+grid[i][j]);
    }
}
