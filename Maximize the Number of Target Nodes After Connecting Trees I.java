class Solution {
    public void bottomUp(int node , int parent , int dp[][], ArrayList<Integer>[]  adj , int k){
        // System.out.println("itr " + node);
        for(int i =0; i<=k ;i++) dp[node][i] = 1;
        for(var i:adj[node]){
            if(i == parent) continue;
            bottomUp(i , node , dp , adj , k);
            for(int j =1; j<=k ;j++){
                dp[node][j] += dp[i][j-1];
            }
        }
    }

    public void topDown(int node , int parent , int dp[][], ArrayList<Integer>[]  adj ,int k){
        for(var i:adj[node]){
            if(i == parent) continue;
            for(int j = k; j>=2 ;j--){
                dp[i][j] += dp[node][j-1] - dp[i][j-2]; // populating to childrens
            }
            if(k>0) dp[i][1]+=dp[node][0]; // adding parent node
            topDown(i , node , dp , adj , k);
        }
    }
    public int[] maxTargetNodes(int[][] e1, int[][] e2, int k) {
        int n = e1.length + 1 , m = e2.length + 1;
        ArrayList<Integer>[]  adj1 = new ArrayList[n];
        ArrayList<Integer>[]  adj2 = new ArrayList[m];
        for(int i =0; i<n; i++) adj1[i] = new ArrayList<>();
        for(int i =0; i<m; i++) adj2[i] = new ArrayList<>();
        for(var i:e1) {
            adj1[i[0]].add(i[1]);
            adj1[i[1]].add(i[0]);
        }
        for(var i:e2) {
            adj2[i[0]].add(i[1]);
            adj2[i[1]].add(i[0]);
        }

        int k1 = Math.min(n-1 , k);
        int k2 = Math.min(m-1 , k-1);
        if(k1 == 0){
            int res[] = new int[n];
            Arrays.fill(res, 1);
            return res;
        }
        int dp1[][] = new int[n][k1+1];
        int dp2[][] = new int[m][k2+1];
        
        bottomUp(0,-1,dp1 , adj1, k1);
        topDown(0,-1,dp1 , adj1 , k1);
        
        bottomUp(0,-1,dp2 , adj2, k2);
        topDown(0,-1,dp2 , adj2, k2);
        int maxNodeInT2 = 1;
        for(int i =0; i<m ;i++) maxNodeInT2 = Math.max(maxNodeInT2 , dp2[i][k2]);
        int res[] = new int[n];
        for(int i =0; i<n;i++) res[i] = dp1[i][k1] + maxNodeInT2;

        return res;
    }
}
