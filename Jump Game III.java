class Solution {
    public boolean canReach(int[] arr, int start) {
        int n=arr.length;
        int[]visited=new int[n];
        if(arr[start]==0)return true;
        return bfs(arr,start+arr[start],visited)||bfs(arr,start-arr[start],visited);
    }
    public static boolean bfs(int[]arr,int index,int[]dp){
        if(index<0||index>=arr.length)return false;
        if(dp[index]!=0)return false;
        dp[index]=1;
        if(arr[index]==0)return true;
        return bfs(arr,index+arr[index],dp)||bfs(arr,index-arr[index],dp);
    }
}
