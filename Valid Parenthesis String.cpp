class Solution {
public:
    int solve(string &s,int ind,int open,vector<vector<int>> &dp,int n){
      if(ind==n){
        return open==0;
      }
      if(open<0) return 0;
      if(dp[ind][open]!=-1) return dp[ind][open];
      if(s[ind]!='*'){
        if(s[ind]=='('){
         return dp[ind][open]=solve(s,ind+1,open+1,dp,n);
        }
        else{
         return dp[ind][open]=solve(s,ind+1,open-1,dp,n);
        }
      }
      return dp[ind][open]=solve(s,ind+1,open,dp,n) || solve(s,ind+1,open+1,dp,n) || solve(s,ind+1,open-1,dp,n);
    }
    bool checkValidString(string s) {
    int n=s.size();
    vector<vector<int>>dp(n+1,vector<int>(2*n+1,-1));
    return solve(s,0,0,dp,n);
    }
};
